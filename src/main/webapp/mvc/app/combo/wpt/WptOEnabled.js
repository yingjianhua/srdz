Ext.define('mvc.combo.wpt.WptOEnabled',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : true,text : '是'}
	,{value : false,text : '否'}
	]
});