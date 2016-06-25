package irille.wx.wax;

import irille.core.sys.SysModule;
import irille.pub.Log;
import irille.pub.bean.PackageBase;
import irille.pub.tb.EnumLine;
import irille.pub.tb.IEnumOpt;
import irille.pub.tb.Tb;
import irille.pub.tb.TbBase;
import irille.wx.wx.Wx_ConfPackage;

public class Wax extends PackageBase {
	private static final Log LOG = new Log(Wax.class);
	public static final Wax INST = new Wax();
	public static TbBase TB = new TbBase<Tb>(Wax.class, "微信扩展扩展模块"); // 定义公共的Fld对象用

	private Wax() {
	}

	@Override
	public void initTbMsg() { // 初始化表信息
		addTb(1, WaxBnft.class);
		addTb(2, WaxBnftEntry.class);
		addTb(3, WaxBnftPrize.class);
		addTb(4, WaxLady.class);
		addTb(5, WaxLadyPic.class);
		addTb(6, WaxLadyBsn.class);
		addTb(7, WaxMan.class);
		addTb(8, WaxManPic.class);
		addTb(9, WaxManBsn.class);
	}

	public void initTranData() { // 初始化PrvTranData表数据
	}

	@Override
	public SysModule initModule() {
		return iuModule(Wax.TB, "wax-微信营销-415");
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
	 *        抽奖活动类型
	 */
	public enum OActDrawType implements IEnumOpt {
		// @formatter:off
	TURNPLATE(1, "大转盘"), SCRATCH(2, "刮刮乐");
    public static String NAME = "抽奖类型";
    public static OActDrawType DEFAULT = TURNPLATE; // 定义缺省值
    private EnumLine _line;

    private OActDrawType(int key, String name) {
      _line = new EnumLine(this, key, name);
    }

    public EnumLine getLine() {
      return _line;
    }
  }
	
	public enum OActVisitType implements IEnumOpt {
		// @formatter:off
		VISIT(1,"访问"),ENTRY(2,"报名"),VOTE(3,"投票"),OTHER(99,"操作");
    public static String NAME = "访问类型";
    public static OActVisitType DEFAULT = VISIT; // 定义缺省值
    private EnumLine _line;

    private OActVisitType(int key, String name) {
      _line = new EnumLine(this, key, name);
    }
    public EnumLine getLine() {
      return _line;
    }
  }
	
	public enum OActPageType implements IEnumOpt {
		// @formatter:off
		HOMEPAGE(1,"首页"),ENTRYPAGE(2,"报名页"),RANKINGPAGE(3,"排行页"),INFOPAGRE(4,"详情页"),CUSTOMPAGE(99,"自定义页");
    public static String NAME = "页面类型";
    public static OActPageType DEFAULT = HOMEPAGE; // 定义缺省值
    private EnumLine _line;

    private OActPageType(int key, String name) {
      _line = new EnumLine(this, key, name);
    }
    public EnumLine getLine() {
      return _line;
    }
  }


	/**
	 * @author whx
	 *         活动类型
	 */
	public enum OActType implements IEnumOpt {
		// @formatter:off
    VOTE(1, "投票活动"), DRAW(2, "抽奖活动");
    public static String NAME = "活动类型";
    public static OActType DEFAULT = VOTE; // 定义缺省值
    private EnumLine _line;

    private OActType(int key, String name) {
      _line = new EnumLine(this, key, name);
    }

    public EnumLine getLine() {
      return _line;
    }
  }
  public enum OActStatus implements IEnumOpt {
    NOTPUBLISH(1, "未发布"), PUBLISH(2, "发布"), CLOSE(3,"关闭");
    public static String NAME = "活动状态";
    public static OActStatus DEFAULT = NOTPUBLISH; // 定义缺省值
    private EnumLine _line;
    
    private OActStatus(int key, String name) {
      _line = new EnumLine(this, key, name);
    }
    
    public EnumLine getLine() {
      return _line;
    }
  }
  public enum OActVoteStatus implements IEnumOpt {
	    INIT(0, "初始"), PUBLISH(1, "已发布"), ENTRYFIN(2,"报名结束"), ACTFIN(3,"活动结束"), CLOSE(4,"已关闭");
	    public static String NAME = "活动状态";
	    public static OActVoteStatus DEFAULT = INIT; // 定义缺省值
	    private EnumLine _line;
	    
	    private OActVoteStatus(int key, String name) {
	      _line = new EnumLine(this, key, name);
	    }
	    
	    public EnumLine getLine() {
	      return _line;
	    }
	  }
  public enum OActEntryStatus implements IEnumOpt {
	    INIT(0,"初始"),APPR(1,"已审核"),FAILED(2,"不通过");
	    public static String NAME = "报名状态";
	    public static OActEntryStatus DEFAULT = INIT; // 定义缺省值
	    private EnumLine _line;
	    
	    private OActEntryStatus(int key, String name) {
	      _line = new EnumLine(this, key, name);
	    }
	    
	    public EnumLine getLine() {
	      return _line;
	    }
	  }
  public enum OActSendStatus implements IEnumOpt {
    NOTDELIVERY(1, "未发货"), DELIVERY(2, "已发货");
    public static String NAME = "发货状态";
    public static OActSendStatus DEFAULT = NOTDELIVERY; // 定义缺省值
    private EnumLine _line;
    
    private OActSendStatus(int key, String name) {
      _line = new EnumLine(this, key, name);
    }
    
    public EnumLine getLine() {
      return _line;
    }
  }
  
  public enum OActUnit implements IEnumOpt {
	    DAY(1, "天"), WEEK(2, "周");
	    public static String NAME = "单位";
	    public static OActUnit DEFAULT = DAY; // 定义缺省值
	    private EnumLine _line;
	    
	    private OActUnit(int key, String name) {
	      _line = new EnumLine(this, key, name);
	    }
	    
	    public EnumLine getLine() {
	      return _line;
	    }
	  }
  
  	public enum OActImageShape implements IEnumOpt {
	    SQ(0, "方图"), HO(1, "横图"), VE(2, "竖图"), UL(3, "不限制");
	    public static String NAME = "图片限制";
	    public static OActImageShape DEFAULT = UL; // 定义缺省值
	    private EnumLine _line;
	    
	    private OActImageShape(int key, String name) {
	      _line = new EnumLine(this, key, name);
	    }
	    
	    public EnumLine getLine() {
	      return _line;
	    }
	  }
  // @formatter:on
}
