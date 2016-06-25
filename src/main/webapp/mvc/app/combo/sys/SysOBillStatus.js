Ext.define('mvc.combo.sys.SysOBillStatus',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 11,text : '初始'}
	,{value : 21,text : '已输入确认'}
	,{value : 53,text : '复核中'}
	,{value : 58,text : '已复核'}
	,{value : 63,text : '审核中'}
	,{value : 68,text : '已审核'}
	,{value : 73,text : '审批中'}
	,{value : 78,text : '已审批'}
	,{value : 81,text : '已出入库'}
	,{value : 83,text : '可记账'}
	,{value : 98,text : '完成'}
	,{value : 99,text : '作废'}
	]
});