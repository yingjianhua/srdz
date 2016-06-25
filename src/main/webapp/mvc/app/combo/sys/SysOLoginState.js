Ext.define('mvc.combo.sys.SysOLoginState',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 0,text : '未登录'}
	,{value : 1,text : 'PC在线'}
	,{value : 2,text : 'WAP在线'}
	,{value : 3,text : '非法密码锁定'}
	,{value : 4,text : '停用'}
	,{value : 5,text : '未激活'}
	]
});