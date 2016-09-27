package irille.wpt.service;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import irille.tools.GenericsUtils;
import irille.wpt.dao.AbstractDao;
import irille.wpt.dao.impl.BanquetDao;
import irille.wpt.dao.impl.CashJournalDao;
import irille.wpt.dao.impl.CityDao;
import irille.wpt.dao.impl.CityLineDao;
import irille.wpt.dao.impl.CollectDao;
import irille.wpt.dao.impl.ComboBannerDao;
import irille.wpt.dao.impl.ComboDao;
import irille.wpt.dao.impl.ComboLineDao;
import irille.wpt.dao.impl.CommissionJournalDao;
import irille.wpt.dao.impl.CustomFormDao;
import irille.wpt.dao.impl.CustomServiceDao;
import irille.wpt.dao.impl.DistributionRuleDao;
import irille.wpt.dao.impl.FeedbackDao;
import irille.wpt.dao.impl.HeadlineDao;
import irille.wpt.dao.impl.HotDao;
import irille.wpt.dao.impl.MemberDao;
import irille.wpt.dao.impl.OrderCustomServiceDao;
import irille.wpt.dao.impl.OrderDao;
import irille.wpt.dao.impl.OrderDetailDao;
import irille.wpt.dao.impl.OrderPayJournalDao;
import irille.wpt.dao.impl.OrderServiceDao;
import irille.wpt.dao.impl.PetitionCityDao;
import irille.wpt.dao.impl.QrcodeRuleDao;
import irille.wpt.dao.impl.RedPackRuleDao;
import irille.wpt.dao.impl.RestaurantBannerDao;
import irille.wpt.dao.impl.RestaurantBsnDao;
import irille.wpt.dao.impl.RestaurantCaseDao;
import irille.wpt.dao.impl.RestaurantDao;
import irille.wpt.dao.impl.RestaurantLineDao;
import irille.wpt.dao.impl.RestaurantMenuDao;
import irille.wpt.dao.impl.RestaurantTemplateDao;
import irille.wpt.dao.impl.ServiceCenterDao;
import irille.wpt.dao.impl.SpecialDao;
import irille.wpt.dao.impl.SpecialLineDao;
import irille.wpt.dao.impl.WxTipsDao;
import irille.wpt.tools.Page;

public abstract class AbstractService<T> {

	protected AbstractDao dao;
	
	@Resource
	protected BanquetDao banquetDao;
	@Resource
	protected CashJournalDao cashJournalDao;
	@Resource
	protected CityDao cityDao;
	@Resource
	protected CityLineDao cityLineDao;
	@Resource
	protected CollectDao collectDao;
	@Resource
	protected ComboBannerDao comboBannerDao;
	@Resource
	protected ComboDao comboDao;
	@Resource
	protected ComboLineDao comboLineDao;
	@Resource
	protected CommissionJournalDao commissionJournalDao;
	@Resource
	protected CustomFormDao customFormDao;
	@Resource
	protected CustomServiceDao customServiceDao;
	@Resource
	protected DistributionRuleDao distributionRuleDao;
	@Resource
	protected FeedbackDao feedbackDao;
	@Resource
	protected HeadlineDao headlineDao;
	@Resource
	protected HotDao hotDao;
	@Resource
	protected MemberDao memberDao;
	@Resource
	protected OrderCustomServiceDao orderCustomServiceDao;
	@Resource
	protected OrderDao orderDao;
	@Resource
	protected OrderDetailDao orderDetailDao;
	@Resource
	protected OrderPayJournalDao orderPayJournalDao;
	@Resource
	protected OrderServiceDao orderServiceDao;
	@Resource
	protected PetitionCityDao petitionCityDao;
	@Resource
	protected QrcodeRuleDao qrcodeRuleDao;
	@Resource
	protected RedPackRuleDao redPackRuleDao;
	@Resource
	protected RestaurantBannerDao restaurantBannerDao;
	@Resource
	protected RestaurantBsnDao restaurantBsnDao;
	@Resource
	protected RestaurantCaseDao restaurantCaseDao;
	@Resource
	protected RestaurantDao restaurantDao;
	@Resource
	protected RestaurantLineDao restaurantLineDao;
	@Resource
	protected RestaurantMenuDao restaurantMenuDao;
	@Resource
	protected RestaurantTemplateDao restaurantTemplateDao;
	@Resource
	protected ServiceCenterDao serviceCenterDao;
	@Resource
	protected SpecialDao specialDao;
	@Resource
	protected SpecialLineDao specialLineDao;
	@Resource
	protected WxTipsDao wxTipsDao;
	
	protected Class<T> entityClass;
	
	@SuppressWarnings("unchecked")
	public AbstractService() {
		entityClass = GenericsUtils.getSuperClassGenricType(getClass());
	}
	@PostConstruct
	public void init() {
		String clazzName = entityClass.getSimpleName();
		String clazzService = clazzName.substring(0, 1).toLowerCase() 
				+ clazzName.substring(1) + "Dao";
		try {
			Field clazzField = AbstractService.class.getDeclaredField(clazzService);
			Field baseField = AbstractService.class.getDeclaredField("dao");
			baseField.set(this, clazzField.get(this)); //service就有值了
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public List<T> list(Integer start, Integer limit, String where) {
		return dao.list(start, limit, where);
	}
	
	public T load(Serializable id) {
		return (T)dao.load(id);
	}
	
	public Page<T> page(Integer start, Integer limit, String where) {
		return dao.pageHql(start, limit, where);
	}
	
	public void save(T bean) {
		dao.save(bean);
	}
	
	public void update(T bean) {
		dao.update(bean);
	}
	
	public void delete(T bean) {
		dao.delete(bean);
	}
}
