package irille.wx.wax;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(WaxBnft.class);
		suite.addTestSuite(WaxBnftEntry.class);
		suite.addTestSuite(WaxBnftPrize.class);
		suite.addTestSuite(WaxLady.class);
		suite.addTestSuite(WaxLadyBsn.class);
		suite.addTestSuite(WaxLadyPic.class);
		suite.addTestSuite(WaxMan.class);
		suite.addTestSuite(WaxManPic.class);
		suite.addTestSuite(WaxManBsn.class);
		//$JUnit-END$
		return suite;
	}

}
