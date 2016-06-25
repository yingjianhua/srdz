Ext.define('mvc.combo.sys.SysOAccType',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 1,text : '文件'}
	,{value : 2,text : '图片'}
	,{value : 3,text : '成果'}
	]
});