package irille.core.sys;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(SysCell.class);
		suite.addTestSuite(SysCom.class);
		suite.addTestSuite(SysCtype.class);
		suite.addTestSuite(SysCtypeCode.class);
		suite.addTestSuite(SysCustom.class);
		suite.addTestSuite(SysCustomOrg.class);
		suite.addTestSuite(SysDept.class);
		suite.addTestSuite(SysEm.class);
		suite.addTestSuite(SysGrp.class);
		suite.addTestSuite(SysMenu.class);
		suite.addTestSuite(SysMenuAct.class);
		suite.addTestSuite(SysModule.class);
		suite.addTestSuite(SysOrg.class);
		suite.addTestSuite(SysPerson.class);
		suite.addTestSuite(SysPersonLink.class);
		suite.addTestSuite(SysPost.class);
		suite.addTestSuite(SysProject.class);
		suite.addTestSuite(SysSeq.class);
		suite.addTestSuite(SysSeqLine.class);
		suite.addTestSuite(SysShiping.class);
		suite.addTestSuite(SysSystem.class);
		suite.addTestSuite(SysTable.class);
		suite.addTestSuite(SysTableAct.class);
		suite.addTestSuite(SysTemplat.class);
		suite.addTestSuite(SysTemplatCell.class);
		suite.addTestSuite(SysUser.class);
		suite.addTestSuite(SysUserLogin.class);
		suite.addTestSuite(SysUserRole.class);
		//$JUnit-END$
		return suite;
	}

}
