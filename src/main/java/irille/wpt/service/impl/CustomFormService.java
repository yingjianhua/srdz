package irille.wpt.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import irille.pub.bean.Bean;
import irille.wpt.bean.City;
import irille.wpt.bean.CustomForm;
import irille.wpt.bean.CustomService;
import irille.wpt.bean.Member;
import irille.wpt.bean.ServiceCen;
import irille.wpt.bean.WxTips;
import irille.wpt.dao.impl.BanquetDao;
import irille.wpt.dao.impl.CityLineDao;
import irille.wpt.dao.impl.CustomFormDao;
import irille.wpt.dao.impl.CustomServiceDao;
import irille.wpt.dao.impl.ServiceCenDao;
import irille.wpt.dao.impl.WxTipsDao;
import irille.wpt.exception.ExtjsException;
import irille.wpt.tools.RangeConditionTool;
import irille.wpt.tools.SmsTool;
import irille.wx.wpt.Wpt.OContactStatus;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;
import irille.wxpub.util.mch.MchUtil;
@Service
public class CustomFormService {
	
	@Resource
	private CustomFormDao customFormDao;
	@Resource
	private BanquetDao banquetDao; 
	@Resource
	private CityLineDao citylineDao;
	@Resource
	private CustomServiceDao customServiceDao;
	@Resource
	private ServiceCenDao serviceCenDao;
	@Resource
	private WxTipsDao wxTipsDao;
	@Resource
	private SmsTool smsTool;

	private static final SimpleDateFormat INPUT_DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	private static final SimpleDateFormat FORMID_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
	
	public CustomForm createForm(Integer banquetId, String budget, String number, String time, City city, Integer areaId, String serviceIds, String rem, Member member,
			String contactMan, String contactSex, String contactType, String contactWay) {
		CustomForm form;
		try {
			form = new CustomForm();
			form.setFormid(FORMID_FORMAT.format(new Date())+MchUtil.createRandomNum(4));
			form.setBanquet(banquetDao.load(banquetId).getName());
			form.setBudget(RangeConditionTool.condition2Display1(budget, "元"));
			form.setNumber(RangeConditionTool.condition2Display1(number, "位"));
			form.setTime(INPUT_DATE_FORMAT.parse(time));
			form.setCity(city);
			form.setCityline(citylineDao.load(areaId));
			StringBuilder sb = new StringBuilder();
			for(CustomService customService:customServiceDao.listByIds(serviceIds)) {
				sb.append(",").append(customService.getName());
			}
			form.setCustomServices(sb.substring(1));
			form.setRem(rem);
			form.setMember(member);
			form.setContactMan(contactMan);
			form.setContactSex(Byte.valueOf(contactSex));
			form.setContactType(Byte.valueOf(contactType));
			form.setContactWay(contactWay);
			form.setCreateTime(new Date());
			form.setAccount(member.getAccount());
			customFormDao.save(form);
		} catch (NumberFormatException | ParseException e) {
			e.printStackTrace();
			throw new ExtjsException("私人订制表单产生异常");
		}
		doSent(form);
		return form;
	}
	
	public CustomForm findByFormid(String formid) {
		return customFormDao.findByFormid(formid);
	}
	
	/**
	 * 生成订单发送消息给管理人员
	 */
	public void doSent(CustomForm form){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		final ServiceCen serviceCen = serviceCenDao.find(form.getAccount());
		StringBuilder c = new StringBuilder("【享食光】私人订制 表单生成,内容如下:\n");
		c.append("表单号：").append(form.getFormid()).append("\n");
		c.append("宴会类型：").append(form.getBanquet()).append("\n");
		c.append("用餐时间：").append(format.format(form.getTime())).append("\n");
		c.append("预算：").append(form.getBudget()).append("\n");
		c.append("人数：").append(form.getNumber()).append("\n");
		c.append("城市：").append(form.getCity().getName()).append("\n");
		c.append("区域：").append(form.getCityline().getName()).append("\n");
		c.append("服务：[").append(form.getCustomServices()).append("]").append("\n");
		if(form.getRem() != null) {
			c.append("备注:").append(form.getRem()).append("\n");
		}
		c.append("联系人：").append(form.getContactMan()).append("\n");
		c.append("联系方式：");
		for(OContactStatus line:OContactStatus.values()) {
			if(form.getContactType().equals(line.getLine().getKey())) {
				c.append(line.getLine().getName()).append("-");
				break;
			}
		}
		c.append(form.getContactWay()).append("\n");
		//发送到微信用户
		String accessToken = WxAccountDAO.getAccessToken(Bean.get(WxAccount.class, form.getAccount()));
		for(WxTips line:wxTipsDao.list(form.getAccount())) {
			smsTool.doSend(accessToken, line.getMember(), c.toString());
		}
		boolean flag = true;
		if(flag) {
			return ;
		}
		//发送到手机短信
		for(String line : serviceCen.getSmsTips().split(",")){
			smsTool.doSent(line, c.toString());
		}
	}
	public List<CustomForm> list(Integer memberId) {
		return customFormDao.listByMember(memberId);
	}
	public Long countByMember(Integer memberId) {
		return customFormDao.countByMember(memberId);
	}
}
