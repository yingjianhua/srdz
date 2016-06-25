package irille.wx.wp;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(WpArtShow.class);
		suite.addTestSuite(WpArt.class);
		suite.addTestSuite(WpArtBanner.class);
		suite.addTestSuite(WpArtTheme.class);
		suite.addTestSuite(WpBsn.class);
		suite.addTestSuite(WpBsnArea.class);
		suite.addTestSuite(WpBsnCtg.class);
		//$JUnit-END$
		return suite;
	}

}
