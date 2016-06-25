package irille.wpt.actions;

import java.sql.ResultSet;
import java.util.List;

import irille.pub.bean.Bean;
import irille.pub.bean.BeanBase;
import irille.pub.idu.Idu;
import irille.wx.wpt.WptRestaurant;
import irille.wx.wpt.WptSpecial;
import irille.wx.wpt.WptSpecialLine;

public class ShowFindAction extends AbstractWptAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -615820228352555970L;
	private Integer id;
	private WptSpecial special;
	private List<WptRestaurant> restaurants;
	/**
	 * 发现详情页
	 */
	@Override
	public String execute() throws Exception {
		special = WptSpecial.load(WptSpecial.class, id);
		String where = Idu.sqlString("select r.*,s.{0} from {1} r right join {2} s on (r.{3}=s.{4}) where s.{5}=? order by s.{0} asc", 
				WptSpecialLine.T.SORT, WptRestaurant.class, WptSpecialLine.class, WptRestaurant.T.PKEY, WptSpecialLine.T.RESTAURANT,
				WptSpecialLine.T.SPECIAL );
		restaurants = BeanBase.list(where, new BeanBase.ResultSetBean<WptRestaurant>() {
			@Override
			public WptRestaurant tran(ResultSet set) {
				WptRestaurant bean = Bean.newInstance(WptRestaurant.class);
				bean.fromResultSet(set);
				return bean;
			}
		}, id);
		
		setResult("find/findDetail.jsp");
		return TRENDS;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public WptSpecial getSpecial() {
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
