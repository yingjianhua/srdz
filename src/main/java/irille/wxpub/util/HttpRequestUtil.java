package irille.wxpub.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class HttpRequestUtil {
	
	/** InputSource
	 * http post请求 
	 * @param urlStr URL地址 
	 * @throws IOException 
	 * @throws Exception 
	 */  
	public static String httpRequestPost(String urlStr) throws IOException {  
		URL url = new URL(urlStr);  
		StringBuffer buffer = new StringBuffer();
		HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();  
		
		//设置是否向connection输出，因为是post请求，参数要放在 http正文内，因此需要设为true  
		httpConn.setDoOutput(true);  
		//是否向connection输入，默认为true  
		httpConn.setDoInput(true);  
		//POST请求方式  
		httpConn.setRequestMethod("POST");  
		//post请求不能使用缓存  
		httpConn.setUseCaches(false);  
		//是否自动执行重定向,默认为true  
		httpConn.setInstanceFollowRedirects(true);  
		//配置本次连接的Content-type，配置为application/x-www-form-urlencoded的意思是正文是urlencoded编码过的form参数  
		//下面我们可以看到我们对正文内容使用URLEncoder.encode 进行编码  
		httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");  
		//连接，从openConnection()至此的配置必须要在connect之前完成， 注意的是connection.getOutputStream会隐含的进行connect。  
		httpConn.connect();  
		DataOutputStream dos = new DataOutputStream(httpConn.getOutputStream());  
		//刷新流  
		dos.flush();  
		//关闭流  
		dos.close();  
		// 取得输入流，并使用Reader读取  
		BufferedReader reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));  
		String result = null;
		while ((result = reader.readLine()) != null) {
			buffer.append(result);
		}
		result = buffer.toString();
		reader.close();  
		httpConn.disconnect();  
		return result;
	}  
	/** 
     * http post请求 
     * @param urlStr URL地址 
     * @throws Exception 
     */  
    public static String httpRequestPost(String urlStr, String param) throws Exception{  
        URL url = new URL(urlStr);  
        StringBuffer buffer = new StringBuffer();
        HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();  
          
        //设置是否向connection输出，因为是post请求，参数要放在 http正文内，因此需要设为true  
        httpConn.setDoOutput(true);  
        //是否向connection输入，默认为true  
        httpConn.setDoInput(true);  
        //POST请求方式  
        httpConn.setRequestMethod("POST");  
        //post请求不能使用缓存  
        httpConn.setUseCaches(false);  
        //是否自动执行重定向,默认为true  
        httpConn.setInstanceFollowRedirects(true);  
        //配置本次连接的Content-type，配置为application/x-www-form-urlencoded的意思是正文是urlencoded编码过的form参数  
        //下面我们可以看到我们对正文内容使用URLEncoder.encode 进行编码  
        httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");  
        //连接，从openConnection()至此的配置必须要在connect之前完成， 注意的是connection.getOutputStream会隐含的进行connect。  
        httpConn.connect();  
        DataOutputStream dos = new DataOutputStream(httpConn.getOutputStream());  
        //发送正文，正文内容其实跟get的URL中'?'后的参数字符串一致 word=wait&tn=news&from=news  
        //DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写道流里面  
        dos.write(param.getBytes("UTF-8"));   
        //刷新流  
        dos.flush();  
        //关闭流  
        dos.close();  
        // 取得输入流，并使用Reader读取  
        BufferedReader reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));  
        System.out.println("=========post request接收数据内容开始============");  
        String result = null;
        while ((result = reader.readLine()) != null) {
        	buffer.append(result);
        }
        result = buffer.toString();
        reader.close();  
        System.out.println("=========post request接收数据内容结束============"); 
        httpConn.disconnect();  
        return result;
    }  
  
    /** 
     * http get 请求 
     * @param urlStr 请求URL地址 
     * @throws Exception 
     */  
    public static void httpRequestGet(String urlStr) throws Exception{  
        //URL拼接,如："http://www.baidu.com?name=HI,中国",这里对特殊字符进行了编码，不然会产生乱码  
//        URL url = new URL(urlStr + "?filePath=" + URLEncoder.encode("设计资料/设计", "utf-8"));  
    	URL url = new URL(urlStr);
          
        //openConnection函数会根据URL的协议返回不同的URLConnection子类的对象  
        //这里URL是一个http,因此实际返回的是HttpURLConnection   
        HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();  
          
        //进行连接,实际上request要在下一句的connection.getInputStream()函数中才会真正发到 服务器****待验证  
        httpConn.connect();  
          
         // 取得输入流，并使用Reader读取  
        BufferedReader reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));  
          
        System.out.println("=========get request接收数据内容开始============");  
        String lines;  
        while ((lines = reader.readLine()) != null) {  
            System.out.println(lines);  
        }  
        reader.close();  
        System.out.println("=========get request接收数据内容结束============");  
        httpConn.disconnect();  
    }  
  
      
    /** 
     * http post 请求(未测试) 
     *  
     * @param urlStr 
     * @throws Exception 
     */  
    public static void httpRequestPostBig(String urlStr) throws Exception {  
            URL postUrl = new URL(urlStr);  
            HttpURLConnection connection = (HttpURLConnection) postUrl  
                    .openConnection();  
            connection.setDoOutput(true);  
            connection.setDoInput(true);  
            connection.setRequestMethod("POST");  
            connection.setUseCaches(false);  
            connection.setInstanceFollowRedirects(true);  
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");  
             //与httpRequestPost()的不同，设置了块大小为5字节  
            connection.setChunkedStreamingMode(5);  
            connection.connect();  
            /* 
             * 注意，下面的getOutputStream函数工作方式于在httpRequestPost()里面的不同 
             * 在httpRequestPost()里面该函数仍在准备http request，没有向服务器发送任何数据 
             * 而在这里由于设置了ChunkedStreamingMode，getOutputStream函数会根据connect之前的配置 
             * 生成http request头，先发送到服务器。 
             */  
            DataOutputStream out = new DataOutputStream(connection  
                    .getOutputStream());  
            String content = "name=" + URLEncoder.encode("发送到服务器", "utf-8");  
            out.writeBytes(content);   
  
            out.flush();  
            out.close(); //到此时服务器已经收到了完整的http request了，而在httpRequestPost()函数里，要等到下一句getInputStream()服务器才能收到http请求。  
            BufferedReader reader = new BufferedReader(new InputStreamReader(  
                    connection.getInputStream()));  
              
            out.flush();  
            out.close();  
            String line;  
            System.out.println("==========post request开始==========");  
            while ((line = reader.readLine()) != null) {  
                System.out.println(line);  
            }  
            System.out.println("==========post request结束==========");  
            reader.close();  
            connection.disconnect();  
        }  
    
    
    /** Reader
	 * http post请求 
	 * @param urlStr URL地址 
	 * @throws Exception 
	 */  
	public static Reader httpRequestPost1(String urlStr) throws Exception{  
		URL url = new URL(urlStr);  
		StringBuffer buffer = new StringBuffer();
		HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();  
		
		//设置是否向connection输出，因为是post请求，参数要放在 http正文内，因此需要设为true  
		httpConn.setDoOutput(true);  
		//是否向connection输入，默认为true  
		httpConn.setDoInput(true);  
		//POST请求方式  
		httpConn.setRequestMethod("POST");  
		//post请求不能使用缓存  
		httpConn.setUseCaches(false);  
		//是否自动执行重定向,默认为true  
		httpConn.setInstanceFollowRedirects(true);  
		//配置本次连接的Content-type，配置为application/x-www-form-urlencoded的意思是正文是urlencoded编码过的form参数  
		//下面我们可以看到我们对正文内容使用URLEncoder.encode 进行编码  
		httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");  
		//连接，从openConnection()至此的配置必须要在connect之前完成， 注意的是connection.getOutputStream会隐含的进行connect。  
		httpConn.connect();  
		DataOutputStream dos = new DataOutputStream(httpConn.getOutputStream());  
		//刷新流  
		dos.flush();  
		//关闭流  
//		dos.close();  
		// 取得输入流，并使用Reader读取  
		BufferedReader reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));  
		System.out.println("=========post request接收数据内容开始============");  
		String result = null;
		while ((result = reader.readLine()) != null) {
			buffer.append(result);
		}
		result = buffer.toString();
		reader.close();  
		System.out.println("=========post request接收数据内容结束============"); 
		httpConn.disconnect();  
		return reader;
	}  

}
