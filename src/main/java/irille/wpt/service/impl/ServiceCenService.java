package irille.wpt.service.impl;

import java.io.IOException;

import javax.annotation.Resource;

import org.json.JSONException;
import org.springframework.stereotype.Service;

import irille.pub.idu.Idu;
import irille.wpt.bean.ServiceCenter;
import irille.wpt.dao.impl.ServiceCenDao;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;

@Service
public class ServiceCenService {

	@Resource
	private ServiceCenDao serviceCenDao;
	
	public ServiceCenter find(Integer account) {
		return serviceCenDao.find(account);
	}

	/**
	 * 客服中心设置
	 */
	public void insOrUpd(ServiceCenter serviceCenter) throws IOException, JSONException {
		WxAccount account = WxAccountDAO.getByUser(Idu.getUser());
		if (serviceCenter.getPkey() == null) {
			serviceCenter.setAccount(account.getPkey());
			serviceCenter.setPkey(account.getPkey());
			serviceCenDao.save(serviceCenter);
		} else {
			serviceCenter.setAccount(account.getPkey());
			serviceCenter.setPkey(account.getPkey());
			serviceCenDao.update(serviceCenter);
		}
	}
}
