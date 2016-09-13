package irille.wpt.tools;

public class RangeConditionTool {
	private static final String pattern1 = ",(\\d+)";
	private static final String pattern2 = "(\\d+,)";
	private static final String pattern3 = "(\\d+),(\\d+)";
	private static final String pattern4 = "(\\d+)";
	//将表单中的条件转换成用于显示的格式
	public static String condition2Display1(String condition, String unit) {
		if(condition.matches(pattern1)) {
			return condition.substring(1)+unit+"以下";
		} else if(condition.matches(pattern2)) {
			return condition.substring(0, condition.length()-1)+unit+"以上";
		} else if(condition.matches(pattern3)) {
			return condition.replace(",", "-")+unit;
		} else if(condition.matches(pattern4)){
			return condition+unit;
		} else {
			return condition;
		}
	}
}
