Ext.define('mvc.model.wpt.WptCommissionJournal',{
extend : 'Ext.data.Model',
idProperty : 'pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptCommissionJournal_load'
},
fields : [{name : 'pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'orderid',mapping : 'orderid',type : 'string'}
	,{name : 'status',mapping : 'status',type : 'int',useNull : true}
	,{name : 'createTime',mapping : 'createTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'price',mapping : 'price',type : 'float',useNull : true}
	,{name : 'commission',mapping : 'commission',type : 'float',useNull : true}
	,{name : 'fan.pkey',mapping : 'fan.pkey',type : 'string',outkey : true,
		 convert: function(value, record) {
			 if(record.raw.city)
				 return record.raw.fan.pkey+bean_split+record.raw.fan.nickname;
			 return value;
         }}
	,{name : 'imageUrl',mapping : 'imageUrl',type : 'string'}
	,{name : 'nickname',mapping : 'nickname',type : 'string'}
	,{name : 'member.pkey',mapping : 'member.pkey',type : 'string',outkey : true,
		 convert: function(value, record) {
			 if(record.raw.city)
				 return record.raw.member.pkey+bean_split+record.raw.member.nickname;
			 return value;
         }}
	,{name : 'account',mapping : 'account',type : 'string',outkey : true}
	,{name : 'rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});