package irille.action.wm;

import irille.action.ActionBase;
import irille.pub.Str;
import irille.pub.bean.Bean;
import irille.pub.idu.Idu;
import irille.pub.idu.IduInsLines;
import irille.pub.idu.IduPage;
import irille.pub.idu.IduUpdLines;
import irille.pub.inf.IExtName;
import irille.wx.wm.WmNews;
import irille.wx.wm.WmNewsDAO;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;
import irille.wx.wx.WxSubscribe;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;

public class WmNewsAction extends ActionBase<WmNews> {
	private List<WmNews>  _listLine;

	public List<WmNews> getListLine() {
		return _listLine;
	}

	public void setListLine(List<WmNews> listLine) {
		_listLine = listLine;
	}

	public WmNews getBean() {
		return _bean;
	}

	public void setBean(WmNews bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return WmNews.class;
	}
	
	public WmNews insRun() throws Exception {
		insBefore();
		IduInsLines ins = new WmNewsDAO.Ins();
		ins.setClazz(WmNews.class);
		ins.setLines(getListLine());
		ins.commit();
		insAfter();
		return (WmNews) ins.getB();
	}
	@Override
	public WmNews updRun() throws Exception {
		updBefore();
		IduUpdLines upd = new WmNewsDAO.Upd();
		upd.setClazz(WmNews.class);
		upd.setLines(getListLine());
		upd.commit();
		updAfter();
		return (WmNews) upd.getB();
	}
	
	public void sync() throws Exception {
		WmNews bean = WmNewsDAO.sync(getPkey());
		HttpServletResponse response = ServletActionContext.getResponse();
		JSONObject json = crtJsonByBean(bean, "bean.");
		json.put(SUCCESS, true);
		response.getWriter().print(json.toString());
	}
	public void unsync() throws Exception {
		WmNews bean = WmNewsDAO.unsync(getPkey());
		HttpServletResponse response = ServletActionContext.getResponse();
		JSONObject json = crtJsonByBean(bean, "bean.");
		json.put(SUCCESS, true);
		response.getWriter().print(json.toString());
	}
	@Override
	public void list() throws Exception {
		JSONObject json = new JSONObject();
		JSONArray ja = new JSONArray();
		// 目前过滤器的搜索，是肯定会带初始条件的
		String where = Str.isEmpty(getQuery()) ? crtFilter() : crtQuery();
		IduPage page = newPage();
		page.setStart(getStart());
		page.setLimit(getLimit());
		page.setWhere(where);
		page.commit();
		List<WmNews> list = page.getList();
		JSONObject lineJson = null;
		Integer account = WxAccountDAO.getByUser(getLoginSys()).getPkey();
		for (WmNews line : list) {
			if(line.getAccount()!=account) continue;
			if(line.getPicUp()!=null) continue;
			lineJson = crtJsonByBean(line);
			ja.put(lineJson);
		}
		json.put(STORE_ROOT, ja);
		json.put(STORE_TOTAL, page.getCount());
		writerOrExport(json);
	  
	}
	@Override
	public void load() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		String result = WmNewsDAO.load(getPkey());
		response.getWriter().print(result);
	}
	@Override
	public JSONObject crtJsonExt(JSONObject json, Bean bean, String pref)
			throws Exception {
		if(((WmNews)bean).getExp() != null) {
			Bean obj = ((WmNews) bean).gtExp();
			String showname = obj.getPkey().toString();
			if (IExtName.class.isAssignableFrom(obj.clazz()))
				showname = ((IExtName) obj).getExtName();
			json.put(pref + WmNews.T.EXP.getFld().getCode(), ((WmNews)bean).getExp() + BEAN_SPLIT + showname);
		}
		return super.crtJsonExt(json, bean, pref);
	}
	@Override
	public String crtAll() {
      WxAccount account = WxAccountDAO.getByUser(Idu.getUser());
      if(account==null){
        return "1=2";
      }else{
        return WmNews.T.ACCOUNT.getFld().getCodeSqlField() + " = " + account.getPkey();
      }
	}
	
}
