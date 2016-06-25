package irille.wx.wx;

import irille.core.prv.PrvRoleAct;
import irille.core.sys.SysDept;
import irille.core.sys.SysModule;
import irille.core.sys.SysUser;
import irille.pub.Log;
import irille.pub.bean.PackageBase;
import irille.pub.svr.StartInitServlet;
import irille.pub.tb.EnumLine;
import irille.pub.tb.IEnumOpt;
import irille.pub.tb.Tb;
import irille.pub.tb.TbBase;

public class Wx extends PackageBase {
	private static final Log LOG = new Log(Wx.class);
	public static final Wx INST = new Wx();
	public static TbBase TB = new TbBase<Tb>(Wx.class, "微信模块"); // 定义公共的Fld对象用

	private Wx() {
	}

	public static void main(String[] args) {
		StartInitServlet.initBeanLoad();
		PrvRoleAct.TB.getCode();
		Wx_ConfPackage.INST.install();
	}

	@Override
	public void initTbMsg() { // 初始化表信息
		addTb(1, WxAccount.class);
		addTb(2, WxSubscribe.class);
		addTb(3, WxAuto.class);
		addTb(4, WxMenu.class);
		addTb(7, WxUser.class);
		addTb(8, WxUserGroup.class);
		addTb(9, WxMessage.class);
		addTb(10, WxMassMessage.class);
		addTb(11, WxExpand.class);
		addTb(12, WxSetting.class);
		addTb(16, WxOpenPlat.class);
		addTb(17, WxActionRecord.class);
	}

	public void initTranData() { // 初始化PrvTranData表数据
		addTD(new TranDataMsg(WxOpenPlat.TB).u(WxOpenPlat.T.USER_SYS).d(WxOpenPlat.T.USER_SYS, SysUser.T.DEPT).o(WxOpenPlat.T.USER_SYS, SysUser.T.DEPT, SysDept.T.ORG));
		addTD(new TranDataMsg(WxAccount.TB).u(WxAccount.T.USER_SYS).d(WxAccount.T.USER_SYS, SysUser.T.DEPT).o(WxAccount.T.USER_SYS, SysUser.T.DEPT, SysDept.T.ORG));
		addTD(new TranDataMsg(WxSetting.TB).u(WxSetting.T.USER_SYS).d(WxSetting.T.USER_SYS, SysUser.T.DEPT).o(WxSetting.T.USER_SYS, SysUser.T.DEPT, SysDept.T.ORG));
	}

	@Override
	public SysModule initModule() {
		return iuModule(Wx.TB, "wx-微配置中心-400,wxex-高级接口-410");
	}

	public int getPackageId() {
		return Wx_ConfPackage.INST.getPackageId(getClass());
	}

	/**
	 * 初始化，在运行期间仅执行一次
	 */
	public void initOnlyOne() { // 初始化方法，在每次启动时执行一次
		super.initOnlyOne();
	}

	/**
	 * @author whx
	 *         选择类型
	 */
	public enum OAccountType implements IEnumOpt {
		// @formatter:off
    SERVICE(1, "服务号"), SUBSCRIPTION(2, "订阅号");
    public static String NAME = "公众号类型";
    public static OAccountType DEFAULT = SERVICE; // 定义缺省值
    private EnumLine _line;
    private OAccountType(int key, String name)  {_line = new EnumLine(this, key, name); }
    public EnumLine getLine() {
      return _line;
    }
  }

	public enum OMsgType implements IEnumOpt {
    TEXT(1, "文本消息"), IMAGE(2, "图片消息"),
    MUSIC(3, "音乐消息"),VIDEO(4, "视频消息"),
    VOICE(5, "语音消息"), NEWS(6, "图文消息");
    public static String NAME = "消息类型";
    public static OMsgType DEFAULT = TEXT; // 定义缺省值
    private EnumLine _line;
    private OMsgType(int key, String name)  {_line = new EnumLine(this, key, name); }
    public EnumLine getLine() {
      return _line;
    }
  }
  public enum OWxMsgType implements IEnumOpt {
    TEXT(1, "文本消息"), IMG(2, "图文消息"), 
    VOICE(3, "语音消息"), VIDEO(4, "视频消息"), 
    MINVIDEO(5, "小视频消息"), GP(6, "地理位置消息"), 
    LINK(7, "链接消息");
    public static String NAME = "消息类型";
    public static OWxMsgType DEFAULT = TEXT; // 定义缺省值
    private EnumLine _line;
    private OWxMsgType(int key, String name)  {_line = new EnumLine(this, key, name); }
    public EnumLine getLine() {
      return _line;
    }
  }
  public enum OMassMsgType implements IEnumOpt {
  	ARTICLE(1, "图文消息"), TEXT(2, "文本消息"), 
    VOICE(3, "语音消息"), IMAGE(4,"图片消息"),
    VIDEO(5, "视频消息"), CARD(6, "卡券消息"); 
    public static String NAME = "群发消息类型";
    public static OMassMsgType DEFAULT = ARTICLE; // 定义缺省值
    private EnumLine _line;
    private OMassMsgType(int key, String name)  {_line = new EnumLine(this, key, name); }
    public EnumLine getLine() {
      return _line;
    }
  }
  public enum OWxMsgDir implements IEnumOpt {
    RECEIVE(1, "接收消息"), REPLY(2, "回复消息"); 
    public static String NAME = "消息方向";
    public static OWxMsgDir DEFAULT = RECEIVE; // 定义缺省值
    private EnumLine _line;
    private OWxMsgDir(int key, String name)  {_line = new EnumLine(this, key, name); }
    public EnumLine getLine() {
      return _line;
    }
  }
  public enum OMotionType implements IEnumOpt {
    MESSAGECLASS(1, "消息触发类"), WEBLINKCLASS(2, "网页链接类");
    public static String NAME = "动作设置";
    public static OMotionType DEFAULT = MESSAGECLASS; // 定义缺省值
    private EnumLine _line;
    private OMotionType(int key, String name)  {_line = new EnumLine(this, key, name); }
    public EnumLine getLine() {
      return _line;
    }
  }
  public enum OStatus  implements IEnumOpt {
    FOLLOW(1, "关注"), NOFOLLOW(2, "取消关注");
    public static String NAME = "关注状态";
    public static OStatus DEFAULT = FOLLOW; // 定义缺省值
    private EnumLine _line;
    private OStatus(int key, String name)  {_line = new EnumLine(this, key, name); }
    public EnumLine getLine() {
      return _line;
    }
	}
  public enum OSyncStatus  implements IEnumOpt {
    INIT(1, "未同步"), SYNC(2, "已同步"), DEL(9, "已删除");
    public static String NAME = "同步状态";
    public static OSyncStatus DEFAULT = INIT; // 定义缺省值
    private EnumLine _line;
    private OSyncStatus(int key, String name)  {_line = new EnumLine(this, key, name); }
    public EnumLine getLine() {
      return _line;
    }
	}
  public enum OQRCodeType implements IEnumOpt {
  	TEMPORARY(1,"临时二维码"),PERMANENT(2,"永久二维码");
  	public static String NAME = "二维码类型";
  	public static OQRCodeType DEFAULT=TEMPORARY;
  	private EnumLine _line;
  	private OQRCodeType(int key, String name) {_line = new EnumLine(this, key, name); }
  	public EnumLine getLine() {
  		return _line;
  	}
  }
  // @formatter:on
}
