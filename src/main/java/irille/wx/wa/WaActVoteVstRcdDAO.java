package irille.wx.wa;

import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.idu.Idu;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;
import irille.pub.svr.Env;
import irille.wx.wa.Wa.OActPageType;
import irille.wx.wa.Wa.OActVisitType;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;
import irille.wx.wx.WxUser;

public class WaActVoteVstRcdDAO {
	 public static final Log LOG = new Log(WaActTemplateMenuDAO.class);

	  public static class Ins extends IduIns<Ins, WaActVoteVstRcd> {
		   @Override
		    public void before() {
		      super.before();
		      WxAccount account = WxAccountDAO.getByUser(getUser());
				getB().setAccount(account.getPkey());
		      
		 
		
		    }

	  }
	  public static class Upd extends IduUpd<Upd, WaActVoteVstRcd> {
		    @Override
		    public void before() {
		      super.before();
		      WaActVoteVstRcd dbBean = load(getB().getPkey());
		      PropertyUtils.copyPropertiesWithout(dbBean, getB(), WaActVoteVstRcd.T.ACCOUNT);
		      setB(dbBean);
		    }

		  }

	  public static class Del extends IduDel<Del, WaActVoteVstRcd> {
			@Override
			public void valid() {
				super.valid();
			}
	   
	  }
/*
 * 访问记录
 */
	  public static void insByVote(WaActVote vote,WxUser user,OActPageType pagtype,OActVisitType visitType,String ip) {
		  WaActVoteVstRcd vstRcd=new WaActVoteVstRcd();
		  vstRcd.setVote(vote.getPkey());
		  if(user!=null)
			  vstRcd.setUser(user.getPkey()); 
		  vstRcd.stPagetype(pagtype);
		  vstRcd.stVisittype(visitType);
		  vstRcd.setIp(ip);
		  vstRcd.setCreatedTime(Env.getSystemTime());
		  vstRcd.setAccount(vote.getAccount());
		  vstRcd.ins();	
	}
	/*
	 *  统计阅读量
	 */
	  public static int count(WaActVote vote) {
		  String sql = Idu.sqlString("SELECT COUNT(*) FROM {0} WHERE {1}=? and {2}=? and {3}=?", WaActVoteVstRcd.class, WaActVoteVstRcd.T.VOTE, WaActVoteVstRcd.T.VISITTYPE, WaActVoteVstRcd.T.PAGETYPE);
		  Object[] result = WaActVoteVstRcd.queryOneRowIsNull(sql, vote.getPkey(),OActVisitType.VISIT.getLine().getKey(),OActPageType.HOMEPAGE.getLine().getKey());
		  if(result == null) {
			  return 0;
		  } else if(result[0] == null){
			  return 0;
		  }
		  return ((Number)result[0]).intValue();
	}
}
