Ext.define('mvc.model.wpt.WptCombo',{
extend : 'Ext.data.Model',
idProperty : 'pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptCombo_load'
},
fields : [{name : 'pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'restaurant.pkey',mapping : 'restaurant.pkey',type : 'string',outkey : true,
		 convert: function(value, record) {
			 return record.raw.restaurant.pkey+bean_split+record.raw.restaurant.name;
         }}
	,{name : 'name',mapping : 'name',type : 'string'}
	,{name : 'imgUrl',mapping : 'imgUrl',type : 'string'}
	,{name : 'des',mapping : 'des',type : 'string'}
	,{name : 'origPrice',mapping : 'origPrice',type : 'float',useNull : true}
	,{name : 'price',mapping : 'price',type : 'float',useNull : true}
	,{name : 'numberMin',mapping : 'numberMin',type : 'int',useNull : true}
	,{name : 'numberMax',mapping : 'numberMax',type : 'int',useNull : true}
	,{name : 'serviceDate',mapping : 'serviceDate',type : 'string'}
	,{name : 'serviceTime',mapping : 'serviceTime',type : 'string'}
	,{name : 'rem',mapping : 'rem',type : 'string'}
	,{name : 'sort',mapping : 'sort',type : 'int', defaultValue : 0, useNull : true}
	,{name : 'enabled',mapping : 'enabled',type : 'boolean',useNull : true}	
	,{name : 'rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});