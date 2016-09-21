Ext.define('mvc.model.wpt.WptRestaurantBanner',{
extend : 'Ext.data.Model',
idProperty : 'pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptRestaurantBanner_load'
},
fields : [{name : 'pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'restaurant.pkey',mapping : 'restaurant.pkey',type : 'string',outkey : true,
		 convert: function(value, record) {
			 if(record.raw.restaurant)
				 return record.raw.restaurant.pkey+bean_split+record.raw.restaurant.name;
			 return value;
         }}
	,{name : 'imgUrl',mapping : 'imgUrl',type : 'string'}
	,{name : 'sort',mapping : 'sort',type : 'int',useNull : true}
	,{name : 'rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});