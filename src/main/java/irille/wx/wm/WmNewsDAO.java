package irille.wx.wm;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import irille.action.wm.WmNewsAction;
import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.PubInfs.IMsg;
import irille.pub.Str;
import irille.pub.bean.Bean;
import irille.pub.bean.BeanBase;
import irille.pub.idu.Idu;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduInsLines;
import irille.pub.idu.IduOther;
import irille.pub.idu.IduUpdLines;
import irille.pub.svr.DbPool;
import irille.pub.svr.DepConstants;
import irille.wx.wx.WxAccountDAO;
import irille.wx.wx.WxAuto;
import irille.wx.wx.WxMenu;
import irille.wx.wx.WxSubscribe;
import irille.wx.wx.WxUser;
import irille.wx.wx.Wx.OMassMsgType;
import irille.wx.wx.Wx.OSyncStatus;
import irille.wx.wx.WxSubscribe.T;
import irille.wx.wx.WxUserGroup;
import irille.wxpub.util.WeixinUtil;
import irille.wxpub.util.WxMaterialUtil;
import irille.wxpub.util.WxMaterialUtil.MaterialType;

public class WmNewsDAO implements MessageMassable{
	
	public enum Msgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
		noLineErr("图文素材不能为空"),
		cantUpd("已同步状态，不能修改"),
		overLimitErr("图文数过长"),
		accountErr("公众号错误"),
		dataErr("图文数据出错"),
		createNewsErr("创建图文素材出错"),
		wirteFileErr("{0}文件读取错误"),
		noExistsErr("图文模板不存在"),
		titleEmptyErr("群发的图文素材，标题不能为空"),
		contentEmptyErr("群发的图文素材，内容不能为空"),
		coverEmptyErr("群发的图文素材，封面不能为空"),
		syncErr("同步异常，请联系管理员"),
		unsyncErr("取消同步异常，请联系管理员"),
		timeoutErr("微信服务器连接超时");
		private String _msg;

		private Msgs(String msg) {
			_msg = msg;
		}

