Ext.define('mvc.model.wpt.WptOrder',{
extend : 'Ext.data.Model',
idProperty : 'pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptOrder_load'
},
fields : [{name : 'pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'orderid',mapping : 'orderid',type : 'string'}
	,{name : 'status',mapping : 'status',type : 'int',useNull : true}
	,{name : 'restaurant.pkey',mapping : 'restaurant',type : 'string',outkey : true,
		 convert: function(value, record) {
			 if(record.raw.restaurant)
				 return record.raw.restaurant.pkey+bean_split+record.raw.restaurant.name;
			 return value;
         }}
	,{name : 'restaurantName',mapping : 'restaurantName',type : 'string'}
	,{name : 'combo.pkey',mapping : 'combo',type : 'string',outkey : true,
		 convert: function(value, record) {
			 if(record.raw.combo)
				 return record.raw.combo.pkey+bean_split+record.raw.combo.name;
			 return value;
         }}
	,{name : 'comboName',mapping : 'comboName',type : 'string'}
	,{name : 'member.pkey',mapping : 'member',type : 'string',outkey : true,
		 convert: function(value, record) {
			 if(record.raw.member)
				 return record.raw.member.pkey+bean_split+record.raw.member.nickname;
			 return value;
         }}
	,{name : 'number',mapping : 'number',type : 'int',useNull : true}
	,{name : 'deposit',mapping : 'deposit',type : 'float',useNull : true}
	,{name : 'price',mapping : 'price',type : 'float',useNull : true}
	,{name : 'payment',mapping : 'payment',type : 'float',useNull : true}
	,{name : 'time',mapping : 'time',type : 'date',dateFormat : 'Y-m-d H:i'}
	,{name : 'checkcode',mapping : 'checkcode',type : 'string'}
	,{name : 'contactMan',mapping : 'contactMan',type : 'string'}
	,{name : 'contactSex',mapping : 'contactSex',type : 'int',useNull : true}
	,{name : 'contactType',mapping : 'contactType',type : 'int',useNull : true}
	,{name : 'contactWay',mapping : 'contactWay',type : 'string'}
	,{name : 'city.pkey',mapping : 'city',type : 'string',outkey : true,
		 convert: function(value, record) {
			 if(record.raw.city)
				 return record.raw.city.pkey+bean_split+record.raw.city.name;
			 return value;
         }}
	,{name : 'rem',mapping : 'rem',type : 'string'}
	,{name : 'createTime',mapping : 'createTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});