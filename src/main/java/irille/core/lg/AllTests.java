package irille.core.lg;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(LgAttemper.class);
		suite.addTestSuite(LgLogin.class);
		suite.addTestSuite(LgTran.class);
		//$JUnit-END$
		return suite;
	}

}
