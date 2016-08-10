package irille.wpt.actions;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.stereotype.Controller;

import irille.core.sys.Sys.OEnabled;
import irille.pub.bean.Bean;
import irille.pub.bean.BeanBase;
import irille.pub.idu.Idu;
import irille.wx.wpt.WptRestaurant;
import irille.wx.wpt.WptSpecial;
import irille.wx.wpt.WptSpecialLine;
@Controller
public class ShowSpecialAction extends AbstractWptAction implements IMenuShareAppMessage, IMenuShareTimeline {

	/**
	 * 
	 */
	private static final long serialVersionUID = -615820228352555970L;
	private Integer id;
	private WptSpecial special;
	private List<WptRestaurant> restaurants;
	private static final String TITLE_PRE = "【享食光】• ";
	/**
	 * 发现详情页
	 */
	@Override
	public String execute() throws Exception {
		String where = Idu.sqlString("select r.*,s.{0} from {1} r right join {2} s on (r.{3}=s.{4}) where s.{5}=? and r.{6}=? order by s.{0} asc", 
				WptSpecialLine.T.SORT, WptRestaurant.class, WptSpecialLine.class, WptRestaurant.T.PKEY, WptSpecialLine.T.RESTAURANT,
				WptSpecialLine.T.SPECIAL, WptRestaurant.T.ENABLED );
		restaurants = BeanBase.list(where, new BeanBase.ResultSetBean<WptRestaurant>() {
			@Override
			public WptRestaurant tran(ResultSet set) {
				WptRestaurant bean = Bean.newInstance(WptRestaurant.class);
				bean.fromResultSet(set);
				return bean;
			}
		}, id, OEnabled.TRUE.getLine().getKey());
		
		setResult("find/specialDetail.jsp");
		return TRENDS;
	}
	@Override
	public String getShareTimelineTitle() {
		return TITLE_PRE + getSpecial().getExtName();
	}
	@Override
	public String getShareTimelineLink() {
		return getRequestUrl();
	}
	@Override
	public String getShareTimelineImgUrl() {
		return getDomain()+"/"+getSpecial().getBaseImgUrl();
	}
	@Override
	public String getShareAppMessageTitle() {
		return TITLE_PRE + getSpecial().getExtName();
	}
	@Override
	public String getShareAppMessageDesc() {
		return getSpecial().getIntro();
	}
	@Override
	public String getShareAppMessageLink() {
		return getRequestUrl();
	}
	@Override
	public String getShareAppMessageImgUrl() {
		return getDomain()+"/"+getSpecial().getBaseImgUrl();
	}
	@Override
	public String getShareAppMessageType() {
		return "link";
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public WptSpecial getSpecial() {
		if(special == null)
			special = WptSpecial.load(WptSpecial.class, id);
		return special;
	}
	public void setSpecial(WptSpecial special) {
		this.special = special;
	}
	public List<WptRestaurant> getRestaurants() {
		return restaurants;
	}
	public void setRestaurants(List<WptRestaurant> restaurants) {
		this.restaurants = restaurants;
	}
}