		public String getMsg() {
			return _msg;
		}
	} // @formatter:on

	/**
	 *  上传图文消息内的图片获取URL【订阅号与服务号认证后均可用】
	 */
	public final static String media_uploading_url = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=ACCESS_TOKEN";
	/**
	 *  上传图文消息素材【订阅号与服务号认证后均可用】
	 */
	public final static String media_uploadnews_url = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=ACCESS_TOKEN";
	/**
	 *  上传临时多媒体文件
	 */
	public final static String media_upload_url = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
	
	public static final Log LOG = new Log(WmNewsDAO.class);

	public static class Ins extends IduInsLines<Ins, WmNews, WmNews> {
		Integer account = WxAccountDAO.getByUser(getUser()).getPkey();
		Date date = new Date();

		@Override
		public void before() {
			super.before();
			List<WmNews> list = getLines();
			if (list == null || list.size() == 0) {
				throw LOG.err(Msgs.noLineErr);
			} else if (list.size() > 8) {
				throw LOG.err(Msgs.overLimitErr);
			}
			WmNews main = list.get(0);
			main.setAccount(account);
			main.stStatus(OSyncStatus.INIT);
			setB(main);
		}

		@Override
		public void after() {
			super.after();
			List<WmNews> list = getLines();
			for (int i = 1; i < list.size(); i++) {
				list.get(i).stPicUp(getB());
				list.get(i).setAccount(account);
				list.get(i).ins();
			}
			createNewsAll(getB());
		}
	}

	public static class Upd extends IduUpdLines<Upd, WmNews, WmNews> {
		Integer account = WxAccountDAO.getByUser(getUser()).getPkey();
		Date date = new Date();

		@Override
		public void before() {
			super.before();
			List<WmNews> list = getLines();
			if (list == null || list.size() == 0) {
				throw LOG.err(Msgs.noLineErr);
			} else if (list.size() > 8) {
				throw LOG.err(Msgs.overLimitErr);
			}
			WmNews main = list.get(0);
			PropertyUtils.copyProperties(main, Bean.load(WmNews.class, main.getPkey()), WmNews.T.ACCOUNT, WmNews.T.STATUS, WmNews.T.MEDIA_ID);
			
			if(main.gtStatus() != OSyncStatus.INIT) {
				throw LOG.err(Msgs.cantUpd);
			} else if (main.getAccount() != account) {
				throw LOG.err(Msgs.accountErr);
			}

			// 先将原来的下级图文删除
			String sql = Idu.sqlString("delete from {0} where {1}=?", WmNews.class, WmNews.T.PIC_UP);
			Bean.executeUpdate(sql, main.getPkey());
			setB(main);
		}

		@Override
		public void after() {
			super.after();

			// 将现在的下级图文插入到数据中
			List<WmNews> list = getLines();
			for (int i = 1; i < list.size(); i++) {
				WmNews line = list.get(i);
				line.stPicUp(getB());
				line.setAccount(account);
				line.ins();
			}
			createNewsAll(getB());
			
		}
	}

	public static class Del extends IduDel<Del, WmNews> {
		// 删除永久素材，删除meida_id，并将状态修改为已删除
		@Override
		public void valid() {
			super.valid();
			WmNewsDAO.haveBeUsed(getB());
		}
		
		@Override
		public void run() {
			WmNews bean = getB();
			if(bean.gtStatus() == OSyncStatus.SYNC) {
				try {
					WxMaterialUtil.delMaterial(WxAccountDAO.getAccessToken(Idu.getUser()), bean.getMediaId());
				} catch (JSONException e) {
					throw LOG.err(Msgs.unsyncErr);
				}
			}
			bean.setMediaId(null);
			bean.stStatus(OSyncStatus.DEL);
			bean.upd();
		}

	}

	/**
	 * 同步，将图文素材新增到微信的永久素材库中
	 * @author yingjianhua
	 * @since 2016/2/17
	 */
	public static WmNews sync(WmNews news) {
		//上传，设置media_id，并将状态修改为已同步
		if(news.gtStatus() != OSyncStatus.INIT) return news;
		try {
			List<WmNews> list = getList(news);
			for (WmNews line : list)
				valid4mass(line);
			news = list.get(0);
			JSONObject result;
			result = WxMaterialUtil.addNews(WxAccountDAO.getAccessToken(Idu.getUser()), list);
			String media_id = result.getString("media_id");
			news.setMediaId(media_id);
			news.stStatus(OSyncStatus.SYNC);
			news.upd();
			return news;
		} catch (JSONException | IOException e) {
			throw LOG.err(Msgs.syncErr);
		}
	}
	
	/**
	 * 同步，将图文素材新增到微信的永久素材库中
	 * @author yingjianhua
	 * @since 2016/2/17
	 */
	public static WmNews sync(Serializable pkey) {
		return sync(WmNews.load(WmNews.class, pkey));
	}
	
	/**
	 * 取消同步
 	 * @author yingjianhua
	 * @since 2016/2/17
	 * @return
	 */
	public static WmNews unsync(WmNews news) {
		// 判断是否被使用，包括（自动回复，自定义菜单，关注欢迎语）
	    // 删除永久素材，删除media_id，并将状态修改为未同步
		if(news.gtStatus() != OSyncStatus.SYNC) return news;
		haveBeUsed(news);
		try {
			WxMaterialUtil.delMaterial(WxAccountDAO.getAccessToken(Idu.getUser()), news.getMediaId());
			news.setMediaId(null);
			news.stStatus(OSyncStatus.INIT);
			news.upd();
		} catch (JSONException e) {
			throw LOG.err(Msgs.unsyncErr);
		}
		
		return news;
	}
	/**
	 * 取消同步
	 * @author yingjianhua
	 * @since 2016/2/18
	 * @return
	 */
	public static WmNews unsync(Serializable pkey) {
		return unsync(WmNews.load(WmNews.class, pkey));
	}
	/**
	 * 检查是否已经被使用了（包括自动回复，自定义菜单，关注欢迎语）
	 * @author yingjianhua
	 * @since 2016/2/17
	 */
	private static void haveBeUsed(WmNews news) {
		Long longPkey = Bean.gtLongPkey(news.getPkey(), WmNews.TB.getID());
		Idu.haveBeUsed(WxAuto.class, WxAuto.T.TEMPLATE, longPkey);
		Idu.haveBeUsed(WxMenu.class, WxMenu.T.TEMPLATE, longPkey);
		Idu.haveBeUsed(WxSubscribe.class, WxSubscribe.T.TEMPLATE, longPkey);
	}
	
	/**
	 * 获取多图文素材的列表，以json的格式
	 * @param pkey
	 * @return
	 * @throws Exception
	 */
	public static String load(Serializable pkey) throws Exception {
		WmNews templ = WmNews.load(WmNews.class, pkey);
		if (templ.getPicUp() != null) {
			templ = templ.gtPicUp();
		}
		if (templ.getPicUp() != null) {
			LOG.err(Msgs.dataErr);
		}
		String where = Idu.sqlString("{0}=? order by {1}", WmNews.T.PIC_UP, WmNews.T.SORT);
		List<WmNews> items = WmNews.list(WmNews.class, where, false, templ.getPkey());
		WmNewsAction action = new WmNewsAction();

		JSONArray array = new JSONArray();
		JSONObject json = action.crtJsonByBean(templ, "bean.");
		json.put("main", true);
		array.put(json);
		for (WmNews temp : items) {
			array.put(action.crtJsonByBean(temp, "bean."));
		}
		json = new JSONObject();
		json.put("success", true);
		json.put("items", array);
		json.put("count", array.length());
		return json.toString();
	}

	public static String transform2XML(WmNews main, String accountId, String openId, String createTime) {
		String where = Idu.sqlString("{0}=? order by {1}", WmNews.T.PIC_UP, WmNews.T.SORT);
		List<WmNews> items = BeanBase.list(WmNews.class, where, false, main.getPkey());
		StringBuffer result = new StringBuffer();
		result.append("<xml>");
		result.append("<ToUserName><![CDATA[" + openId + "]]></ToUserName>");
		result.append("<FromUserName><![CDATA[" + accountId + "]]></FromUserName>");
		result.append("<CreateTime>" + createTime + "</CreateTime>");
		result.append("<MsgType><![CDATA[news]]></MsgType>");
		result.append(tranform2ItemsXML(main, items, openId));
		result.append("</xml>");
		// gtLongTbObj
		return result.toString();
	}
	/**
	 * 通过title，description，picUrl，url，accountId，openId， createTime 参数生成一个单图文的xml用于回复用户信息
	 * @return
	 */
	public String transform2XML(String title, String description, String picUrl, String url, String accountId, String openId, String createTime) {
		StringBuilder result = new StringBuilder();
		result.append("<xml>");
		result.append("<ToUserName><![CDATA[" + openId + "]]></ToUserName>");
		result.append("<FromUserName><![CDATA[" + accountId + "]]></FromUserName>");
		result.append("<CreateTime>" + createTime + "</CreateTime>");
		result.append("<MsgType><![CDATA[news]]></MsgType>");
		result.append("<ArticleCount>1</ArticleCount>");
		result.append("<Articles>");
		result.append("<item>");
		result.append("<Title><![CDATA[" + title + "]]></Title>");
		result.append("<Description><![CDATA[" + description + "]]></Description>");
		result.append("<PicUrl><![CDATA[" +picUrl + "]]></PicUrl>");
		result.append("<Url><![CDATA[" + url + "]]></Url>");
		result.append("</item>");
		result.append("</Articles>");
		result.append("</xml>");
		return result.toString();
	}

	public static String transform2XML(Integer pkey, String accountId, String openId, String createTime) {
		WmNews main = BeanBase.load(WmNews.class, pkey);
		return transform2XML(main, accountId, openId, createTime);
	}

	private static String tranform2ItemsXML(WmNews main, List<WmNews> items, String openId) {
		int count;
		if (items == null)
			count = 1;
		else
			count = items.size() + 1;
		StringBuffer xitems = new StringBuffer();
		xitems.append("<ArticleCount>" + count + "</ArticleCount>");
		xitems.append("<Articles>");
		xitems.append(transform2ItemXML(main, openId));
		for (WmNews item : items) {
			xitems.append(transform2ItemXML(item, openId));
		}
		xitems.append("</Articles>");
		return xitems.toString();
	}

	private static String transform2ItemXML(WmNews item, String openId) {
		String webPath = ServletActionContext.getServletContext().getInitParameter("webPath");
		StringBuffer xitem = new StringBuffer();
		xitem.append("<item>");
		xitem.append("<Title><![CDATA[" + item.getTitle() + "]]></Title>");
		xitem.append(
				item.getSummary() == null ? "" : "<Description><![CDATA[" + item.getSummary() + "]]></Description>");
		xitem.append("<PicUrl><![CDATA[" + webPath + DepConstants.FILE_SEPA + item.getImageUrl() + "]]></PicUrl>");
		if(item.gtType() == Wm.ONewsType.BASE) {
			String url = webPath + ServletActionContext.getServletContext().getInitParameter("newsPath") + DepConstants.FILE_SEPA + item.getPkey()
					+ ".html";
			xitem.append("<Url><![CDATA[" + url + "]]></Url>");
		} else if(item.gtType() == Wm.ONewsType.URL && item.getUrl()!=null) {
			xitem.append("<Url><![CDATA[" + item.getUrl() + "]]></Url>");
		} else if(item.gtType() == Wm.ONewsType.EXP && item.getExp()!=null) {
			Bean exp = item.gtExp();
			String className = exp.getClass().getName();
			String[] sarg = className.split("\\.");
			String url = webPath + DepConstants.FILE_SEPA + "expand_"+sarg[2]+"_"+sarg[3]+"_show?pkey="+exp.getPkey();
			xitem.append("<Url><![CDATA[" + url + "]]></Url>");
		} 
		xitem.append("</item>");
		return xitem.toString();
	}
	
	public static void createNewsAll(WmNews main) {
		String where = Idu.sqlString("{0}=?", WmNews.T.PIC_UP);
		List<WmNews> list = BeanBase.list(WmNews.class, where, false, main.getPkey());
		if (main.getUrl() == null || main.getUrl().equals("")) {
			createNews(main);
		}
		for (WmNews line : list) {
			if (line.getUrl() == null || line.getUrl().equals("")) {
				createNews(line);
			}
		}
	}

	private static void createNews(WmNews template) {
		String classPath = WmNewsDAO.class.getClassLoader().getResource("/").getPath();
		// String newsPath = "/uploads/news";
		String newsPath = ServletActionContext.getServletContext().getInitParameter("newsPath");
		classPath = classPath.replace("/WEB-INF/classes", newsPath);
		BufferedReader reader = null;
		try {
			String title = template.getTitle();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String createTime = sdf.format(new Date());
			String author = template.getAuthor();
			String imageUrl = template.getImageUrl();
			String content = template.getContent();
			reader = new BufferedReader((new InputStreamReader(new FileInputStream(getBaseHtmlPath()), "utf-8")));
			StringBuffer buf = new StringBuffer();
			String s = "";
			while ((s = reader.readLine()) != null) {
				buf.append(s).append("\r\n");
			}
			String str = buf.toString();
			str = str.replaceAll("【0】", title == null ? "" : title);
			str = str.replaceAll("【1】", createTime == null ? "" : createTime);
			str = str.replaceAll("【2】", author == null ? "" : author);
			str = str.replaceAll("【3】", imageUrl == null ? "" : imageUrl);
			str = str.replaceAll("【4】", content == null ? "" : content);

			writeStr(getNewsPath() + "/" + template.getPkey() + ".html", str);
		} catch (IOException e) {
			throw LOG.err(Msgs.createNewsErr);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static void writeStr(String fileName, String str) {
		File file = new File(fileName);
		new File(file.getParent()).mkdirs(); // 建立上级目录
		if (file.exists()) {
			file.delete();
		}
		try {
			OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8");
			// FileWriter writer = new FileWriter(new OutputStreamWriter(new
			// FileOutputStream(file), ""));
			writer.write(str);
			writer.close();
		} catch (IOException e) {
			throw LOG.err(Msgs.wirteFileErr, fileName);
		}
	}

	private static String getNewsPath() {
		String classPath = WmNewsDAO.class.getClassLoader().getResource("/").getPath();
		// String newsPath = "/uploads/news";
		String newsPath = ServletActionContext.getServletContext().getInitParameter("newsPath");
		classPath = classPath.replace("/WEB-INF/classes", newsPath);
		return classPath;
	}
	
	/**
	 * 上传图文消息内的图片获取URL
	 * 
	 */
	public static void main(String[] args) {
		System.out.println(getBaseHtmlPath());
	}
	/**
	 * 获取base.html文件的绝对路径
	 * */
	private static String getBaseHtmlPath() {
		String classPath = WmNewsDAO.class.getClassLoader().getResource("").getPath();
		return classPath.replace("/WEB-INF/classes/", "/wm/news/base.html");
	}

	public static String uploadNews(WmNews template) {
		// 根据多级图文的其中一级，取出整个多图文的列表，按排序号排列
		List<WmNews> list = getList(template);
		// TODO 校验 有些字段不能为空
		for (WmNews line : list) {
			valid4mass(line);
		}

		try {
			// 将图文转换为JSON格式
			JSONObject json = new JSONObject();
			JSONArray array = new JSONArray();
			String accessToken = WxAccountDAO.getAccessToken(Idu.getUser());
			array.put(template2json(template, accessToken));
			for (WmNews line : list) {
				array.put(template2json(line, accessToken));
			}
			json.put("articles", array);

			// 上传图文，并返回media_id
			String requestUrl = media_uploadnews_url.replaceAll("ACCESS_TOKEN", accessToken);
			JSONObject result = WeixinUtil.httpRequest(requestUrl, "POST", json.toString());
			if (result.has("errcode")) {
				throw LOG.err("" + result.get("errcode"), result.get("errmsg") + "");
			}
			return result.getString("media_id");
		} catch (Exception e) {
			throw LOG.err(e);
		}
	}

	/**
	 * 通过多图文的其中一级，获取整个多图文列表
	 */
	private static List<WmNews> getList(WmNews main) {
		if (main.getPicUp() != null)
			main = main.gtPicUp();
		String where = Idu.sqlString("{0}=? order by {1}", WmNews.T.PIC_UP, WmNews.T.SORT);
		List<WmNews> subList = WmNews.list(WmNews.class, where, true, main.getPkey());
		List<WmNews> list = new ArrayList<WmNews>();
		list.add(main);
		list.addAll(subList);
		return list;
	}

	/**
	 * 对群发图文的格式进行校验
	 * 1.封面图片不能为空
	 * 2.标题不能为空
	 * 3.正文不能为空
	 * @param template
	 */
	private static void valid4mass(WmNews template) {
		if (template.getImageUrl() == null)
			throw LOG.err(Msgs.coverEmptyErr);
		if (template.getTitle() == null)
			throw LOG.err(Msgs.titleEmptyErr);
		if (template.getContent() == null)
			throw LOG.err(Msgs.contentEmptyErr);
	}

	/**
	 * 将List<WmNews>转换为Json格式
	 * @throws IOException 
	 */
	public static JSONObject transform2Json(List<WmNews> list, String accessToken) throws JSONException, IOException {
		JSONArray array = new JSONArray();
		for (WmNews line : list) {
			array.put(transform2Json(line, accessToken));
		}
		JSONObject json = new JSONObject();
		json.put("articles", array);
		return json;
	}

	/**
	 * 将WmNews转换为Json格式
	 * 
	 * @throws JSONException
	 * @throws IOException
	 */
	public static JSONObject transform2Json(WmNews article, String accessToken)
			throws JSONException, IOException {
		/**
		 * { "articles": [{ "title": TITLE, "thumb_media_id": THUMB_MEDIA_ID,
		 * "author": AUTHOR, "digest": DIGEST, "show_cover_pic":
		 * SHOW_COVER_PIC(0 / 1), "content": CONTENT, "content_source_url":
		 * CONTENT_SOURCE_URL }, //若新增的是多图文素材，则此处应有几段articles结构，最多8段 ] }
		 */

		JSONObject json = new JSONObject();
		json.put("title", article.getTitle());
		json.put("thumb_media_id", WxMaterialUtil.addMaterial(accessToken, article.getImageUrl(), MaterialType.IMAGE)
				.getString("media_id"));
		json.put("author", article.getAuthor());
		json.put("digest", article.getSummary());
		json.put("show_cover_pic", article.getShowCoverPic());
		// TODO 在百度的富文本编辑器中编辑的内容中的图片如果是本地上传的，就会是一个项目的相对路径，需要转换为带上域名的地址
		String content = article.getContent();
		System.out.println("");
		// System.out.println(content);
		Pattern pa = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
		Date d_start = new Date();
		Matcher matcher = pa.matcher(content);
		while (matcher.find()) {
			Date d1 = new Date();
			String old_src = matcher.group(1);
			System.out.println(old_src);
			Date d2 = new Date();
			String new_src = WxMaterialUtil.uploadImg(accessToken, old_src, getUploadPath(true));
			System.out.println(new_src);
			Date d3 = new Date();
			content = content.replaceAll(matcher.group(), matcher.group().replaceAll(old_src, new_src));
			Date d4 = new Date();
			System.out.println("匹配用时：" + (d1.getTime() - d2.getTime()));
			System.out.println("上传用时：" + (d2.getTime() - d3.getTime()));
			System.out.println("替换用时：" + (d3.getTime() - d4.getTime()));
		}
		Date d_end = new Date();
		// System.out.println(content);
		System.out.println("上传正文总共用时：" + (d_start.getTime() - d_end.getTime()));
		json.put("content", content);
		json.put("content_source_url", article.getRelUrl());
		return json;
	}

	private static JSONObject template2json(WmNews template, String accessToken)
			throws IOException, JSONException {
		JSONObject json = new JSONObject();
		json.put("thumb_media_id", uploadImg(template.getImageUrl(), accessToken));
		json.put("author", template.getAuthor());
		json.put("title", template.getTitle());
		json.put("content_source_url", template.getRelUrl());
		String content = template.getContent();
		Pattern pa = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
		Matcher matcher = pa.matcher(content);
		while (matcher.find()) {
			String old_src = matcher.group(1);
			String new_src = WxMaterialUtil.uploadImg(accessToken, old_src, getUploadPath(true));
			content = content.replaceAll(matcher.group(), matcher.group().replaceAll(old_src, new_src));
		}
		json.put("content", content);
		json.put("digest", template.getSummary());
		json.put("show_cover_pic", "1");
		return json;
	}

	// 上传临时素材
	public static String uploadImg(String imgPath, String accessToken) {
		File imgFile = new File(WmNewsDAO.class.getClassLoader().getResource("").getPath()
				.replaceAll("WEB-INF/classes/", imgPath));
		JSONObject jsonObject = null;
		String requestUrl = media_upload_url.replaceAll("ACCESS_TOKEN", accessToken).replaceAll("TYPE", "image");
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
			conn.setRequestMethod("POST");
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
			jsonObject = new JSONObject(buf.toString()); // TODO 未测试是否正确
			return jsonObject.getString("media_id");
		} catch (ConnectException ce) {
			throw LOG.err(Msgs.timeoutErr);
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

	private static String getUploadPath(boolean real) {
		String path = ServletActionContext.getServletContext().getInitParameter("uploadPath");
		if (Str.isEmpty(path)) {
			path = "/uploads";
		}
		if (real) {
			return ServletActionContext.getServletContext().getRealPath("/") + path;
		} else {
			return path;
		}
	}
	@Override
	public OMassMsgType getMessageType() {
		return OMassMsgType.ARTICLE;
	}

	@Override
	public String transform4Preview(Integer wxUserPkey, Integer tempPkey) throws JSONException {
		JSONObject json = new JSONObject();
		json.put("touser", Bean.load(WxUser.class, wxUserPkey).getOpenId());
		json.put("mpnews", new JSONObject().put("media_id", Bean.load(WmNews.class, tempPkey).getMediaId()));
		json.put("msgtype", "mpnews");
		return json.toString();
	}

	@Override
	public String transform4Mass(boolean isToAll, Integer wxGroupPkey, Integer tempPkey) throws JSONException {
		JSONObject json = new JSONObject();
		JSONObject filter = new JSONObject();
		filter.put("is_to_all", isToAll);
		if(!isToAll) {
			filter.put("group_id", Bean.load(WxUserGroup.class, wxGroupPkey).getWxid());
		}
		json.put("filter", filter);
		json.put("mpnews", new JSONObject().put("media_id", Bean.load(WmNews.class, tempPkey).getMediaId()));
		json.put("msgtype", "mpnews");
		return json.toString();
	}
}
