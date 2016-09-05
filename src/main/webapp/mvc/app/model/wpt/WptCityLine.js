Ext.define('mvc.model.wpt.WptCityLine',{
extend : 'Ext.data.Model',
idProperty : 'pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptCityLine_load'
},
fields : [{name : 'pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'city.pkey',mapping : 'city.pkey',type : 'string',outkey : true,
		 convert: function(value, record) {
			 if(record.raw.city)
				 return record.raw.city.pkey+bean_split+record.raw.city.name;
			 return value;
         }}
	,{name : 'name',mapping : 'name',type : 'string'}
	,{name : 'account',mapping : 'account',type : 'string',outkey : true}
	,{name : 'rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});