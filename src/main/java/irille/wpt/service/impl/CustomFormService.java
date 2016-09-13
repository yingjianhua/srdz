package irille.wpt.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import irille.wpt.bean.City;
import irille.wpt.bean.CustomForm;
import irille.wpt.bean.CustomService;
import irille.wpt.bean.Member;
import irille.wpt.dao.impl.BanquetDao;
import irille.wpt.dao.impl.CityLineDao;
import irille.wpt.dao.impl.CustomFormDao;
import irille.wpt.dao.impl.CustomServiceDao;
import irille.wpt.exception.ExtjsException;
import irille.wpt.tools.RangeConditionTool;
import irille.wxpub.util.mch.MchUtil;
@Service
public class CustomFormService {
	private static final String pattern1 = ",(\\d+)";
	private static final String pattern2 = "(\\d+,)";
	private static final String pattern3 = "(\\d+),(\\d+)";
	private static final String pattern4 = "(\\d+)";
	
	@Resource
	private CustomFormDao customFormDao;
	@Resource
	private BanquetDao banquetDao; 
	@Resource
	private CityLineDao citylineDao;
	@Resource
	private CustomServiceDao customServiceDao;

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
		return form;
	}
	
	public CustomForm findByFormid(String formid) {
		return customFormDao.findByFormid(formid);
	}
}
