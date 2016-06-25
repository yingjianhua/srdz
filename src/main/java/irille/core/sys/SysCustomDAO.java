package irille.core.sys;

import org.json.JSONArray;

import irille.pub.JsonTools;
import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.Str;
import irille.pub.bean.BeanBase;
import irille.pub.idu.Idu;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;
import irille.pub.svr.ProvDataCtrl;

/**
 * Title: <br>
 * Description: <br>
 * Copyright: Copyright (c) 2005<br>
 * Company: IRILLE<br>
 * @version 1.0
 */
public class SysCustomDAO {
	public static final Log LOG = new Log(SysCustomDAO.class);

	public static JSONArray autoComplete(int idx, int count, String code, String diy) {
		StringBuffer sql = new StringBuffer(Idu.sqlString("select pkey,code,name from {0} where {1}={2}", SysCustom.class,
		    SysCustom.T.ENABLED, Sys.OEnabled.TRUE));
		sql.append(" AND "+ProvDataCtrl.INST.getWhere(Idu.getUser(), SysCustom.class));
		if (Str.isEmpty(code) == false)
			sql.append(" and (code like '%"+code+"%' OR name like '%"+code+"%')");
		if (Str.isEmpty(diy) == false)
			sql.append(" and " + diy);
		sql.append(" order by code");
		return JsonTools.mapToArray(BeanBase.executeQueryMap(BeanBase.getPageSql(sql.toString(), false, idx, count)));
	}

	public static class Ins extends IduIns<Ins, SysCustom> {
		public SysCom _com = null;

		@Override
		public void before() {
			super.before();
			getB().stEnabled(true);
			if (SysCustom.chkUniqueCode(false, getB().getCode()) != null)
				throw LOG.err("sameCode", "代码[{0}]的客户已存在!", getB().getCode());
			getB().setRem(_com.getRem());
		}

		@Override
		public void after() {
			super.after();
			_com.setPkey(getB().gtLongPkey());
			_com.setName(getB().getName());
			_com.setShortName(getB().getShortName());
			_com.setRowVersion(getB().getRowVersion());
			SysComDAO.Ins cins = new SysComDAO.Ins();
			cins.setB(_com);
			cins.commit();
		}

	}

	public static class Upd extends IduUpd<Upd, SysCustom> {
		public SysCom _com = null;

		public void before() {
			super.before();
			SysCustom dbBean = load(getB().getPkey());
			if (dbBean.getCode().equals(getB().getCode()) == false
			    && SysCustom.chkUniqueCode(false, getB().getCode()) != null)
				throw LOG.err("sameCode", "代码[{0}]的客户已存在!", getB().getCode());
			SysCom dbCom = BeanBase.load(SysCom.class, getB().gtLongPkey());
			PropertyUtils.copyPropertiesWithout(dbCom, _com, SysCom.T.PKEY);
			dbCom.setName(getB().getName());
			dbCom.setShortName(getB().getShortName());
			dbCom.setRowVersion(dbBean.getRowVersion());
			SysComDAO.Upd cins = new SysComDAO.Upd();
			cins.setB(dbCom);
			cins.commit();
			//
			PropertyUtils.copyPropertiesWithout(dbBean, getB(), SysCustom.T.PKEY,SysCustom.T.ENABLED);
			dbBean.setRem(dbCom.getRem());
			setB(dbBean);
		}

	}
	public static class Del extends IduDel<Del, SysCustom> {
		@Override
		public void valid() {
			super.valid();
			haveBeUsed(SysCustomOrg.class, SysCustomOrg.T.CUSTOM, b.getPkey());
			haveBeUsed(SysPersonLink.class, SysPersonLink.T.TB_OBJ_LONG, b.gtLongPkey());
		}
		
		@Override
		public void after() {
		  super.after();
		  SysComDAO.Del cact = new SysComDAO.Del();
			cact.setBKey(getB().gtLongPkey());
			cact.commit();
		}
	}
}