Ext.define('mvc.model.wa.WaActGet',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wa_WaActGet_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.act',mapping : 'act',type : 'string',outkey : true}
	,{name : 'bean.recName',mapping : 'recName',type : 'string'}
	,{name : 'bean.recAddr',mapping : 'recAddr',type : 'string'}
	,{name : 'bean.recMobile',mapping : 'recMobile',type : 'string'}
	,{name : 'bean.actItem',mapping : 'actItem',type : 'string',outkey : true}
	,{name : 'bean.actPrize',mapping : 'actPrize',type : 'string',outkey : true}
	,{name : 'bean.actTime',mapping : 'actTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.wxUser',mapping : 'wxUser',type : 'string',outkey : true}
	,{name : 'bean.wxCard',mapping : 'wxCard',type : 'string'}
	,{name : 'bean.actKey',mapping : 'actKey',type : 'string'}
	,{name : 'bean.sendStatus',mapping : 'sendStatus',type : 'int',useNull : true}
	,{name : 'bean.sendName',mapping : 'sendName',type : 'string'}
	,{name : 'bean.sendNum',mapping : 'sendNum',type : 'string'}
	,{name : 'bean.sendTime',mapping : 'sendTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});