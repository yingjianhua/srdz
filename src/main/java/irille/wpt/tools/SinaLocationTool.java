package irille.wpt.tools;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import irille.wxpub.util.HttpRequestUtil;

public class SinaLocationTool {
	
	public static JSONObject findLocationByIp(String ip) {
		JSONObject jsonResult = null;
		try {
			String stringResult = HttpRequestUtil.httpRequestPost("http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js&ip="+ip);
			//格式如下：
			//	var remote_ip_info = {"ret":1,"start":-1,"end":-1,"country":"\u4e2d\u56fd","province":"\u6d59\u6c5f","city":"\u6e29\u5dde","district":"","isp":"","type":"","desc":""};
			int startIndex = stringResult.indexOf("{");
			int endIndex = stringResult.lastIndexOf("}");
			if(startIndex != -1 && endIndex != -1) {
				stringResult = stringResult.substring(startIndex, endIndex + 1);
				jsonResult = new JSONObject(stringResult);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonResult;
	}
	private static String findLocationStringByIp(String ip, String map) {
		String result = null;
		try {
			JSONObject jsonResult = findLocationByIp(ip);
			result = jsonResult.getString(map);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}
			
	public static String findCityByIp(String ip) {
		return findLocationStringByIp(ip, "city");
	}
	public static String findProvinceByIp(String ip) {
		return findLocationStringByIp(ip, "province");
	}
	public static String findCountryByIp(String ip) {
		return findLocationStringByIp(ip, "country");
	}
}
