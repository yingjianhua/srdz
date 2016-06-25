package irille.action.wpt;

import java.util.List;

import irille.action.ActionWx;
import irille.wx.wpt.WptCity;
import irille.wx.wpt.WptCityDAO;
import irille.wx.wpt.WptCityLine;

public class WptCityAction extends ActionWx<WptCity,WptCityAction> {
	private List<WptCity> _cityList;
	private String _petitionCity;
	private List<WptCityLine> _listLine;
	public String getPetitionCity() {
		return _petitionCity;
	}

	public void setPetitionCity(String petitionCity) {
		_petitionCity = petitionCity;
	}

	public List<WptCity> getCityList() {
		return _cityList;
	}

	public void setCityList(List<WptCity> cityList) {
		_cityList = cityList;
	}

	public List<WptCityLine> getListLine() {
		return _listLine;
	}

	public void setListLine(List<WptCityLine> listLine) {
		_listLine = listLine;
	}

	public WptCity getBean() {
		return _bean;
	}

	public void setBean(WptCity bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return WptCity.class;
	}
	@Override
	public WptCity insRun() throws Exception {
		insBefore();
		WptCityDAO.Ins ins = new WptCityDAO.Ins();
		ins.setB(_bean);
		ins.setLines(_listLine);
		ins.commit();
		insAfter();
		return ins.getB();
	}

	@Override
	public WptCity updRun() throws Exception {
		updBefore();
		WptCityDAO.Upd upd = new WptCityDAO.Upd();
		upd.setB(_bean);
		upd.setLines(_listLine);
		upd.commit();
		updAfter();
		return upd.getB();
	}

}
