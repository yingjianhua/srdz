package irille.wxpub.util;

import irille.action.ActionUpload;
import irille.pub.Log;
import irille.pub.uploads.FileSystemSaverNew;
import irille.pub.uploads.Photo;
import irille.wx.wm.WmNews;
import irille.wx.wm.WmNewsDAO;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 微信素材管理工具
 */
public class WxMaterialUtil {

	/**
	 * 上传图文消息内的图片获取URL【订阅号与服务号认证后均可用】
	 */
	public final static String media_uploadimg_url = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=ACCESS_TOKEN";
	/**
	 * 上传图文消息素材【订阅号与服务号认证后均可用】
	 */
	public final static String media_uploadnews_url = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=ACCESS_TOKEN";
	/**
	 * 新增临时素材
	 */
	public final static String media_upload_url = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
	/**
	 * 新增永久图文素材
	 */
	public final static String material_add_news_url = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=ACCESS_TOKEN";
	/**
	 * 新增其他类型永久素材
	 */
	public final static String material_add_material_url = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN";
	/**
	 * 获取永久素材
	 */
	public final static String material_get_material_url = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=ACCESS_TOKEN";
	/**
	 * 删除永久素材
	 */
	public final static String material_del_material_url = "https://api.weixin.qq.com/cgi-bin/material/del_material?access_token=ACCESS_TOKEN";
	/**
	 * 修改永久图文素材
	 */
	public final static String material_update_news_url = "https://api.weixin.qq.com/cgi-bin/material/update_news?access_token=ACCESS_TOKEN";
	/**
	 * 获取素材列表
	 */
	public final static String material_batchget_material_url = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN";
	/**
	 * 获取视频用于群发的media_id
	 */
	public final static String media_uploadvideo_url = "https://api.weixin.qq.com/cgi-bin/media/uploadvideo?access_token=ACCESS_TOKEN";
	
	private static final String accessTokenTmp = "dbQcs4rkfl_YVFd9yaacEvVbo9oFd1m-mA2EmcoIO82_yL42tuPWaGckqtmEgXmKC0ztVoJ0s-1g_ux_Vq18f2g9jB9dKuluMrgMCTFEcYYKUSgABAQAY";
	public static final Log LOG = new Log(WxMaterialUtil.class);

	public enum MaterialType {
		IMAGE, MUSIC, VOICE, VIDEO, NEWS, THUMB
	}

	/**
	 * 上传图文消息内的图片获取URL
	 * 
	 * @param accessToken
	 *            请求凭证
	 * @param src
	 *            img标签中的src属性的值
	 * @param rootPath
	 *            若图片为网络图片，则rootPath表示图片的保存路径的根路径
	 * @throws IOException
	 * @throws JSONException
	 */
	public static String uploadImg(String accessToken, String src,
			String rootPath) throws IOException, JSONException {
		String wxsrc = src;
		System.out.println("src:" + src);
		File imgFile = null;
		if(src.startsWith("../")) {
			src = WxMaterialUtil.class.getClassLoader().getResource("/").getPath().replace("WEB-INF/classes", src.substring(3));
			imgFile = new File(src);
		} else {
			// 若文件不存在，用URL去解析，若没有报错，并且content是一个图片文件，表示这是一个网络图片
			String url = FileSystemSaverNew.createNewPhotoURI(rootPath, "jpg",
					"wx");
			String imageURL = rootPath + File.separator + url;
			String filename = imageURL.substring(imageURL
					.lastIndexOf(File.separator) + 1);
			System.out.println("imageURL:" + imageURL);
			int i=3;
			while(!WeixinUtil.requestImage(src, "GET", imageURL)) {
				if(i--<0) throw LOG.err("networkErr","网络异常，请重试");
			}
			imgFile = new File(imageURL);
//			File imageFile = new File(imageURL);
//			// 通过项目上传文件的保存方式转存一遍
//			Photo photo = new ActionUpload().saveImage(imageFile, filename,
//					"wm", 0, 0);
//			// 最后把原始文件删除
//			System.out.println("删除原始文件:" + imageFile.delete());
//			System.out.println("previewURL:" + photo.getPreviewURL());
//			wxsrc = photo.getPreviewURL();
//			imgFile = new File(rootPath + File.separator + wxsrc);
		}
		System.out.println("wxsrc:" + wxsrc);
		String requestUrl = media_uploadimg_url.replaceAll("ACCESS_TOKEN",
				accessToken);
		JSONObject result = WeixinUtil.uploadImg(requestUrl, "POST", imgFile);
		System.out.println("result:" + result);
		wxsrc = result.getString("url");
		System.out.println("wxsrc:" + wxsrc);
		return wxsrc;
	}

