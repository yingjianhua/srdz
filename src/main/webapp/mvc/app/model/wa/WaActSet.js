Ext.define('mvc.model.wa.WaActSet',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wa_WaActSet_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.act',mapping : 'act',type : 'string',outkey : true}
	,{name : 'bean.actItem',mapping : 'actItem',type : 'string',outkey : true}
	,{name : 'bean.actPrize',mapping : 'actPrize',type : 'string',outkey : true}
	,{name : 'bean.actNum',mapping : 'actNum',type : 'int',useNull : true}
	,{name : 'bean.account',mapping : 'account',type : 'string',outkey : true}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});