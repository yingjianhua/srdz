Ext.define('mvc.model.wpt.WptOrderLine',{
extend : 'Ext.data.Model',
idProperty : 'pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptOrderLine_load'
},
fields : [{name : 'pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'wptorder.pkey',mapping : 'wptorder.pkey',type : 'string',outkey : true,
		 convert: function(value, record) {
			 if(record.raw.city)
				 return record.raw.city.pkey+bean_split+record.raw.city.name;
			 return value;
         }}
	,{name : 'name',mapping : 'name',type : 'string'}
	,{name : 'des',mapping : 'des',type : 'string'}
	,{name : 'price',mapping : 'price',type : 'float',useNull : true}
	,{name : 'number',mapping : 'number',type : 'int',useNull : true}
	,{name : 'account',mapping : 'account',type : 'string',outkey : true}
	,{name : 'rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});