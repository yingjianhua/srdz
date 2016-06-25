package irille.action.sys;

import irille.action.ActionBase;
import irille.core.sys.SysCell;

public class SysCellAction extends ActionBase<SysCell> {

	@Override
	public Class beanClazz() {
		return SysCell.class;
	}

	public SysCell getBean() {
		return _bean;
	}

	public void setBean(SysCell bean) {
		this._bean = bean;
	}

	@Override
	public String orderBy() {
		return " ORDER BY " + SysCell.T.CODE.getFld().getCodeSqlField();
	}

}
