/**
 * 
 */
package irille.core.sys;

import irille.core.lg.Lg;
import irille.core.prv.Prv;
import irille.pub.bean.PackageBase.ConfPackageBase;

/**
 * 包配置文件
 * @author whx
 * 
 */
public class ConfPackage extends ConfPackageBase {
	public static ConfPackage INST = new ConfPackage();

	public void installSys() {
		//core系统核心 2000以内
		add(Sys.class, 0);
		add(Lg.class, 200);
		//add(Ms.class, 400);
		add(Prv.class, 600);
	}

	@Override
	public void initPacks() {
		super.initPacks();
		_packsFlag = true;
		installSys();
	}
}
