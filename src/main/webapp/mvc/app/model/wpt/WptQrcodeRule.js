Ext.define('mvc.model.wpt.WptQrcodeRule',{
extend : 'Ext.data.Model',
idProperty : 'pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptQrcodeRule_load'
},
fields : [{name : 'pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'single',mapping : 'single',type : 'float',useNull : true}
	,{name : 'amount',mapping : 'amount',type : 'float',useNull : true}
	,{name : 'validityPeriod',mapping : 'validityPeriod',type : 'int',useNull : true}
	,{name : 'aheadUpdate',mapping : 'aheadUpdate',type : 'int',useNull : true}
	,{name : 'rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});