	/**
	 *  上传临时素材
	 * @param accessToken
	 * @param imgPath
	 * @param type
	 * @return 正确情况下的返回JSON数据包结果如：{"type":"TYPE","media_id":"MEDIA_ID","created_at":123456789}
	 * @throws IOException
	 */
	public static JSONObject upload(String accessToken, String imgPath, MaterialType type) {
		try {
			String sType = null;
			switch (type) {
			case IMAGE : {
				sType = "image";
				break;
			}
			case VOICE : {
				sType = "voice";
				break;
			}
			case VIDEO : {
				sType = "video";
				break;
			}
			case THUMB : {
				sType = "thumb";
				break;
			}
			default : {
				
			}
			}
			String requestUrl = media_upload_url.replace("ACCESS_TOKEN", accessToken).replace("TYPE", sType);
			
			File file_media = new File(WxMaterialUtil.class.getClassLoader().getResource("").getPath().replaceAll("WEB-INF/classes/", imgPath));
			Map<String, Object> map_media = new HashMap<String, Object>();
			map_media.put("media", file_media);

			List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();
			params.add(map_media);

			JSONObject result = new JSONObject(WeixinUtil.httpRequestPost(requestUrl, params));

			if (result.has("errcode"))
				throw LOG.err("" + result.get("errcode"),"" + result.get("errmsg"));
			return result;
		} catch (JSONException | IOException e) {
			throw LOG.err("err", "素材上传出错");
		}
	}

	/**
	 * 新增其他类型永久素材 
	 * 不支持视频素材，新增视频素材请见 addVideo()
	 * 不支持图文素材，新增图文素材请见 addNews()
	 * @throws JSONException
	 */
	public static JSONObject addMaterial(String accessToken, String path, MaterialType type) throws JSONException {
		JSONObject result = null;
		switch (type) {
		case IMAGE: {
			result = addImage(accessToken, path);
			break;
		}
		case THUMB: {
			result = addImage(accessToken, path);
			break;
		}
		case VOICE: {
			result = addImage(accessToken, path);
			break;
		}
		// TODO
		}
		if (result.has("errcode") && (Integer) result.get("errcode") != 0) {
			throw LOG.err(result.get("errcode").toString(),
					result.get("errmsg") + "");
		}
		return result;
	}

	/**
	 * 新增永久图文素材
	 * 
	 * @throws JSONException
	 * @throws IOException
	 */
	public static JSONObject addNews(String accessToken, List<WmNews> list) throws JSONException, IOException {
		JSONObject result = null;
		String requestUrl = material_add_news_url.replaceAll("ACCESS_TOKEN", accessToken);
		JSONObject json = WmNewsDAO.transform2Json(list, accessToken);
		result = WeixinUtil.httpRequest(requestUrl, "POST", json.toString());
		if (result.has("errcode") && (Integer) result.get("errcode") != 0) {
			throw LOG.err(result.get("errcode").toString(), result.get("errmsg") + "");
		}
		return result;
	}

	/**
	 * 新增永久图片素材
	 */
	private static JSONObject addImage(String accessToken, String imgPath) {
		try {
			String requestUrl = material_add_material_url.replace("ACCESS_TOKEN", accessToken);
			
			File file_media = new File(WxMaterialUtil.class.getClassLoader().getResource("").getPath().replaceAll("WEB-INF/classes/", imgPath));
			Map<String, Object> map_media = new HashMap<String, Object>();
			map_media.put("media", file_media);

			List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();
			params.add(map_media);

			JSONObject result = new JSONObject(WeixinUtil.httpRequestPost(requestUrl, params));

			if (result.has("errcode"))
				throw LOG.err("" + result.get("errcode"),"" + result.get("errmsg"));
			return result;
		} catch (ConnectException ce) {
			throw LOG.err("timeout", "微信服务器连接超时");
		} catch (Exception e) {
			throw LOG.err(e);
		}
	}
	
