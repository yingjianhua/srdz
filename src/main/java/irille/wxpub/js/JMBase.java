package irille.wxpub.js;

import irille.pub.Log;
import irille.pub.PubInfs.IMsg;
import irille.pub.html.ExtList;
import irille.pub.svr.Env;

import java.lang.reflect.Field;


public abstract class JMBase<THIS extends JMBase> extends ExtList<JMBase> {
	public static final Log LOG = new Log(JMBase.class);
	public enum Msgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
		argErr("非法字段【{0}】，无法获取!"),
		accErr("字段【{0}】无访问权限!")
		;
		private String _msg;
		private Msgs(String msg) { _msg=msg; }
		public String getMsg() {return _msg; }
	}
	private JsFldFun _success;
	private JsFldFun _fail;
	private JsFldFun _complete;
	private JsFldFun _cancel;
	private JsFldFun _trigger;

	public JsFldFun getSuccess() {
		return _success;
	}

	public void setSuccess(JsFldFun success) {
		this._success = success;
	}
	
	public void setSuccess(String funName) {
		this._success = new JsFldFun(funName);
	}
	
	public void setSuccess(String funName, boolean isCode, String...params) {
		this._success = new JsFldFun(funName, isCode, params);
	}

	public JsFldFun getFail() {
		return _fail;
	}

	public void setFail(JsFldFun fail) {
		this._fail = fail;
	}
	
	public void setFail(String funName) {
		this._fail = new JsFldFun(funName);
	}
	
	public void setFail(String funName, boolean isCode, String...params) {
		this._fail = new JsFldFun(funName, isCode, params);
	}

	public JsFldFun getComplete() {
		return _complete;
	}

	public void setComplete(JsFldFun complete) {
		this._complete = complete;
	}
	
	public void setComplete(String funName) {
		this._complete = new JsFldFun(funName);
	}
	
	public void setComplete(String funName, boolean isCode, String...params) {
		this._complete = new JsFldFun(funName, isCode, params);
	}

	public JsFldFun getCancel() {
		return _cancel;
	}

	public void setCancel(JsFldFun cancel) {
		this._cancel = cancel;
	}
	
	public void setCancel(String funName) {
		this._cancel = new JsFldFun(funName);
	}
	
	public void setCancel(String funName, boolean isCode, String...params) {
		this._cancel = new JsFldFun(funName, isCode, params);
	}

	public JsFldFun getTrigger() {
		return _trigger;
	}

	public void setTrigger(JsFldFun trigger) {
		this._trigger = trigger;
	}

	public void setTrigger(String funName) {
		this._trigger = new JsFldFun(funName);
	}
	
	public void setTrigger(String funName, boolean isCode, String...params) {
		this._trigger = new JsFldFun(funName, isCode, params);
	}

	protected String getClazz() {
		return getClass().toString().split("\\.")[3];
	}
	
	public String getDefineName() {
		String funName = getClazz().replace("JM", "");
		return funName.substring(0, 1).toLowerCase() + funName.replaceFirst("\\w","");
	}
	
	/**
	 * 输出
	 */
	@Override
	public void out(int tabs, StringBuilder buf) {
		init();
		String dn = "wx." + getDefineName() + "("; 
		buf.append(isTabs() ? Env.LN + T[tabs] + dn : dn);
		super.out(tabs, buf);
		buf.append(");");
	}
	
	/**
	 * 初始化字段输出内容
	 * @param flds
	 */
	private void initFlds(Field[] flds){
		for (Field fld :flds) {
			Object obj;
			try {
				obj = fld.get(this);
				if (obj == null || obj instanceof Log)//若字段值为空或者为Log类型，不输出
					continue;
				if (obj.getClass().isArray()) //数组类型，输出属性值为数组，由“[]”括起来，如['aaa',bbb,'ccc']
					AddDime(getFldName(fld), (Object[]) obj);
				else
					addExp(getFldName(fld), obj);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				throw LOG.err(Msgs.argErr, getFldName(fld));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				throw LOG.err(Msgs.accErr, getFldName(fld));
			}
		}
	}
	
	/**
	 * 初始化本类及上级父类字段
	 */
	private void init() {
		Field[] flds = getClass().getDeclaredFields();//获取本身的字段
		Field[] sflds = getClass().getSuperclass().getDeclaredFields();//获取父类的字段
		initFlds(flds);
		initFlds(sflds);
	}
	
	private String getFldName(Field fld) {
		return fld.getName().substring(1);
	}
	
	/**
	 * 增加表达式的属性定义行，输出时没有带“'”
	 * 
	 * @param name
	 *          名称
	 * @param value
	 *          值
	 * @return
	 */
	public THIS addExp(String name, Object value) {
		return (THIS) addExp(name, value.toString());
	}
}
