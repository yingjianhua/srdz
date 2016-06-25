package irille.pub.idu;

import irille.pub.PropertyUtils;
import irille.pub.bean.Bean;
import irille.pub.bean.BeanMain;

public class IduBase {

	public static class Ins extends IduIns<Ins, BeanMain> {
	}

	public static class Upd extends IduUpd<Upd, BeanMain> {

		public void before() {
			super.before();
			BeanMain dbBean = loadThisBeanAndLock();
			PropertyUtils.copyPropertiesWithout(dbBean, getB(), Bean.tb(clazz()).get("pkey"));
			setB(dbBean);
		}

	}

	public static class Del extends IduDel<Del, BeanMain> {}

	public static class Page extends IduPage<Page, BeanMain> {}
	
	public static class Enabled extends IduEnabled<Enabled, BeanMain> {}
	
	public static class UnEnabled extends IduUnEnabled<UnEnabled, BeanMain> {}
	
}
