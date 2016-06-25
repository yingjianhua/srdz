package irille.core.prv;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(PrvRole.class);
		suite.addTestSuite(PrvRoleAct.class);
		suite.addTestSuite(PrvRoleLine.class);
		suite.addTestSuite(PrvRoleTran.class);
		suite.addTestSuite(PrvTranData.class);
		suite.addTestSuite(PrvTranGrp.class);
		//$JUnit-END$
		return suite;
	}

}
