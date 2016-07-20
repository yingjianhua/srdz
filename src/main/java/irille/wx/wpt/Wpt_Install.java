package irille.wx.wpt;



import irille.core.sys.SysMenu;
import irille.core.sys.SysMenuDAO;
import irille.pub.bean.InstallBase;
import irille.wx.wx.WxUser;

public class Wpt_Install extends InstallBase {
	public static final Wpt_Install INST = new Wpt_Install();

	@Override
	public void initMenu(SysMenuDAO m) {
//		SysMenu wptOrder = m.procParent("订单信息", "wpt", 10, null);
		SysMenu wptRes = m.procParent("餐厅信息", "wpt", 20, null);
		SysMenu wptCity = m.procParent("城市地区", "wpt", 100, null);
		SysMenu wptMember = m.procParent("会员管理", "wpt", 130, null);
		SysMenu wptRule = m.procParent("参数设置", "wpt", 150, null);
		
		m.proc(WptOrder.TB, 10, null);
//		m.proc(WptOrderService.TB, 12, wptOrder);
		
		m.proc(WptRestaurant.TB, 21, wptRes);//餐厅
		m.proc(WptBanquet.TB, 22, wptRes);//宴会类型
//		m.proc(WptMenu.TB, 23, wptRes);
		m.proc(WptCombo.TB, 23, wptRes);
		m.proc(WptRestaurantBsn.TB, 24, wptRes);
		m.proc(WptCase.TB, 25, wptRes);
		m.proc(WptRestaurantBanner.TB, 26, wptRes);
		m.proc(WptRestaurantTemplate.TB, 27, wptRes);
		
		m.proc(WptService.TB, 40, null);
		m.proc(WptSpecial.TB, 50, null);
		m.proc(WptTop.TB, 60, null);
		m.proc(WptHot.TB, 70, null);
		m.proc(WptServiceCen.TB, 70, null);
		m.proc(WptFeedBack.TB, 80, null);
		
		m.proc(WptCollect.TB, 90, null);
		
		m.proc(WptCity.TB, 101, wptCity);
		m.proc(WptPetitionCity.TB, 102, wptCity);
		
		m.proc(WxUser.TB, "会员信息", "wpt", "view.wpt.WptMember.List", 131, wptMember);
		
		m.proc(WptDistributionRule.TB, 151, wptRule);
		m.proc(WptQrcodeRule.TB, 152, wptRule);
		m.proc(WptRedPackRule.TB, 153, wptRule);
		
	}
}
