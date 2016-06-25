Ext.define('mvc.model.wx.WxActionRecord',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wx_WxActionRecord_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.actionName',mapping : 'actionName',type : 'string'}
	,{name : 'bean.dealPriod',mapping : 'dealPriod',type : 'int',useNull : true}
	,{name : 'bean.visitTime',mapping : 'visitTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.visitHost',mapping : 'visitHost',type : 'string'}
	,{name : 'bean.successFlag',mapping : 'successFlag',type : 'int',useNull : true}
	]
});