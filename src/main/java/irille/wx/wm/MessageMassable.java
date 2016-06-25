package irille.wx.wm;

import irille.wx.wx.Wx.OMassMsgType;

import org.json.JSONException;

public interface MessageMassable {
	/**
	 * 将素材转换成预览所需的json格式
	 * @param wxUserPkey
	 * @param tempPkey
	 * @return
	 */
	public String transform4Preview(Integer wxUserPkey, Integer tempPkey) throws JSONException;
	/**
	 * 将素材转换成群发所需的json格式
	 * @param isToAll
	 * @param wxGroupPkey
	 * @param tempPkey
	 * @return
	 * @throws JSONException
	 */
	public String transform4Mass(boolean isToAll, Integer wxGroupPkey, Integer tempPkey) throws JSONException;
	/**
	 * 获取群发的消息类型
	 * @return
	 */
	public OMassMsgType getMessageType();
}
