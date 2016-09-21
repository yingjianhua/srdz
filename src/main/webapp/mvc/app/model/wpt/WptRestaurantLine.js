Ext.define('mvc.model.wpt.WptRestaurantLine',{
extend : 'Ext.data.Model',
idProperty : 'pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptRestaurantLine_load'
},
fields : [{name : 'pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'restaurant.pkey',mapping : 'restaurant.pkey',type : 'string',outkey : true,
		 convert: function(value, record) {
			 if(record.raw.restaurant)
				 return record.raw.restaurant.pkey+bean_split+record.raw.restaurant.name;
			 return value;
         }}
	,{name : 'banquet.pkey',mapping : 'banquet.pkey',type : 'string',outkey : true,
		 convert: function(value, record) {
			 if(record.raw.banquet)
				 return record.raw.banquet.pkey+bean_split+record.raw.banquet.name;
			 return value;
         }}
	,{name : 'account',mapping : 'account',type : 'string',outkey : true}
	,{name : 'rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});