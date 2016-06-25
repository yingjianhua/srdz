package irille.wx.wm;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(WmText.class);
		suite.addTestSuite(WmNews.class);
		suite.addTestSuite(WmImage.class);
		suite.addTestSuite(WmMusic.class);
		suite.addTestSuite(WmVideo.class);
		suite.addTestSuite(WmVoice.class);
		//$JUnit-END$
		return suite;
	}

}
