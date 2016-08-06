package irille.action.wpt;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;

import irille.action.ActionWx;
import irille.wx.wpt.WptCombo;
import irille.wx.wpt.WptComboDAO;
import irille.wx.wpt.WptComboLine;

public class WptComboAction extends ActionWx<WptCombo,WptComboAction> {
	public List<WptComboLine> _listLine;
	
	public List<WptComboLine> getListLine() {
		return _listLine;
	}

	public void setListLine(List<WptComboLine> _listLine) {
		this._listLine = _listLine;
	}

	public WptCombo getBean() {
		return _bean;
	}

	public void setBean(WptCombo bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return WptCombo.class;
	}
	@Override
	public WptCombo updRun() throws Exception {
		updBefore();
		WptComboDAO.Upd upd = new WptComboDAO.Upd();
		upd.setB(_bean);
		upd.setLines(_listLine);
		upd.commit();
		updAfter();
		return upd.getB();
	}

	@Override
	public WptCombo insRun() throws Exception {
		insBefore();
		WptComboDAO.Ins ins = new WptComboDAO.Ins();
		ins.setB(_bean);
		ins.setLines(_listLine);
		ins.commit();
		insAfter();
		return ins.getB();
	}
	public void enableDisable() throws Exception {
		WptComboDAO dao = new WptComboDAO();
		JSONObject jbean = crtJsonByBean(dao.enableDisable(getPkey()), "bean.");
		ServletActionContext.getResponse().getWriter().print(jbean.put("success", true));
	}
	
}
