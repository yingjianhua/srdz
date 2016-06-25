Ext.define('mvc.combo.sys.SysOCardPersonType',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 0,text : '无'}
	,{value : 1,text : '身份证'}
	,{value : 2,text : '护照'}
	,{value : 3,text : '军人证'}
	,{value : 4,text : '驾驶证'}
	]
});