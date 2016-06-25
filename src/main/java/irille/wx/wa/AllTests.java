package irille.wx.wa;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(WaQRCode.class);
		
		//$JUnit-END$
		return suite;
	}

}
