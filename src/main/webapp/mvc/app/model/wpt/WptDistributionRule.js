Ext.define('mvc.model.wpt.WptDistributionRule',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptDistributionRule_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.bonus1',mapping : 'bonus1',type : 'int',useNull : true}
	,{name : 'bean.bonus2',mapping : 'bonus2',type : 'int',useNull : true}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});