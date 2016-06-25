Ext.define('mvc.model.sys.SysBranch',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/sys_SysBranch_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'string',outkey : true}
	,{name : 'bean.org',mapping : 'org',type : 'string',outkey : true}
	,{name : 'bean.addr',mapping : 'addr',type : 'string'}
	,{name : 'bean.zip',mapping : 'zip',type : 'string'}
	,{name : 'bean.tel',mapping : 'tel',type : 'string'}
	,{name : 'bean.enabled',mapping : 'enabled',type : 'int',useNull : true}
	,{name : 'bean.rem',mapping : 'rem',type : 'string'}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});