Ext.define('mvc.model.wpt.WptComboLine',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptComboLine_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.combo.pkey',mapping : 'combo.pkey',type : 'string',outkey : true,
		 convert: function(value, record) {
			 if(record.raw.combo)
				 return record.raw.combo.pkey+bean_split+record.raw.combo.name;
			 return value;
         }
	}
	,{name : 'bean.menu.pkey',mapping : 'menu.pkey',type : 'string',outkey : true,
		 convert: function(value, record) {
			 if(record.raw.menu)
				 return record.raw.menu.pkey+bean_split+record.raw.menu.name;
			 return value;
         }}
	,{name : 'bean.price',mapping : 'price',type : 'float',useNull : true}
	,{name : 'bean.account',mapping : 'account',type : 'string',outkey : true}
	,{name : 'bean.sort',mapping : 'sort',type : 'int',useNull : true, defaultValue:0}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});