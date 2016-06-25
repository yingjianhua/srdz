Ext.define('mvc.combo.wpt.WptOStatus',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 0,text : '未付款'}
	,{value : 1,text : '未受理'}
	,{value : 2,text : '已受理'}
	,{value : 3,text : '已付定金'}
	,{value : 4,text : '已付款'}
	,{value : 5,text : '已完成'}
	,{value : 6,text : '已关闭'}
	,{value : 7,text : '申请取消订单'}
	,{value : 8,text : '申请退款'}
	]
});