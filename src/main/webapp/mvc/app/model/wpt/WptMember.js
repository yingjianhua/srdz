Ext.define('mvc.model.wpt.WptMember',{
extend : 'Ext.data.Model',
idProperty : 'pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptUser_load'
},
fields : [{name : 'pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'status',mapping : 'status',type : 'int',useNull : true}
	,{name : 'openId',mapping : 'openId',type : 'string'}
	,{name : 'invited1.pkey',mapping : 'invited1.pkey',type : 'string',outkey : true,
		 convert: function(value, record) {
			 if(record.raw.invited1)
				 return record.raw.invited1.pkey+bean_split+record.raw.invited1.nickname;
			 return value;
         }}
	,{name : 'invited2.pkey',mapping : 'invited2.pkey',type : 'string',outkey : true,
		 convert: function(value, record) {
			 if(record.raw.invited2)
				 return record.raw.invited2.pkey+bean_split+record.raw.invited2.nickname;
			 return value;
         }}
	,{name : 'historyCommission',mapping : 'historyCommission',type : 'float',useNull : true}
	,{name : 'cashableCommission',mapping : 'cashableCommission',type : 'float',useNull : true}
	,{name : 'unionId',mapping : 'unionId',type : 'string'}
	,{name : 'nickname',mapping : 'nickname',type : 'string'}
	,{name : 'sex',mapping : 'sex',type : 'int',useNull : true}
	,{name : 'isMember',mapping : 'isMember',type : 'int',useNull : true}
	,{name : 'city',mapping : 'city',type : 'string'}
	,{name : 'province',mapping : 'province',type : 'string'}
	,{name : 'country',mapping : 'country',type : 'string'}
	,{name : 'imageUrl',mapping : 'imageUrl',type : 'string'}
	,{name : 'rem',mapping : 'rem',type : 'string'}
	,{name : 'qrcode',mapping : 'qrcode',type : 'string'}
	,{name : 'qrcodeExpireTime',mapping : 'qrcodeExpireTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'userGroup',mapping : 'userGroup',type : 'string',outkey : true}
	,{name : 'subscribeTime',mapping : 'subscribeTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'syncTime',mapping : 'syncTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'syncStatus',mapping : 'syncStatus',type : 'int',useNull : true}
	,{name : 'rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});