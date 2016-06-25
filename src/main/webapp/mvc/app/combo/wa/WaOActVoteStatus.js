Ext.define('mvc.combo.wa.WaOActVoteStatus',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 0,text : '初始'}
	,{value : 1,text : '已发布'}
	,{value : 2,text : '报名结束'}
	,{value : 3,text : '活动结束'}
	,{value : 4,text : '已关闭'}
	]
});