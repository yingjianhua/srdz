Ext.define('mvc.model.wpt.WptComboBanner',{
extend : 'Ext.data.Model',
idProperty : 'pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptComboBanner_load'
},
fields : [{name : 'pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'combo.pkey',mapping : 'combo.pkey',type : 'string',outkey : true,
		 convert: function(value, record) {
			 if(record.raw.city)
				 return record.raw.combo.pkey+bean_split+record.raw.combo.name;
			 return value;
         }}
	,{name : 'imgUrl',mapping : 'imgUrl',type : 'string'}
	,{name : 'sort',mapping : 'sort',type : 'int',useNull : true}
	,{name : 'rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});