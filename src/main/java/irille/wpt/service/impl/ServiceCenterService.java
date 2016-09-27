package irille.wpt.service.impl;

import java.io.IOException;

import org.json.JSONException;
import org.springframework.stereotype.Service;

import irille.pub.idu.Idu;
import irille.wpt.bean.ServiceCenter;
import irille.wpt.service.AbstractService;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;

@Service
public class ServiceCenterService extends AbstractService<ServiceCenter> {
	
	public ServiceCenter find(Integer account) {
		return serviceCenterDao.find(account);
	}

	/**
	 * 客服中心设置
	 */
	public void insOrUpd(ServiceCenter serviceCenter) throws IOException, JSONException {
		WxAccount account = WxAccountDAO.getByUser(Idu.getUser());
		if (serviceCenter.getPkey() == null) {
			serviceCenter.setAccount(account.getPkey());
			serviceCenter.setPkey(account.getPkey());
			serviceCenterDao.save(serviceCenter);
		} else {
			serviceCenter.setAccount(account.getPkey());
			serviceCenter.setPkey(account.getPkey());
			serviceCenterDao.update(serviceCenter);
		}
	}
}
