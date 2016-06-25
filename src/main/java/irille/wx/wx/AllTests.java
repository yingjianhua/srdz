package irille.wx.wx;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(WxAccount.class);
		suite.addTestSuite(WxAuto.class);
		suite.addTestSuite(WxExpand.class);
		suite.addTestSuite(WxMassMessage.class);
		suite.addTestSuite(WxMenu.class);
		suite.addTestSuite(WxMessage.class);
		suite.addTestSuite(WxSubscribe.class); 
		suite.addTestSuite(WxUser.class);
		suite.addTestSuite(WxUserGroup.class);
		suite.addTestSuite(WxSetting.class);
		suite.addTestSuite(WxOpenPlat.class);
		//$JUnit-END$
		return suite;
	}
}
