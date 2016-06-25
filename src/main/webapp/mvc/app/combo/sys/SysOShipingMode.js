Ext.define('mvc.combo.sys.SysOShipingMode',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 1,text : '不需运输'}
	,{value : 2,text : '自提'}
	,{value : 10,text : '快递'}
	,{value : 30,text : '陆运'}
	,{value : 50,text : '海运'}
	,{value : 70,text : '空运'}
	,{value : 99,text : '其它'}
	]
});