Ext.define('mvc.model.wpt.WptCommissionJournal',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptCommissionJournal_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.orderid',mapping : 'orderid',type : 'string'}
	,{name : 'bean.status',mapping : 'status',type : 'int',useNull : true}
	,{name : 'bean.createTime',mapping : 'createTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.price',mapping : 'price',type : 'float',useNull : true}
	,{name : 'bean.commission',mapping : 'commission',type : 'float',useNull : true}
	,{name : 'bean.fans',mapping : 'fans',type : 'string',outkey : true}
	,{name : 'bean.imageUrl',mapping : 'imageUrl',type : 'string'}
	,{name : 'bean.nickname',mapping : 'nickname',type : 'string'}
	,{name : 'bean.wxuser',mapping : 'wxuser',type : 'string',outkey : true}
	,{name : 'bean.account',mapping : 'account',type : 'string',outkey : true}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});