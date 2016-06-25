package irille.action.wax;

import java.io.Serializable;

import irille.action.ActionWx;
import irille.wx.wax.WaxBnftPrize;
import irille.wx.wax.WaxBnftPrizeDAO.DoSend;

public class WaxBnftPrizeAction extends ActionWx<WaxBnftPrize,WaxBnftPrizeAction> {
	public WaxBnftPrize getBean() {
		return _bean;
	}

	public void setBean(WaxBnftPrize bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return WaxBnftPrize.class;
	}
	public void doSend() throws Exception {
		DoSend act = new DoSend();
		act.setPkeys(getPkeys());
		act.commit();
		writeSuccess(act.getB());
	}
}
