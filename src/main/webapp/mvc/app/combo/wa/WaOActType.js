Ext.define('mvc.combo.wa.WaOActType',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 1,text : '投票活动'}
	,{value : 2,text : '抽奖活动'}
	]
});