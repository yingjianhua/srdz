Ext.define('mvc.model.wpt.WptDistributionRule',{
extend : 'Ext.data.Model',
idProperty : 'pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptDistributionRule_load'
},
fields : [{name : 'pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bonus1',mapping : 'bonus1',type : 'int',useNull : true}
	,{name : 'bonus2',mapping : 'bonus2',type : 'int',useNull : true}
	,{name : 'rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});