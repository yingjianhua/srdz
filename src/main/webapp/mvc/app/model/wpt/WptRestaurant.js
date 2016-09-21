Ext.define('mvc.model.wpt.WptRestaurant',{
extend : 'Ext.data.Model',
idProperty : 'pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptRestaurant_load'
},
fields : [{name : 'pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'name',mapping : 'name',type : 'string'}
	,{name : 'mobile',mapping : 'mobile',type : 'string'}
	,{name : 'manager',mapping : 'manager',type : 'string'}
	,{name : 'city.pkey',mapping : 'city.pkey',type : 'string',outkey : true,
		 convert: function(value, record) {
			 if(record.raw.city)
				 return record.raw.city.pkey+bean_split+record.raw.city.name;
			 return value;
         }}
	,{name : 'cityline.pkey',mapping : 'cityline.pkey',type : 'string',outkey : true,
		 convert: function(value, record) {
			 if(record.raw.cityline)
				 return record.raw.cityline.pkey+bean_split+record.raw.cityline.name;
			 return value;
         }}
	,{name : 'addr',mapping : 'addr',type : 'string'}
	,{name : 'longitude',mapping : 'longitude',type : 'float'}
	,{name : 'latitude',mapping : 'latitude',type : 'float'}
	,{name : 'imgUrl',mapping : 'imgUrl',type : 'string'}
	,{name : 'rem',mapping : 'rem',type : 'string'}
	,{name : 'display',mapping : 'display',type : 'int',useNull : true}
	,{name : 'des',mapping : 'des',type : 'string'}
	,{name : 'startdate',mapping : 'startdate',type : 'string'}
	,{name : 'stopdate',mapping : 'stopdate',type : 'string'}
	,{name : 'consumption',mapping : 'consumption',type : 'float',useNull : true}
	,{name : 'wifiaccount',mapping : 'wifiaccount',type : 'string'}
	,{name : 'wifipassword',mapping : 'wifipassword',type : 'string'}
	,{name : 'template',mapping : 'template',type : 'string',outkey : true}
	,{name : 'enabled',mapping : 'enabled',type : 'int',useNull : true}
	,{name : 'rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});