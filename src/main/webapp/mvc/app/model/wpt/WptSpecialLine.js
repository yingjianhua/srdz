Ext.define('mvc.model.wpt.WptSpecialLine',{
extend : 'Ext.data.Model',
idProperty : 'pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptSpecialLine_load'
},
fields : [{name : 'pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'special.pkey',mapping : 'special.pkey',type : 'string',outkey : true,
		 convert: function(value, record) {
			 if(record.raw.special)
				 return record.raw.special.pkey+bean_split+record.raw.special.name;
			 return value;
         }}
	,{name : 'restaurant.pkey',mapping : 'restaurant.pkey',type : 'string',outkey : true,
		 convert: function(value, record) {
			 if(record.raw.restaurant)
				 return record.raw.restaurant.pkey+bean_split+record.raw.restaurant.name;
			 return value;
         }}
	,{name : 'sort',mapping : 'sort',type : 'int',useNull : true}
	,{name : 'account',mapping : 'account',type : 'string',outkey : true}
	,{name : 'rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});