Ext.define('mvc.model.wpt.WptComboLine',{
extend : 'Ext.data.Model',
idProperty : 'pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptComboLine_load'
},
fields : [{name : 'pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'combo.pkey',mapping : 'combo.pkey',type : 'string',outkey : true,
		 convert: function(value, record) {
			 if(record.raw.combo)
				 return record.raw.combo.pkey+bean_split+record.raw.combo.name;
			 return value;
         }
	}
	,{name : 'menu.pkey',mapping : 'menu.pkey',type : 'string',outkey : true,
		 convert: function(value, record) {
			 if(record.raw.menu)
				 return record.raw.menu.pkey+bean_split+record.raw.menu.name;
			 return value;
         }}
	,{name : 'price',mapping : 'price',type : 'float',useNull : true}
	,{name : 'account',mapping : 'account',type : 'string',outkey : true}
	,{name : 'sort',mapping : 'sort',type : 'int',useNull : true, defaultValue:0}
	,{name : 'rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});