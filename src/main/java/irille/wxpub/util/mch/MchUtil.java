package irille.wxpub.util.mch;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.ssl.SSLContexts;

import irille.pub.Log;
import irille.pub.PubInfs.IMsg;
import irille.wx.wx.WxAccount;

/**
 * 商户平台调用工具
 * @author yingjianhua
 *
 */
public abstract class MchUtil {
	private static final Log LOG = new Log(MchUtil.class);
	public enum Msgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
		CertErr("商户平台证书错误!"),
		;
		private String _msg;
		private Msgs(String msg) { _msg=msg; }
		public String getMsg() {return _msg; }
	} //@formatter:on
	private static final String ALLCHAR = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
	private static final String ALLNUM = "0123456789";
	private static final Random random = new Random();


	/**
	 * 生成长度为 length 的随机字符串，包含数字和大写字母
	 * @param length
	 * @return
	 */
	public static final String createRandom(int length) {
		StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {  
            sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));  
        }
        return sb.toString();
	}
	/**
	 * 生成长度为 length 的随机字符串，包含数字
	 * @param length
	 * @return
	 */
	public static final String createRandomNum(int length) {
		StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < length; i++) {  
            sb.append(ALLNUM.charAt(random.nextInt(ALLNUM.length())));  
        }  
        return sb.toString();
	}
	/**
	 * 对字符串进行MD5运算
	 * @param str
	 * @return 
	 * @throws NoSuchAlgorithmException
	 */
	public static String md5(String str) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("MD5");
		try {
			digest.update(str.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		StringBuilder md5 = new StringBuilder();
		int i;
		for(byte line:digest.digest()) {
			i = line;
			if(i < 0) {
				i+=256;
			}
			if(i < 16) {
				md5.append("0");
			}
			md5.append(Integer.toHexString(i).toUpperCase());
		}
		System.out.println(md5.toString().toUpperCase()+"   length:"+md5.toString().length());
		return md5.toString().toUpperCase();
	}
	/**
	 * 创建SSL
	 * @param account
	 * @return
	 */
	protected final static SSLConnectionSocketFactory createSSLFactory(WxAccount account) {
		try {
			String classPath = MchUtil.class.getClassLoader().getResource("").getPath();
			classPath = classPath.replace("/WEB-INF/classes", "");
			KeyStore ks = KeyStore.getInstance("PKCS12");
			FileInputStream input = new FileInputStream(classPath + account.getMchPayCert());
			char[] npassword = null;
			if(account.getMchId() != null && !account.getMchId().trim().equals("")) {
				npassword = account.getMchId().toCharArray();
			}
			ks.load(input, npassword);
			input.close();
			SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(ks, npassword).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null,SSLConnectionSocketFactory.getDefaultHostnameVerifier());
			return sslsf;
		} catch (KeyManagementException | UnrecoverableKeyException | KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e) {
			e.printStackTrace();
			throw LOG.err(Msgs.CertErr);
		}
	}

	/**
	 * 生成随机字符串
	 * @param length
	 * @return
	 */
	protected String createNonceStr(int length) {
		return createRandom(length);
	}
	/**
	 * 计算签名
	 * @param mchKey
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	protected String createSign(String mchKey) throws NoSuchAlgorithmException {
		Field[] fields = this.getClass().getDeclaredFields();
		StringBuffer buffer = new StringBuffer();
		Map<String, Object> map = new TreeMap<String, Object>();
		for(Field field:fields) {
			try {
				if(field.getAnnotation(Sendable.class) != null) {
					if(field.get(this)==null) continue;
					map.put(field.getName(), field.get(this));
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		for(String key:map.keySet()) {
			buffer.append(key+"="+map.get(key)+"&");
			
		}
		String stringSignTemp = buffer+"key="+mchKey;
		System.out.println(stringSignTemp);
		return md5(stringSignTemp);
	}
	/**
	 * 讲field转换成xml格式
	 * @return
	 */
	protected String trans2XML() {
		Field[] fields = this.getClass().getDeclaredFields();
		StringBuffer buffer = new StringBuffer();
		buffer.append("<xml>\r\n");
		for(Field field:fields) {
			System.out.println(field.getName());
			try {
				if(field.getAnnotation(Sendable.class) != null){
					if(field.get(this)==null) continue;
					buffer.append("<"+field.getName()+">");
					buffer.append(field.get(this));
					buffer.append("</"+field.getName()+">\r\n");
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		buffer.append("</xml>");
		return buffer.toString();
	}
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public @interface Sendable {
	}
}
