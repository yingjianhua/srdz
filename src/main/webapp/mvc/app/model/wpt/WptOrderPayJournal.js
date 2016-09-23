Ext.define('mvc.model.wpt.WptOrderPayJournal',{
extend : 'Ext.data.Model',
idProperty : 'pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptOrderPayJournal_load'
},
fields : [{name : 'pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'order.pkey',mapping : 'order.pkey',type : 'string',outkey : true,
		 convert: function(value, record) {
			 if(record.raw.city)
				 return record.raw.order.pkey+bean_split+record.raw.order.orderid;
			 return value;
	    }}
	,{name : 'income',mapping : 'income',type : 'string'}
	,{name : 'outTradeNo',mapping : 'outTradeNo',type : 'string'}
	,{name : 'outRefundNo',mapping : 'outRefundNo',type : 'string'}
	,{name : 'price',mapping : 'price',type : 'float',useNull : true}
	,{name : 'createTime',mapping : 'createTime',type : 'date',dateFormat : 'Y-m-dTH:i:s'}
	,{name : 'payTime',mapping : 'payTime',type : 'date',dateFormat : 'Y-m-dTH:i:s'}
	,{name : 'refundTime',mapping : 'refundTime',type : 'date',dateFormat : 'Y-m-dTH:i:s'}
	,{name : 'payChannel',mapping : 'payChannel',type : 'int',useNull : true}
	,{name : 'userId',mapping : 'int',type : 'string'}
	]
});