	/**
	 * 新增永久视频素材
	 * 
	 * @throws JSONException
	 * @throws IOException
	 */
	public static JSONObject addVideo(String accessToken, String path, String title, String introduction) {
		try {
			String requestUrl = material_add_material_url.replace("ACCESS_TOKEN", accessToken);
			//String requestUrl = media_upload_url.replace("ACCESS_TOKEN", accessToken).replace("TYPE", "video");

			File file_media = new File(WxMaterialUtil.class.getClassLoader().getResource("").getPath().replaceAll("WEB-INF/classes/", path));
			Map<String, Object> map_media = new HashMap<String, Object>();
			map_media.put("media", file_media);

			Map<String, Object> map_description = new HashMap<String, Object>();
			map_description.put("description", new JSONObject().put("title", title).put("introduction", introduction).toString());

			List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();
			params.add(map_media);
			params.add(map_description);

			JSONObject result = new JSONObject(WeixinUtil.httpRequestPost(requestUrl, params));

			if (result.has("errcode"))
				throw LOG.err("" + result.get("errcode"), "" + result.get("errmsg"));

			return result;
		} catch (JSONException e) {
			throw LOG.err(e);
		} catch (IOException e) {
			throw LOG.err(e);
		}
	}

	/**
	 * 新增永久图文素材
	 * 
	 * @deprecated 弃用了
	 */
	// public static JSONObject addNewsTemp(String accessToken, List<WxArticle>
	// list) {
	// try {
	// JSONObject result = null;
	// String requestUrl = material_add_news_url.replaceAll("ACCESS_TOKEN",
	// accessToken);
	// JSONObject json = WxArticleDAO.transform2Json(list);
	// result = WeixinUtil.httpRequest(requestUrl, "POST", json.toString());
	// if (result.has("errcode") && (Integer) result.get("errcode") != 0) {
	// throw LOG.err(result.get("errcode").toString(), result.get("errmsg") +
	// "");
	// }
	// return result;
	// } catch (JSONException e) {
	// e.printStackTrace();
	// }
	// return null;
	// }

