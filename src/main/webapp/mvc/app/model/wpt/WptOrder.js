Ext.define('mvc.model.wpt.WptOrder',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptOrder_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.orderid',mapping : 'orderid',type : 'string'}
	,{name : 'bean.depPayId',mapping : 'depPayId',type : 'string'}
	,{name : 'bean.outRefundNo',mapping : 'outRefundNo',type : 'string'}
	,{name : 'bean.wxuser',mapping : 'wxuser',type : 'string',outkey : true}
	,{name : 'bean.restaurant',mapping : 'restaurant',type : 'string',outkey : true}
	,{name : 'bean.banquet',mapping : 'banquet',type : 'string',outkey : true}
	,{name : 'bean.time',mapping : 'time',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.number',mapping : 'number',type : 'int',useNull : true}
	,{name : 'bean.consumption',mapping : 'consumption',type : 'float',useNull : true}
	,{name : 'bean.city',mapping : 'city',type : 'string',outkey : true}
	,{name : 'bean.createTime',mapping : 'createTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.status',mapping : 'status',type : 'int',useNull : true}
	,{name : 'bean.deposit',mapping : 'deposit',type : 'float',useNull : true}
	,{name : 'bean.residue',mapping : 'residue',type : 'float',useNull : true}
	,{name : 'bean.contactMan',mapping : 'contactMan',type : 'string'}
	,{name : 'bean.contactSex',mapping : 'contactSex',type : 'int',useNull : true}
	,{name : 'bean.contactWay',mapping : 'contactWay',type : 'string'}
	,{name : 'bean.contactType',mapping : 'contactType',type : 'int',useNull : true}
	,{name : 'bean.rem',mapping : 'rem',type : 'string'}
	,{name : 'bean.comboName',mapping : 'comboName',type : 'string'}
	,{name : 'bean.price',mapping : 'price',type : 'float',useNull : true}
	,{name : 'bean.isPt',mapping : 'isPt',type : 'int',useNull : true}
	,{name : 'bean.checkcode',mapping : 'checkcode',type : 'string'}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});