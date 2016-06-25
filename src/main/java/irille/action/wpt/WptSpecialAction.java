package irille.action.wpt;

import java.util.List;

import irille.action.ActionWx;
import irille.pub.Log;
import irille.wx.wpt.WptSpecial;
import irille.wx.wpt.WptSpecialDAO;
import irille.wx.wpt.WptSpecialLine;

public class WptSpecialAction extends ActionWx<WptSpecial,WptSpecialAction> {
	public static final Log LOG = new Log(WptSpecialAction.class);
	private List<WptSpecialLine> _listLine;

	public List<WptSpecialLine> getListLine() {
		return _listLine;
	}

	public void setListLine(List<WptSpecialLine> listLine) {
		_listLine = listLine;
	}

	public WptSpecial getBean() {
		return _bean;
	}

	public void setBean(WptSpecial bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return WptSpecial.class;
	}
	@Override
	public WptSpecial insRun() throws Exception {
		insBefore();
		WptSpecialDAO.Ins ins = new WptSpecialDAO.Ins();
		ins.setB(_bean);
		ins.setLines(_listLine);
		ins.commit();
		insAfter();
		return ins.getB();
	}

	@Override
	public WptSpecial updRun() throws Exception {
		updBefore();
		WptSpecialDAO.Upd upd = new WptSpecialDAO.Upd();
		upd.setB(_bean);
		upd.setLines(_listLine);
		upd.commit();
		updAfter();
		return upd.getB();
	}
}
