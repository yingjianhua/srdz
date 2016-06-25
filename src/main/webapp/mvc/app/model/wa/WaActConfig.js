Ext.define('mvc.model.wa.WaActConfig',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wa_WaActConfig_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.name',mapping : 'name',type : 'string'}
	,{name : 'bean.cycle',mapping : 'cycle',type : 'int',useNull : true}
	,{name : 'bean.unit',mapping : 'unit',type : 'int',useNull : true}
	,{name : 'bean.cycleLimit',mapping : 'cycleLimit',type : 'int',useNull : true}
	,{name : 'bean.cycleLimitWords',mapping : 'cycleLimitWords',type : 'string'}
	,{name : 'bean.ipLimit',mapping : 'ipLimit',type : 'int',useNull : true}
	,{name : 'bean.ipLimitWords',mapping : 'ipLimitWords',type : 'string'}
	,{name : 'bean.viewRate',mapping : 'viewRate',type : 'int',useNull : true}
	,{name : 'bean.entryRate',mapping : 'entryRate',type : 'int',useNull : true}
	,{name : 'bean.actRate',mapping : 'actRate',type : 'int',useNull : true}
	,{name : 'bean.imageShape',mapping : 'imageShape',type : 'int',useNull : true}
	,{name : 'bean.customMenu',mapping : 'customMenu',type : 'int',useNull : true}
	,{name : 'bean.resArea',mapping : 'resArea',type : 'int',useNull : true}
	,{name : 'bean.resAreaWords',mapping : 'resAreaWords',type : 'string'}
	,{name : 'bean.area',mapping : 'area',type : 'string'}
	,{name : 'bean.invalidArea',mapping : 'invalidArea',type : 'string'}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});