/**
 * 
 */
package irille.pub.svr;

import irille.core.lg.Lg;
import irille.core.sys.Sys;
import irille.core.sys.Sys.OEnabled;
import irille.core.sys.Sys.OYn;
import irille.pub.IPubVars;


/**
 * @author surface1
 *
 */
public interface ISvrVars  extends IPubVars{
	public static final Sys.T SYS = Sys.T.USER_SYS;
	public static final Env ENV=Env.INST;
	public static final Lg.T LG = Lg.T.LOGIN;
//	public static final Gs.T GS = Gs.T.UOM;
//	public static final Mv.T MV = Mv.T.COST_TYPE;
//	public static final Pur.T PUR = Pur.T.SETTLE_TYPE;
//	public static final Cst.T CST = Cst.T.SAL_INVOICE_RED;
//	public static final Sal.T SAL = Sal.T.SALE;
//	public static final Rp.T RP = Rp.T.JOURNAL_TYPE;

	public static final byte YES = OYn.YES.getLine().getKey();
	public static final byte NO = OYn.NO.getLine().getKey();
	public static final byte TRUE = OEnabled.TRUE.getLine().getKey();
	public static final byte FALSE = OEnabled.FALSE.getLine().getKey();
}
