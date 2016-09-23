package irille.wx.wpt;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(WptCity.class);
		suite.addTestSuite(WptCityLine.class);
		suite.addTestSuite(WptPetitionCity.class);
		suite.addTestSuite(WptRestaurant.class);
		suite.addTestSuite(WptRestaurantLine.class);
		suite.addTestSuite(WptBanquet.class);
		suite.addTestSuite(WptMenu.class);
		suite.addTestSuite(WptCombo.class);
		suite.addTestSuite(WptComboLine.class);
		suite.addTestSuite(WptCustomService.class);
		suite.addTestSuite(WptSpecial.class);
		suite.addTestSuite(WptSpecialLine.class);
		suite.addTestSuite(WptServiceCenter.class);
		suite.addTestSuite(WptFeedBack.class);
		suite.addTestSuite(WptTop.class);
		suite.addTestSuite(WptOrder.class);
		suite.addTestSuite(WptOrderLine.class);
		suite.addTestSuite(WptOrderService.class);
		suite.addTestSuite(WptCollect.class);
		suite.addTestSuite(WptRestaurantBsn.class);
		suite.addTestSuite(WptHot.class);
		suite.addTestSuite(WptRestaurantBanner.class);
		
		
		//$JUnit-END$
		return suite;
	}

}
