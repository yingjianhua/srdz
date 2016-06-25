Ext.define('mvc.combo.sys.SysOOrgState',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 1,text : '日终处理结束'}
	,{value : 2,text : '营业中'}
	,{value : 3,text : '日终处理中...'}
	]
});