package irille.wpt.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import irille.wpt.bean.CustomForm;
import irille.wpt.dao.AbstractDao;

@Repository
public class CustomFormDao extends AbstractDao<CustomForm, Integer>{

	public CustomForm findByFormid(String formid) {
		return findUnique("select * from wpt_custom_form where formid=?", formid);
	}
	public List<CustomForm> listByMember(Integer memberId) {
		return list("select * from wpt_custom_form where member=?", memberId);
	}
	public Long countByMember(Integer memberId) {
		return count("select count(*) from wpt_custom_form where member=?", memberId);
	}
}
