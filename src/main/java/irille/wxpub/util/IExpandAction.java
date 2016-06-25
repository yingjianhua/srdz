package irille.wxpub.util;

/**
 * 所有扩展功能的类都要继承IExpandAction接口
 * */
public interface IExpandAction {
	
	String excute(String faceImgUrl,String accountId,String openId,String content);
}
