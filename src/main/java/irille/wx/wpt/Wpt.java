package irille.wx.wpt;

import irille.core.sys.SysModule;
import irille.pub.Log;
import irille.pub.bean.PackageBase;
import irille.pub.tb.EnumLine;
import irille.pub.tb.IEnumOpt;
import irille.pub.tb.Tb;
import irille.pub.tb.TbBase;
import irille.wx.wx.Wx_ConfPackage;

public class Wpt extends PackageBase {
	private static final Log LOG = new Log(Wpt.class);
	public static final Wpt INST = new Wpt();
	public static TbBase TB = new TbBase<Tb>(Wpt.class, "订阅号"); // 定义公共的Fld对象用


	private Wpt() {
	}

	@Override
	public void initTbMsg() { // 初始化表信息
		addTb(1, WptCity.class);
		addTb(2, WptPetitionCity.class);
		addTb(3, WptCityLine.class);
		addTb(4, WptRestaurant.class);
		addTb(5, WptRestaurantLine.class);
		addTb(6, WptBanquet.class);
		addTb(7, WptMenu.class);
		addTb(8, WptCombo.class);
		addTb(9, WptComboLine.class);
		addTb(10, WptCustomService.class);
		addTb(11, WptSpecial.class);
		addTb(12, WptSpecialLine.class);
		addTb(13, WptServiceCenter.class);
		addTb(14, WptFeedBack.class);
		addTb(15, WptHeadline.class);
		addTb(16, WptOrder.class);
		addTb(17, WptOrderLine.class);
		addTb(18, WptOrderService.class);
		addTb(19, WptCollect.class);
		addTb(20, WptHot.class);
		addTb(21, WptRestaurantBsn.class);
		addTb(22, WptRestaurantCase.class);
		addTb(23, WptRestaurantBanner.class);
		addTb(24, WptDistributionRule.class);
		addTb(25, WptQrcodeRule.class);
		addTb(26, WptRedPackRule.class);
		addTb(27, WptWxTips.class);
		addTb(28, WptRestaurantTemplate.class);
		addTb(29, WptCustomForm.class);
	}

	public void initTranData() { // 初始化PrvTranData表数据
	}

	@Override
	public SysModule initModule() {
		return iuModule(Wpt.TB, "wpt-私人订制-419");
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
	public enum OStatus implements IEnumOpt {
		NOTACCEPTED(0, "未受理"), ACCEPTED(1,"已受理"), UNPAYMENT(2, "未付款"),
	    DEPOSIT (3,"已付定金"),  PAYMENT(4,"已付款"),  FINISH(5,"已完成"),
	    CLOSE(6,"已关闭"), CANCEL(7, "申请取消订单"), REFUND(8, "申请退款");
	    public static String NAME = "订单状态";
	    public static OStatus DEFAULT = UNPAYMENT; // 定义缺省值
	    private EnumLine _line;
	    
	    private OStatus(int key, String name) {
	      _line = new EnumLine(this, key, name);
	    }
	    
	    public EnumLine getLine() {
	      return _line;
	    }
	  }
	
	public enum OContactStatus implements IEnumOpt {
	    MOBILE(0, "手机"), WECHAT(1, "微信"), QQ(2,"qq"), OTHER (3,"其他");
	    public static String NAME = "联系方式类型";
	    public static OContactStatus DEFAULT = MOBILE; // 定义缺省值
	    private EnumLine _line;
	    
	    private OContactStatus(int key, String name) {
	      _line = new EnumLine(this, key, name);
	    }
	    
	    public EnumLine getLine() {
	      return _line;
	    }
	  }
	public enum OArticleStatus implements IEnumOpt {
	    UNPUBLISHED(0, "未发表"),PUBLISHED(1, "已发表");
	    public static String NAME = "状态";
	    public static OArticleStatus DEFAULT = UNPUBLISHED; // 定义缺省值
	    private EnumLine _line;
	    
	    private OArticleStatus(int key, String name) {
	      _line = new EnumLine(this, key, name);
	    }
	    
	    public EnumLine getLine() {
	      return _line;
	    }
	  }
	public enum OPayChannel implements IEnumOpt {
	    WX(0, "微信"),CASH(1, "现金");
	    public static String NAME = "状态";
	    public static OPayChannel DEFAULT = WX; // 定义缺省值
	    private EnumLine _line;
	    
	    private OPayChannel(int key, String name) {
	      _line = new EnumLine(this, key, name);
	    }
	    
	    public EnumLine getLine() {
	      return _line;
	    }
	  }
  // @formatter:on
}
