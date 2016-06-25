package irille.wx.wp;

import irille.core.sys.SysModule;
import irille.pub.Log;
import irille.pub.bean.PackageBase;
import irille.pub.tb.Tb;
import irille.pub.tb.TbBase;
import irille.wx.wx.Wx_ConfPackage;

public class Wp extends PackageBase {
	private static final Log LOG = new Log(Wp.class);
	public static final Wp INST = new Wp();
	public static TbBase TB = new TbBase<Tb>(Wp.class, "订阅号"); // 定义公共的Fld对象用


	private Wp() {
	}

	@Override
	public void initTbMsg() { // 初始化表信息
		addTb(1, WpArtShow.class);
		addTb(2, WpArt.class);
		addTb(3, WpArtTheme.class);
		addTb(4, WpArtBanner.class);
		addTb(5, WpBsn.class);
		addTb(6, WpBsnCtg.class);
		addTb(7, WpBsnArea.class);
	}

	public void initTranData() { // 初始化PrvTranData表数据
		addTD(new TranDataMsg(WpBsn.TB).o(WpBsn.T.ORG));
		addTD(new TranDataMsg(WpBsnArea.TB).o(WpBsnArea.T.ORG));
		addTD(new TranDataMsg(WpBsnCtg.TB).o(WpBsnCtg.T.ORG));
	}

	@Override
	public SysModule initModule() {
		return iuModule(Wp.TB, "wp-微展示-418");
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
}
