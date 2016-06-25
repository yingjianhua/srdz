Ext.define('mvc.combo.wa.WaOActPageType',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 1,text : '首页'}
	,{value : 2,text : '报名页'}
	,{value : 3,text : '排行页'}
	,{value : 4,text : '详情页'}
	,{value : 99,text : '自定义页'}
	]
});