	/**
	 * 获取永久素材 素材的类型，图片（image）、语音 （voice）
	 * 
	 * @param accessToken
	 *            请求凭证
	 * @param media_id
	 *            media_id
	 * @param rootPath
	 *            素材文件存储的根路径，根路径下的相对路径会自动生成
	 * @return 获取素材保存在项目中的相对路径
	 */
	public static String getMaterial(String accessToken, String media_id,
			String rootPath) {
		try {
			String requestUrl = material_get_material_url.replaceAll(
					"ACCESS_TOKEN", accessToken);
			JSONObject json = new JSONObject().put("media_id", media_id);
			String url = FileSystemSaverNew.createNewPhotoURI(rootPath, "jpg",
					"wm");
			String imageURL = rootPath + File.separator + url;
			String filename = imageURL.substring(imageURL
					.lastIndexOf(File.separator) + 1);
			System.out.println("imageURL:" + imageURL);
			WeixinUtil.requestMaterial(requestUrl, "POST", json.toString(),
					imageURL);
			File imageFile = new File(imageURL);
			// 通过项目上传文件的保存方式转存一遍
			Photo photo = new ActionUpload().saveImage(imageFile, filename,
					"wm", 0, 0);
			// 最后把原始文件删除
			System.out.println("删除原始文件:" + imageFile.delete());
			System.out.println("previewURL:" + photo.getPreviewURL());
			return photo.getPreviewURL();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取永久素材 素材的类型，视频（video）、图文（news）
	 * 
	 * @param accessToken
	 *            请求凭证
	 * @param media_id
	 *            media_id
	 */
	public static JSONObject getMaterial(String accessToken, String media_id) {
		String requestUrl = material_get_material_url.replaceAll(
				"ACCESS_TOKEN", accessToken);
		JSONObject json = new JSONObject();
		try {
			json.put("media_id", media_id);
			JSONObject result = WeixinUtil.httpRequest(requestUrl, "POST",
					json.toString());
			if (result.has("errcode") && (Integer) result.get("errcode") != 0) {
				throw LOG.err(result.get("errcode").toString(),
						result.get("errmsg") + "");
			}
			return result;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除公众平台上素材库中的永久素材
	 * @throws JSONException 
	 */
	public static void delMaterial(String accessToken, String mediaId) throws JSONException {
		String requestUrl = material_del_material_url.replaceAll("ACCESS_TOKEN", accessToken);
		JSONObject json = new JSONObject();
		json.put("media_id", mediaId);
		JSONObject result = WeixinUtil.httpRequest(requestUrl, "POST", json.toString());
		if (result.has("errcode") && (Integer) result.get("errcode") != 0) {
			throw LOG.err(result.get("errcode").toString(), result.get("errmsg") + "");
		}
	}

	/**
	 * 修改永久图文素材
	 */
	// public static void updateNews(String accessToken, String media_id, int
	// index, WxArticle article) {
	// String requestUrl = material_update_news_url.replaceAll("ACCESS_TOKEN",
	// accessToken);
	// JSONObject json = new JSONObject();
	// try {
	// json.put("media_id", media_id);
	// json.put("index", index);
	// json.put("articles", WxArticleDAO.transform2Json(article));
	// JSONObject result = WeixinUtil.httpRequest(requestUrl, "POST",
	// json.toString());
	// if (result.has("errcode") && (Integer) result.get("errcode") != 0) {
	// throw LOG.err(result.get("errcode").toString(), result.get("errmsg") +
	// "");
	// }
	// } catch (JSONException e) {
	// e.printStackTrace();
	// }
	// }

	/**
	 * 一次获取所有素材
	 * 
	 * @param accessToken
	 *            请求凭证
	 * @param type
	 *            素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news）
	 * @param set_skip
	 *            需要略过的media_id的集合
	 * @throws JSONException
	 */
	public static Set<JSONObject> batchget(String accessToken,
			MaterialType type, Set<String> set_skip) {
		Set<JSONObject> set_all = batchget(accessToken, type);
		Set<JSONObject> set_need = new HashSet<JSONObject>();
		for (JSONObject line : set_all) {
			try {
				if (!set_skip.contains(line.getString("media_id"))) {
					set_need.add(line);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return set_need;
	}

	/**
	 * 一次获取所有素材
	 */
	public static Set<JSONObject> batchget(String accessToken, MaterialType type) {
		Set<JSONObject> set = new HashSet<JSONObject>();
		int offset = 0;
		int total_count = 0;
		int item_count = 0;
		do {
			try {
				JSONObject result = batchget(accessToken, type, offset, 20);
				total_count = result.getInt("total_count");
				item_count = result.getInt("item_count");
				JSONArray item = result.getJSONArray("item");
				for (int i = 0; i < item.length(); i++) {
					set.add(item.getJSONObject(i));
				}
				offset += item_count;
			} catch (JSONException e) {
				e.printStackTrace();
			}

		} while (offset < total_count);
		return set;
	}

	/**
	 * 获取素材列表
	 * 
	 * @param accessToken
	 *            请求凭证
	 * @param type
	 *            素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news）
	 * @param offset
	 *            从全部素材的该偏移位置开始返回，0表示从第一个素材 返回
	 * @param count
	 *            取值在1到20之间
	 * @throws JSONException
	 */
	public static JSONObject batchget(String accessToken, MaterialType type,
			int offset, int count) throws JSONException {
		String requestUrl = material_batchget_material_url.replaceAll(
				"ACCESS_TOKEN", accessToken);
		JSONObject json = new JSONObject();
		json.put("offset", offset);
		json.put("count", count);
		switch (type) {
		case IMAGE: {
			json.put("type", "image");
			break;
		}
		case VIDEO: {
			json.put("type", "video");
			break;
		}
		case VOICE: {
			json.put("type", "voice");
			break;
		}
		case NEWS: {
			json.put("type", "news");
			break;
		}
		default: {
			throw LOG.err("type", "不支持的素材类型");
		}
		}
		JSONObject result = WeixinUtil.httpRequest(requestUrl, "POST",
				json.toString());
		if (result.has("errcode") && (Integer) result.get("errcode") != 0) {
			throw LOG.err(result.get("errcode").toString(),
					result.get("errmsg") + "");
		}
		return result;
	}
	/**
	 * 用于群发的media_id需要通过此接口重新获得
	 * @param accessToken
	 * @param media_id 基础支持中获取的media_id
	 * @param title 视频的标题
	 * @param description 视频的描述
	 * @return 格式如 ： {
					  "type":"video",
					  "media_id":"IhdaAQXuvJtGzwwc0abfXnzeezfO0NgPK6AQYShD8RQYMTtfzbLdBIQkQziv2XJc",
					  "created_at":1398848981
					}
	 * @throws JSONException
	 */
	public static JSONObject uploadvideo(String accessToken, String media_id, String title, String description) throws JSONException {
		JSONObject json = new JSONObject();
		json.put("media_id", media_id);
		json.put("title", title);
		json.put("description", description);
		String requestUrl = media_uploadvideo_url.replace("ACCESS_TOKEN", accessToken);
		JSONObject result = WeixinUtil.httpRequest(requestUrl, "POST", json.toString());
		if (result.has("errcode") && (Integer) result.get("errcode") != 0) {
			throw LOG.err(result.get("errcode").toString(),
					result.get("errmsg") + "");
		}
		return result;
	}
	
}
