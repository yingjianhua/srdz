Ext.define('mvc.model.wpt.WptRestaurantBsn',{
extend : 'Ext.data.Model',
idProperty : 'pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptRestaurantBsn_load'
},
fields : [{name : 'pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'restaurant.pkey',mapping : 'restaurant.pkey',type : 'string',outkey : true,
		 convert: function(value, record) {
			 if(record.raw.restaurant)
				 return record.raw.restaurant.pkey+bean_split+record.raw.restaurant.name;
			 return value;
         }}
	,{name : 'member.pkey',mapping : 'member.pkey',type : 'string',outkey : true,
		 convert: function(value, record) {
			 if(record.raw.member)
				 return record.raw.member.pkey+bean_split+record.raw.member.nickname;
			 return value;
         }}
	,{name : 'createTime',mapping : 'createTime',type : 'date',dateFormat : 'Y-m-dTH:i:s'}
	,{name : 'rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});