package irille.wxpub.util;

import irille.pub.Log;
import irille.pub.Str;
import irille.pub.bean.BeanBase;
import irille.pub.idu.Idu;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.struts2.ServletActionContext;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 公众平台通用接口工具类
 * 
 * @author liuyq
 * @date 2013-08-09
 */
public class WeixinUtil {
	private static final Log LOG = new Log(WeixinUtil.class);

	// 获取access_token的接口地址（GET） 限200（次/天）
	public final static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	public final static String JSAPI_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
	// 菜单创建（post） 限100（次/天）
	public static String MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	// 客服接口地址
	public static String SEND_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
	/** 创建二维码ticket请求 **/
	public static String QRCODE_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";
	/** 通过ticket换取二维码 **/
	public static String GET_QRCODE_URL = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";
	//
	// private static final ResourceBundle bundle =
	// ResourceBundle.getBundle("weixin");
	/** 微信网页授权获取CODE **/
	public static String WEB_OAUTH_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
	/** 微信网页授权获取网页accesstoken和OPENID **/
	public static String WEB_OAUTH_ACCESSTOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	public static final String ERR_CODE = "errcode";
	public static final String ERR_MSG = "errmsg";

	public static String TEMPLATE_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
	/** 微信JSSDK JSAPI_TICKET获取CODE */
	public static String JS_SGIN_STR = "jsapi_ticket=JSAPI_TICKET&noncestr=NONCESTR&timestamp=TIMESTAMP&url=URL";
	/** 微信下载多媒体文件CODE */
	public static String MEDIA_DOWNLOAD_URL = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";

