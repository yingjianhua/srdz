Ext.define('mvc.model.sys.SysCom',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/sys_SysCom_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.name',mapping : 'name',type : 'string'}
	,{name : 'bean.shortName',mapping : 'shortName',type : 'string'}
	,{name : 'bean.tel1',mapping : 'tel1',type : 'string'}
	,{name : 'bean.tel2',mapping : 'tel2',type : 'string'}
	,{name : 'bean.fax',mapping : 'fax',type : 'string'}
	,{name : 'bean.website',mapping : 'website',type : 'string'}
	,{name : 'bean.addr',mapping : 'addr',type : 'string'}
	,{name : 'bean.zipCode',mapping : 'zipCode',type : 'string'}
	,{name : 'bean.rem',mapping : 'rem',type : 'string'}
	,{name : 'bean.updatedBy',mapping : 'updatedBy',type : 'string',outkey : true}
	,{name : 'bean.updatedDateTime',mapping : 'updatedDateTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.createdBy',mapping : 'createdBy',type : 'string',outkey : true}
	,{name : 'bean.createdDateTime',mapping : 'createdDateTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});