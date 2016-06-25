//Created on 2005-9-27
package irille.core.sys;

import irille.core.sys.Sys.OOrgState;
import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.bean.BeanBuf;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;
import irille.pub.svr.Env;
import irille.pub.svr.ProvDataCtrl;

public class SysOrgDAO {
	public static final Log LOG = new Log(SysOrgDAO.class);

	public static class Ins extends IduIns<Ins, SysOrg> {
		public SysCom _com = null;

		@Override
		public void before() {
			super.before();
			getB().stEnabled(true);
			getB().setWorkDate(Env.INST.getSystemTime());
			getB().stState(OOrgState.OPENING);
			if (getB().getOrgUp() != null)
				if(!getB().getCode().startsWith(getB().gtOrgUp().getCode()))
					throw LOG.err("codeError","机构代码[{0}]需要以上级机构代码[{1}]开头",getB().getCode(),getB().gtOrgUp().getCode());
			if (SysOrg.chkUniqueCode(false, getB().getCode()) != null)
				throw LOG.err("notFound", "机构代码[{0}]已存在,不可重复新增!", getB().getCode());
		}

		@Override
		public void after() {
			super.after();
			_com.setPkey(getB().gtLongPkey());
			_com.setName(getB().getName());
			_com.setShortName(getB().getShortName());
			SysComDAO.Ins cins = new SysComDAO.Ins();
			cins.setB(_com);
			cins.commit();
			ProvDataCtrl.initOrgMap();//将缓存中存储的机构上下级关系更新
		}

	}

	public static class Upd extends IduUpd<Upd, SysOrg> {
		public SysCom _com = null;

		public void before() {
			super.before();
			SysOrg dbBean = loadAndLock(getB().getPkey());
			if (dbBean.getTemplat().equals(getB().getTemplat()) == false)
				SysCellDAO.resetTemplate(getB().getPkey(), getB().getTemplat());
			_com.setPkey(dbBean.gtLongPkey());
			_com.setName(getB().getName());
			_com.setShortName(getB().getShortName());
			_com.setRowVersion(dbBean.getRowVersion());
			SysComDAO.Upd cins = new SysComDAO.Upd();
			cins.setB(_com);
			cins.commit();
			//
			PropertyUtils.copyPropertiesWithout(dbBean, getB(), SysOrg.T.PKEY, SysOrg.T.CODE, 
			    SysOrg.T.ORG_UP, SysOrg.T.ENABLED, SysOrg.T.WORK_DATE, SysOrg.T.STATE);
			setB(dbBean);
			BeanBuf.clear(SysOrg.class, getB().getPkey());
		}

	}

	public static class Del extends IduDel<Del, SysOrg> {

		public void valid() {
			super.valid();
				haveBeUsed(SysOrg.class, SysOrg.T.ORG_UP, b.getPkey());
				haveBeUsed(SysDept.class, SysDept.T.ORG, b.getPkey());
				haveBeUsed(SysCell.class, SysCell.T.ORG, b.getPkey());
		}
		
		@Override
		public void after() {
		  super.after();
		  SysComDAO.Del cact = new SysComDAO.Del();
			cact.setBKey(getB().gtLongPkey());
			cact.commit();
			BeanBuf.clear(SysOrg.class, getB().getPkey());
			ProvDataCtrl.initOrgMap();//将缓存中存储的机构上下级关系更新
		}

	}


}
