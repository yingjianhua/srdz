package irille.wpt.tools;

import java.lang.reflect.Method;
import java.util.Date;

import irille.core.sys.Sys;
import irille.pub.Str;

public class SqlBuilder {
	public static Boolean hasField(Class entityClass, String fieldName) {
		Method method = null;
		try {
			method = entityClass.getMethod("get"+Character.toUpperCase(fieldName.charAt(0))+fieldName.substring(1));
			if(method == null || method.getParameterTypes().length != 0) {
				return false;
			}
		} catch (SecurityException |NoSuchMethodException e) {
			return false;
		}
		return true;
	}
	public static String crtWhereSearch(Class entityClass, String fieldName, String param) {
		Method method = null;
		Class fieldType = null;
		try {
			method = entityClass.getMethod("get"+Character.toUpperCase(fieldName.charAt(0))+fieldName.substring(1));
			if(method == null || method.getParameterTypes().length != 0) {
				return "";
			}
		} catch (SecurityException |NoSuchMethodException e) {
			return "";
		}
		fieldType = method.getReturnType();
		String[] params = param.split("##");
		if (params.length < 2) { //普通搜索需要加入符号条件
			if(fieldType.equals(String.class))
				param = Sys.OOptCht.INC.getLine().getKey() + "##" + param;
			else
				param = Sys.OOptCht.EQU.getLine().getKey() + "##" + param;
			params = param.split("##");
		}
		if (Str.value(Sys.OOptCht.EMP).equals(params[0]))
			return fieldName + " IS NULL";
		if (Str.value(Sys.OOptCht.NOEMP).equals(params[0]))
			return fieldName + " IS NOT NULL";
		if (fieldType.equals(String.class)) {
			if (Str.value(Sys.OOptCht.INC).equals(params[0]))
				return fieldName + " like '%" + params[1] + "%'";
			if (Str.value(Sys.OOptCht.ST).equals(params[0]))
				return fieldName + " like '" + params[1] + "%'";
			if (Str.value(Sys.OOptCht.EQU).equals(params[0]))
				return fieldName + " = '" + params[1] + "'";
			if (Str.value(Sys.OOptCht.NOEQU).equals(params[0]))
				return fieldName + " <> '" + params[1] + "'";
		}
		if (fieldType.equals(Date.class)) {
			if (Str.value(Sys.OOptCht.EQU).equals(params[0]))
				return fieldName + " = '" + params[1] + "'";
			if (Str.value(Sys.OOptCht.NOEQU).equals(params[0]))
				return fieldName + " <> '" + params[1] + "'";
			if (Str.value(Sys.OOptCht.GT).equals(params[0]))
				return fieldName + " > '" + params[1] + "'";
			if (Str.value(Sys.OOptCht.LT).equals(params[0]))
				return fieldName + " < '" + params[1] + "'";
			if (Str.value(Sys.OOptCht.GE).equals(params[0]))
				return fieldName + " >= '" + params[1] + "'";
			if (Str.value(Sys.OOptCht.LE).equals(params[0]))
				return fieldName + " <= '" + params[1] + "'";
			if (Str.value(Sys.OOptCht.BTW).equals(params[0])) {
				if (params.length < 3)
					return fieldName + " >= '" + params[1] + "'";
				if (Str.isEmpty(params[1]))
					return fieldName + " <= '" + params[2] + "'";
				return fieldName + " >= '" + params[1] + "' AND " + fieldName + " <= '" + params[2]
				    + "'";
			}
		}
		if (Str.isNum(params[1])) {
			if (Str.value(Sys.OOptCht.EQU).equals(params[0]))
				return fieldName + " = " + params[1];
			if (Str.value(Sys.OOptCht.NOEQU).equals(params[0]))
				return fieldName + " <> " + params[1];
			if (Str.value(Sys.OOptCht.GT).equals(params[0]))
				return fieldName + " > " + params[1];
			if (Str.value(Sys.OOptCht.LT).equals(params[0]))
				return fieldName + " < " + params[1];
			if (Str.value(Sys.OOptCht.GE).equals(params[0]))
				return fieldName + " >= " + params[1];
			if (Str.value(Sys.OOptCht.LE).equals(params[0]))
				return fieldName + " <= " + params[1];
		}
		return ""; //TODO 其它特殊类型暂不考虑
	}
}
