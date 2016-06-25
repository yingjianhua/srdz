package irille.wxpub.util;

import java.io.IOException;
import java.io.StringReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ReadXmlUtil {

	public static void main(String[] args) throws Exception {
//		ReadXmlUtil readXmlUtil = new ReadXmlUtil();
//		readXmlUtil.setFile(new File("D:\\workspace\\Demo\\src\\test\\xml\\demo.xml"));
//		readXmlUtil.setRootNode("returnsms");
//		readXmlUtil.getValues("taskid");
//		System.out.println(readXmlUtil.getValues("taskid"));
		String node = "mobile";
		String xml2 = "<?xml version=\"1.0\" encoding=\"utf-8\" ?><returnsms><statusbox><mobile>12345678910</mobile><taskid>zhx</taskid></statusbox><statusbox><mobile>13211233321</mobile><taskid>year</taskid></statusbox></returnsms>";

		String xmlString = "<?xml version=\"1.0\" encoding=\"utf-8\" ?><returnsms><returnstatus><de>zhajkkjasd</de><de>de3mo</de></returnstatus><message>message</message><remainpoint>remainpoint</remainpoint><taskID>taskID</taskID><successCounts>successCounts</successCounts></returnsms>";
		List<String> list = ReadXmlUtil.getValues("de", xmlString);
		System.out.println(list.size());
		for (String string : list) {
			System.out.println(string + "=====String is ");
		}
		
		
		/***********短信机的实现***********************/
		String a = URLEncoder.encode("【享食光】验证码: 2878 (享食光手机注册验证，请完成验证)，如非本人操作，请忽略本条短息。", "utf-8");
		//短信机请求，获取返回的xml文件格式的字符串
		String xmlStr = HttpRequestUtil.httpRequestPost(
				"http://218.244.141.161:8888/sms.aspx?action=send&userid=339&account=bl1174&password=123456&mobile=13732059656&content="
						+ a + "&sendTime=&extno=");
		//打印获取的xml格式的字符串
		System.out.println(xmlStr);
		
		//获取节点系列mobile对应的值
		List<String> duan = ReadXmlUtil.getValues("mobile", xmlStr);
		for (String string : duan) {
			System.out.println("mobile： " + string);
		}
		/**************************************/
		
	}

	/**
	 * 解析XML格式的字符串，获取节点对应的值
	 * @param node 需要获取的xml的节点
	 * @param xmlString xml格式的字符串
	 * @return 对应的值的list
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static List<String> getValues(String node, String xmlString) throws ParserConfigurationException, SAXException, IOException {
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		// step 2:获得具体的dom解析器
		DocumentBuilder db = null;
		db = dbf.newDocumentBuilder();
		// step3: 解析一个xml文档，获得Document对象（根结点）
		Document document = null;
		document = db.parse(new InputSource(new StringReader(xmlString)));
		
		List<String> values = new ArrayList<String>();
		NodeList list = document.getElementsByTagName(node);
		for (int i = 0; i < list.getLength(); i++) {
			if (node.equals(list.item(i).getNodeName())) {
				values.add(list.item(i).getFirstChild().getNodeValue());
//				System.out.println(list.item(i).getFirstChild().getNodeValue());
//				System.out.println(list.item(i).getNodeValue());
			}
		}
		return values;
	}

}
