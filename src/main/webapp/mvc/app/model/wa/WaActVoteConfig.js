Ext.define('mvc.model.wa.WaActVoteConfig',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wa_WaActVoteConfig_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.name',mapping : 'name',type : 'string'}
	,{name : 'bean.entryAppr',mapping : 'entryAppr',type : 'int',useNull : true}
	,{name : 'bean.picLimit',mapping : 'picLimit',type : 'int',useNull : true}
	,{name : 'bean.showRanking',mapping : 'showRanking',type : 'int',useNull : true}
	,{name : 'bean.picWidth',mapping : 'picWidth',type : 'int',useNull : true}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});