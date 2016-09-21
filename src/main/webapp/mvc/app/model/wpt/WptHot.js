Ext.define('mvc.model.wpt.WptHot',{
extend : 'Ext.data.Model',
idProperty : 'pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptHot_load'
},
fields : [{name : 'pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'city.pkey',mapping : 'city.pkey',type : 'string',outkey : true,
		 convert: function(value, record) {
			 if(record.raw.city)
				 return record.raw.city.pkey+bean_split+record.raw.city.name;
			 return value;
         }}
	,{name : 'restaurant.pkey',mapping : 'restaurant.pkey',type : 'string',outkey : true,
		 convert: function(value, record) {
			 if(record.raw.city)
				 return record.raw.restaurant.pkey+bean_split+record.raw.restaurant.name;
			 return value;
         }}
	,{name : 'sort',mapping : 'sort',type : 'int',useNull : true}
	,{name : 'rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});