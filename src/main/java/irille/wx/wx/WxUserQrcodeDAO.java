package irille.wx.wx;

import irille.pub.Log;
import irille.pub.PubInfs.IMsg;
import irille.pub.Str;
import irille.pub.bean.Bean;
import irille.pub.bean.BeanBase;
import irille.pub.idu.Idu;
import irille.pub.svr.Svr;
import irille.wxpub.util.WeixinUtil;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 修改备注功能 -- 只能选择一个操作
 * 加入黑名单功能 -- 可以多选择操作
 * 组调整功能 -- 可以多选择操作
 * 微信同步功能 -- 定时同步、手动同步
 */
public class WxUserQrcodeDAO {
	public enum Msgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
		timeOutErr("同步超时"),
		;
		private String _msg;
		private Msgs(String msg) { _msg=msg; }
		public String getMsg() {return _msg; }
	} //@formatter:on
	public static final Log LOG = new Log(WxUserQrcodeDAO.class);

	//新增专属二维码
	public static void insQrcode(WxUser user) {
		
	}
	//更新专属二维码
	public static void updQrcode(WxUser user) {
		
	}
	//若专属二维码不存在，则新增；若存在，则更新
	public static void createQrcode(WxUser user) {
		
	}
	
}
