Ext.define('mvc.combo.sys.SysOCurrency',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 1,text : '人民币'}
	,{value : 2,text : '美元'}
	,{value : 3,text : '欧元'}
	,{value : 4,text : '港币'}
	,{value : 5,text : '日元'}
	,{value : 6,text : '韩币'}
	]
});