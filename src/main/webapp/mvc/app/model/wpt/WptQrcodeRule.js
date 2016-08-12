Ext.define('mvc.model.wpt.WptQrcodeRule',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptQrcodeRule_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.single',mapping : 'single',type : 'float',useNull : true}
	,{name : 'bean.amount',mapping : 'amount',type : 'float',useNull : true}
	,{name : 'bean.validityPeriod',mapping : 'validityPeriod',type : 'int',useNull : true}
	,{name : 'bean.aheadUpdate',mapping : 'aheadUpdate',type : 'int',useNull : true}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});