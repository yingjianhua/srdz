package irille.wpt.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import irille.pub.ext.Ext.IExtOut;
import irille.pub.html.ExtList;
import irille.wx.wx.WxAccount;
import irille.wxpub.js.JMBase;
import irille.wxpub.js.JMCheckJsApi;
import irille.wxpub.js.JMConfig;
import irille.wxpub.js.JMError;
import irille.wxpub.js.JMReady;

public class WxJsCreater {
	private boolean debug;
	private JMConfig _config;
	private JMReady _ready = new JMReady();
	private JMError _error = new JMError();
	private Vector<IExtOut> _nodes = new Vector<IExtOut>();
	
	public boolean isDebug() {
		return debug;
	}
	public void setDebug(boolean debug) {
		this.debug = debug;
	}
	public JMConfig getConfig() {
		return _config;
	}
	public void setConfig(JMConfig config) {
		_config = config;
	}
	public JMReady getReady() {
		return _ready;
	}
	public void setReady(JMReady ready) {
		this._ready = ready;
	}
	public JMError getError() {
		return _error;
	}
	public void setError(JMError error) {
		this._error = error;
	}
	public Vector<IExtOut> getNodes() {
		return _nodes;
	}
	public void setNodes(Vector<IExtOut> nodes) {
		_nodes = nodes;
	}
	
	private WxJsCreater() {
	}
	public WxJsCreater(boolean debug) {
		this.debug = debug;
	}
	/**
	 *  向列表添加接口名称
	 * @param nodes
	 * @param infs
	 */
	private void setWxInf(Vector<IExtOut> nodes, List<String> infs) {
		for (IExtOut node : nodes) {
			if (node instanceof JMBase) //如果是微信js接口类型，将接口名加入到列表
				infs.add(((JMBase) node).getDefineName());
			if (node instanceof ExtList) //如果是基础列表类型，递归继续寻找微信接口类
				setWxInf(((ExtList) node).getNodes(), infs);
		}
	}
	/**
	 * 添加列表内容
	 * @param objs
	 * @return
	 */
	public JMReady add(IExtOut...objs) {
		for (IExtOut obj : objs)
			getReady().add(obj);
		return getReady();
	}
	/**
	 * 输出js
	 * @return
	 */
	public String crtJs(WxAccount account, HttpServletRequest request) {
		String query = request.getQueryString();
		String requestUrl = request.getRequestURL() + (query == null?"":("?" + query));
		
		int tabs = 1;
		int i = 0;
		StringBuilder buf = new StringBuilder();
		List<String> infs = new ArrayList<String>(); //接口名称列表
		setWxInf(getReady().getNodes(), infs); //将接口名称添加至列表
		String[] fnames = new String[infs.size()];
		for (String name : infs)
			fnames[i++] = name;
		setConfig(new JMConfig(account, requestUrl, fnames).setDebug(debug)); //初始化微信config
		JMCheckJsApi chkapi = new JMCheckJsApi(fnames);
		chkapi.setSuccess("chkApi", true);
		add(chkapi);
		getConfig().out(tabs, buf);
		getReady().out(tabs, buf);
		for (IExtOut node : getNodes()) //将与config和ready同级的代码对象输出到到buf
			node.out(tabs, buf);
		getError().AddFunBody("GErr"); //TODO 输出全局错误函数
		getError().out(tabs, buf);
		return buf.toString();
	}
}
