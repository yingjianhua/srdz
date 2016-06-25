Ext.define('mvc.combo.sys.SysOEnabled',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 1,text : '启用'}
	,{value : 0,text : '停用'}
	]
});