package irille.wx.wm;

import irille.core.sys.SysModule;
import irille.pub.Log;
import irille.pub.bean.PackageBase;
import irille.pub.tb.EnumLine;
import irille.pub.tb.IEnumOpt;
import irille.pub.tb.Tb;
import irille.pub.tb.TbBase;
import irille.wx.wx.Wx_ConfPackage;

public class Wm extends PackageBase {
	private static final Log LOG = new Log(Wm.class);
	public static final Wm INST = new Wm();
	public static TbBase TB = new TbBase<Tb>(Wm.class, "响应消息"); // 定义公共的Fld对象用

	private Wm() {
	}

	@Override
	public void initTbMsg() { // 初始化表信息
		addTb(1, WmText.class);
		addTb(2, WmImage.class);
		addTb(3, WmMusic.class);
		addTb(4, WmVideo.class);
		addTb(5, WmVoice.class);
		addTb(6, WmNews.class);
	}

	public void initTranData() { // 初始化PrvTranData表数据
	}

	@Override
	public SysModule initModule() {
		return iuModule(Wm.TB, "wm-微消息素材-410");
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

	// @formatter:on

	public enum ONewsType implements IEnumOpt {
		BASE(0, "普通图文"), URL(1, "自定义链接"), EXP(2, "扩展链接");
		public static String NAME = "图文类型";
		public static ONewsType DEFAULT = URL; // 定义缺省值
		private EnumLine _line;
		private ONewsType(int key, String name) {
			_line = new EnumLine(this, key, name);
		}
		public EnumLine getLine() {
			return _line;
		}
	}
}
