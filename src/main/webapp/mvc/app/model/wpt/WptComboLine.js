Ext.define('mvc.model.wpt.WptComboLine',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptComboLine_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.combo',mapping : 'combo',type : 'string',outkey : true,
		 convert: function(value, record) {
			 return record.raw.combo.pkey+bean_split+record.raw.combo.name;
         }
	}
	,{name : 'bean.menu',mapping : 'menu',type : 'string',outkey : true,
		 convert: function(value, record) {
			 return record.raw.menu.pkey+bean_split+record.raw.menu.name;
         }}
	,{name : 'bean.price',mapping : 'price',type : 'float',useNull : true}
	,{name : 'bean.account',mapping : 'account',type : 'string',outkey : true}
	,{name : 'bean.sort',mapping : 'sort',type : 'int',useNull : true, defaultValue:0}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});