	public static void main(String[] args) {
		String str = "<xml>"+
						"<act_name>美食分享</act_name>"+
						"<client_ip>115.218.232.76</client_ip>"+
						"<mch_billno>1265333801201607080000000001</mch_billno>"+
						"<mch_id>1265333801</mch_id>"+
						"<nonce_str>MVL7OKH4BV7P5JO0YEO2CJZ7GP1GEN51</nonce_str>"+
						"<re_openid>oaCY9t0WVQA8Jr40AYYR3uZYSaZo</re_openid>"+
						"<remark>感谢您对享食光的支持</remark>"+
						"<send_name>享食光</send_name>"+
						"<total_amount>100</total_amount>"+
						"<total_num>1</total_num>"+
						"<wishing>感谢您对享食光的支持</wishing>"+
						"<wxappid>wx13443f418ef53874</wxappid>"+
						"<sign>791D02CA775E6F5ED48E373FB046F8C6</sign>"+
					"</xml>";
		System.out.println(str);
		Matcher m = Pattern.compile("\\\\u([0-9a-fA-F]{4})").matcher(str);
		while (m.find())
			str = str.replaceAll("\\" + m.group(), "" + (char) Integer.parseInt(m.group(1), 16));
		System.out.println(str);
	}
	public static String httpPost(String requestUrl, String outputStr, SSLConnectionSocketFactory sslFactory) throws ClientProtocolException, IOException, JSONException {
		if (outputStr != null) {
			Matcher m = Pattern.compile("\\\\u([0-9a-fA-F]{4})").matcher(outputStr);
			while (m.find())
				outputStr = outputStr.replaceAll("\\" + m.group(), "" + (char) Integer.parseInt(m.group(1), 16));
		}
		LOG.info("requestUrl:"+requestUrl);
		LOG.info("request:"+outputStr);
		CloseableHttpClient httpclient;
		if(sslFactory==null) {
			httpclient = HttpClients.createDefault();
		} else {
			httpclient = HttpClients.custom().setSSLSocketFactory(sslFactory).build();
		}
		HttpPost httppost = new HttpPost(requestUrl);
		String buffer = "";
		try {
			if(outputStr!=null) {
				httppost.setEntity(new StringEntity(outputStr, "utf-8"));
			}
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				HttpEntity entity = response.getEntity();
				buffer = EntityUtils.toString(entity, "UTF-8");
				LOG.info("response:"+buffer);
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}
		return buffer; 
	}
	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
		if (outputStr != null) {
			Matcher m = Pattern.compile("\\\\u([0-9a-fA-F]{4})").matcher(outputStr);
			while (m.find())
				outputStr = outputStr.replaceAll("\\" + m.group(), "" + (char) Integer.parseInt(m.group(1), 16));
		}
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			LOG.info("requestUrl:"+requestUrl);
			LOG.info("requestStr:"+outputStr);
			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();
			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			LOG.info("responseStr:"+buffer);
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = new JSONObject(buffer.toString()); // TODO 未测试是否正确
		} catch (ConnectException ce) {
			throw LOG.err("timeout", "微信服务器连接超时");
		} catch (Exception e) {
			throw LOG.err(e);
		}
		return jsonObject;
	}
	
	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return 
	 */
	public static String httpRequestStr(String requestUrl, String requestMethod, String outputStr) {
		BufferedReader bufferedReader = null;
		OutputStream outputStream = null;
		String charsetName = "utf-8";
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			LOG.info("requestUrl:"+requestUrl);
			LOG.info("requestStr:"+outputStr);
			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();
			// 当有数据需要提交时
			if (!Str.isEmpty(outputStr)) {
				Matcher m = Pattern.compile("\\\\u([0-9a-fA-F]{4})").matcher(outputStr);
				while (m.find())
					outputStr = outputStr.replaceAll("\\" + m.group(), "" + (char) Integer.parseInt(m.group(1), 16));
				outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes(charsetName));
			}
			// 将返回的输入流转换成字符串
			bufferedReader = new BufferedReader(new InputStreamReader(httpUrlConn.getInputStream(), charsetName));
			StringBuffer buffer = new StringBuffer();
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str).append(Str.LN);
			}
			LOG.info("responseStr:"+buffer);
			httpUrlConn.disconnect();
			return buffer.toString();
		} catch (ConnectException ce) {
			throw LOG.err("timeout", "微信服务器连接超时");
		} catch (Exception e) {
			throw LOG.err(e);
		} finally {
			if(bufferedReader != null) 
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if(outputStream != null) 
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	/**
	 * 通过一个url去下载网络图片
	 * 
	 * @param requestUrl
	 *            网络图片的地址
	 * @param pathname
	 *            下载图片的保存路径
	 * @throws IOException
	 * @throws Exception
	 */
	public static boolean requestImage(String requestUrl, String requestMethod, String pathname) {
		try {
			// new一个URL对象
			URL url = new URL(requestUrl);
			// 打开链接
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 设置请求方式
			conn.setRequestMethod(requestMethod);
			// 超时响应时间为5秒
			conn.setConnectTimeout(5 * 1000);
			// 通过输入流获取图片数据
			InputStream inStream = conn.getInputStream();
			// 得到图片的二进制数据，以二进制封装得到数据，具有通用性
			byte[] data = readInputStream(inStream);
			File target = new File(pathname);
			if (!target.getParentFile().exists()) {
				target.getParentFile().mkdirs();
			}
			// 创建输出流
			FileOutputStream outStream = new FileOutputStream(target);
			// 写入数据
			outStream.write(data);
			// 关闭输出流
			outStream.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 向素材库请求素材文件 素材类型 图片素材（image） 音频素材（voice）
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式
	 * @param outputStr
	 *            发送内容
	 * @param pathname
	 *            目标文件保存路径
	 */
	public static void requestMaterial(String requestUrl, String requestMethod, String outputStr, String pathname) {
		// new一个URL对象
		try {
			URL url = new URL(requestUrl);
			// 打开链接
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 设置请求方式
			conn.setRequestMethod(requestMethod);
			// 设置允许输出
			conn.setDoOutput(true);
			// 超时响应时间为5秒
			conn.setConnectTimeout(5 * 1000);
			// 通过输入流获取图片数据
			// 当有数据需要提交时
			OutputStream outputStream = conn.getOutputStream();
			// 注意编码格式，防止中文乱码
			outputStream.write(outputStr.getBytes("UTF-8"));
			outputStream.close();

			InputStream inStream = conn.getInputStream();
			// 得到图片的二进制数据，以二进制封装得到数据，具有通用性
			byte[] data = readInputStream(inStream);
			try {
				// 错误情况下会返回JSON数据包；若不能解析为JSON数据格式，则表示成功
				JSONObject result = new JSONObject(new String(data));
				throw LOG.err("" + result.get("errcode"), "" + result.get("errmsg"));
			} catch (JSONException e) {
				File target = new File(pathname);
				if (!target.getParentFile().exists()) {
					target.getParentFile().mkdirs();
				}
				// 创建输出流
				FileOutputStream outStream = new FileOutputStream(target);
				// 写入数据
				outStream.write(data);
				// 关闭输出流
				outStream.close();
			}
		} catch (Exception e) {
			throw LOG.err(e);
		}
	}

	/**
	 * 保存HTTP获取的图片文件
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式
	 * @param target
	 *            目标文件保存路径
	 */
	public static void saveHttpImage(String requestUrl, String requestMethod, File target) {
		// new一个URL对象
		try {
			URL url = new URL(requestUrl);
			// 打开链接
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 设置请求方式
			conn.setRequestMethod(requestMethod);
			// 超时响应时间为5秒
			conn.setConnectTimeout(5 * 1000);
			// 通过输入流获取图片数据
			InputStream inStream = conn.getInputStream();
			// 得到图片的二进制数据，以二进制封装得到数据，具有通用性
			byte[] data = readInputStream(inStream);
			// 创建输出流
			FileOutputStream outStream = new FileOutputStream(target);
			// 写入数据
			outStream.write(data);
			// 关闭输出流
			outStream.close();
		} catch (Exception e) {
			throw LOG.err(e);
		}

	}
	/**
	 * 扩展的http post请求
	 * @param params
	 * post表单内容，可以是字符串形式的键值对，也可以是上传文件
	 * @throws IOException 
	 * @throws JSONException 
	 * */
	public static String httpRequestPost(String requestUrl, List<Map<String, Object>> params) throws IOException, JSONException {
		String BOUNDARY = "---------------------------" + System.currentTimeMillis(); // boundary就是request头和上传文件内容的分隔符
		List<Object> contents = new ArrayList<Object>();
		long Content_Length = 0L;
		for(Map<String, Object> map : params) {
			for(String key:map.keySet()) {
				Object value = map.get(key);
				StringBuffer buffer = new StringBuffer();
				if(value instanceof File) {
					//假如是文件类型
					buffer.append("--" + BOUNDARY + "\r\n");
					buffer.append("Content-Disposition: form-data; name=\"" +key+ "\"; filename=\"" +((File)value).getName()+ "\"; filelength=\"" +((File)value).length()+ "\"\r\n");
					buffer.append("Content-Type:application/octet-stream\r\n\r\n");
					byte[] byte_disposition = buffer.toString().getBytes("utf-8");
					Content_Length += byte_disposition.length;
					contents.add(byte_disposition);
					Content_Length += ((File) value).length();
					contents.add(value);
				} else {
					buffer.append("--" + BOUNDARY + "\r\n");
					buffer.append("Content-Disposition: form-data; name=\"" +key+ "\"\r\n\r\n");
					byte[] byte_disposition = buffer.toString().getBytes("utf-8");
					Content_Length += byte_disposition.length;
					contents.add(byte_disposition);
					byte[] byte_value = (value+"").getBytes("utf-8");
					Content_Length += byte_value.length;
					contents.add(byte_value);
				}
			}
		}
		byte[] byte_end = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");
		Content_Length += byte_end.length;
		contents.add(byte_end);
		
		URL url = new URL(requestUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(5000);
		conn.setReadTimeout(30000);
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setUseCaches(false);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Connection", "Keep-Alive");
		conn.setRequestProperty("Charset", "UTF-8");
		conn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.3; WOW64; rv:40.0) Gecko/20100101 Firefox/40.0");
		conn.setRequestProperty("Content-Length", Content_Length + "");
		conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
		
		OutputStream out = new DataOutputStream(conn.getOutputStream());
		for(Object value : contents) {
			if(value instanceof File) {
				DataInputStream in = new DataInputStream(new FileInputStream((File)value));
				int bytes = 0;
				byte[] bufferOut = new byte[1024];
				while ((bytes = in.read(bufferOut)) != -1) {
					out.write(bufferOut, 0, bytes);
				}
				in.close();
			} else if (value instanceof byte[]){
				out.write((byte[])value);
			}
		}
		out.flush();
		out.close();
		
		// 读取返回数据
		StringBuffer result = new StringBuffer();
		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line = null;
		while ((line = reader.readLine()) != null) {
			result.append(line).append("\n");
		}
		reader.close();
		System.out.println(result);
		return result.toString();
	}

	public static JSONObject uploadImg(String requestUrl, String requestMethod, File imgFile) {
		JSONObject jsonObject = null;
		String BOUNDARY = "---------------------------" + System.currentTimeMillis(); // boundary就是request头和上传文件内容的分隔符
		HttpURLConnection conn = null;
		try {
			URL url = new URL(requestUrl);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(30000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod(requestMethod);
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("Charset", "UTF-8");
			conn.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 6.3; WOW64; rv:40.0) Gecko/20100101 Firefox/40.0");

			StringBuffer buf = new StringBuffer();
			buf.append("--" + BOUNDARY + "\r\n");
			buf.append("Content-Disposition: form-data; name=\"media\"; filename=\"" + imgFile.getName()
					+ "\"; filelength=\"" + imgFile.length() + "\"\r\n");
			if (imgFile.getName().endsWith(".png")) {
				buf.append("Content-Type:image/png\r\n\r\n");
			} else if (imgFile.getName().endsWith(".jpg")) {
				buf.append("Content-Type:image/jpeg\r\n\r\n");
			} else {
				buf.append("Content-Type:application/octet-stream\r\n\r\n");
				// throw LOG.err("noPattern", "文件格式不支持");
			}
			byte[] header = buf.toString().getBytes("utf-8");
			byte[] footer = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");

			conn.setRequestProperty("Content-Length", header.length + imgFile.length() + footer.length + "");
			conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

			OutputStream out = new DataOutputStream(conn.getOutputStream());
			out.write(header);
			DataInputStream in = new DataInputStream(new FileInputStream(imgFile));
			int bytes = 0;
			byte[] bufferOut = new byte[1024];
			while ((bytes = in.read(bufferOut)) != -1) {
				out.write(bufferOut, 0, bytes);
			}
			in.close();
			out.write(footer);
			out.flush();
			out.close();

			// 读取返回数据
			buf = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				buf.append(line).append("\n");
			}
			reader.close();
			reader = null;
			JSONObject result = new JSONObject(buf.toString());
			if (result.has("errcode") && (Integer) result.get("errcode") != 0) {
				throw LOG.err(result.get("errcode").toString(), result.get("errmsg") + "");
			}
			return result;
		} catch (ConnectException ce) {
			throw LOG.err("timeout", "微信服务器连接超时");
		} catch (Exception e) {
			throw LOG.err(e);
		} finally {
			if (conn != null) {
				conn.disconnect();
				conn = null;
			}
		}
		// return jsonObject;
	}

	// 拼出一个用户请求网页授权的地址
	/**
	 * @deprecated
	 * @param account
	 * @param param
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws JSONException
	 */
	public static String authorizeCode(WxAccount account, JSONObject param)
			throws UnsupportedEncodingException, JSONException {
		param.put("account", account.getPkey());
		String request_uri = ServletActionContext.getServletContext().getInitParameter("webPath")
				+ "/expand_wx_Wechat_expand?param=" + Crypto.encrypt(param.toString());
		request_uri = URLEncoder.encode(
				request_uri.replace("+", "%2B").replace("*", "%2A").replace("~", "%7E").replace("#", "%23"), "UTF-8");
		System.err.println(request_uri);
		return WEB_OAUTH_URL.replaceAll("APPID", "wx23ec4f6df9a6a61d").replaceAll("REDIRECT_URI", request_uri)
				.replaceAll("SCOPE", "snsapi_base").replaceAll("STATE", "1");
	}

	/**
	 * @deprecated
	 * @param accountNum
	 * @param param
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws JSONException
	 */
	public static String authorizeCode(String accountNum, JSONObject param)
			throws UnsupportedEncodingException, JSONException {
		String where = Idu.sqlString("{0}=?", WxAccount.T.ACCOUNT_NUMBER);
		WxAccount account = BeanBase.list(WxAccount.class, where, false, accountNum).get(0);
		return authorizeCode(account, param);
	}

	/**
	 * @deprecated
	 * @param appID
	 * @param secret
	 * @param code
	 * @return
	 * @throws JSONException
	 */
	// 通过appid,secret,code换取用户openid
	public static String authorizeOpenid(String appID, String secret, String code) throws JSONException {
		return authorize(appID, secret, code).getString("openid");
	}

	/**
	 * @deprecated
	 * @param appID
	 * @param secret
	 * @param code
	 * @return
	 * @throws JSONException
	 */
	// 返回微信获取的参数
	public static JSONObject authorize(String appID, String secret, String code) throws JSONException {
		String requestUrl = WEB_OAUTH_ACCESSTOKEN_URL.replaceAll("APPID", appID).replaceAll("SECRET", secret)
				.replaceAll("CODE", code);
		JSONObject result = httpRequest(requestUrl, "POST", null);
		if (result.has("errcode")) {
			throw LOG.err("" + result.get("errcode"), result.getString("errmsg"));
		}
		return result;
	}

	/**
	 * 编码
	 * @deprecated
	 * @param bstr
	 * @return String
	 */
	public static String encode(byte[] bstr) {
		return new sun.misc.BASE64Encoder().encode(bstr);
	}

	/**
	 * 解码
	 * @deprecated
	 * @param str
	 * @return string
	 */
	public static byte[] decode(String str) {

		byte[] bt = null;
		try {
			sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
			bt = decoder.decodeBuffer(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bt;

	}

	/**
	 * 将微信的10位的时间转换成java中的Date对象
	 */
	public static Date tranDate(long time) {
		return new Date(Long.parseLong(time + "000"));
	}

	/**
	 * 将java中的Date对象转换为微信的10位时间
	 */
	public static long tranDate(Date date) {
		return Long.parseLong((date.getTime() + "").substring(0, 10));
	}

	public static byte[] readInputStream(InputStream inStream) throws IOException {
		ByteArrayOutputStream outstream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024]; // 用数据装
		int len = -1;
		while ((len = inStream.read(buffer)) != -1) {
			outstream.write(buffer, 0, len);
		}
		outstream.close();
		inStream.close();
		return outstream.toByteArray();
	}

	/**
	 * 从微信服务器下载媒体文件
	 * 
	 * @param account
	 *            微信公众号
	 * @param mediaId
	 * @param target
	 *            目标文件
	 */
	public static void downloadMedia(WxAccount account, String mediaId, File target) {
		saveHttpImage(MEDIA_DOWNLOAD_URL.replace("ACCESS_TOKEN", WxAccountDAO.getAccessToken(account))
				.replace("MEDIA_ID", mediaId), "GET", target);
	}

	/**
	 * 产生随机字符串
	 * @param account
	 * @return
	 */
	public static String getNonceStr(WxAccount account) {
		return Crypto.encrypt(account.getJsapiTicket()).substring(0, 16);
	}
	
	public static String getJsTimestamp(WxAccount account) {
		return String.valueOf(tranDate(account.getAccessTime()));
	}

	/**
	 * 产生JsSDK签名
	 * @deprecated
	 * @param account
	 * @param url
	 * @return
	 */
	public static String getSignature(WxAccount account, String url) {
		String str = JS_SGIN_STR.replace("JSAPI_TICKET", WxAccountDAO.getJsapiTicket(account))
				.replace("NONCESTR", getNonceStr(account)).replace("TIMESTAMP", getJsTimestamp(account))
				.replace("URL", url);
		return Crypto.getSha1(str);
	}

}