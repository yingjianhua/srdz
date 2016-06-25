Ext.define('mvc.model.wa.WaActHomeTemplateBarnner',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wa_WaActHomeTemplateBarnner_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.homeTemp',mapping : 'homeTemp',type : 'string',outkey : true}
	,{name : 'bean.picUrl',mapping : 'picUrl',type : 'string'}
	,{name : 'bean.url',mapping : 'url',type : 'string'}
	,{name : 'bean.description',mapping : 'description',type : 'string'}
	,{name : 'bean.account',mapping : 'account',type : 'string',outkey : true}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});