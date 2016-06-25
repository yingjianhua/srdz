Ext.define('mvc.model.sys.SysOrg',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/sys_SysOrg_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.code',mapping : 'code',type : 'string'}
	,{name : 'bean.name',mapping : 'name',type : 'string'}
	,{name : 'bean.shortName',mapping : 'shortName',type : 'string'}
	,{name : 'bean.enabled',mapping : 'enabled',type : 'int',useNull : true}
	,{name : 'bean.orgUp',mapping : 'orgUp',type : 'string',outkey : true}
	,{name : 'bean.workDate',mapping : 'workDate',type : 'date'}
	,{name : 'bean.state',mapping : 'state',type : 'int',useNull : true}
	,{name : 'bean.templat',mapping : 'templat',type : 'string',outkey : true}
	,{name : 'bean.valuationMethods',mapping : 'valuationMethods',type : 'int',useNull : true}
	,{name : 'bean.internationTrade',mapping : 'internationTrade',type : 'int',useNull : true}
	,{name : 'bean.currency',mapping : 'currency',type : 'int',useNull : true}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	,{name : 'one.tel1',mapping : 'tel1',type : 'string'}
	,{name : 'one.tel2',mapping : 'tel2',type : 'string'}
	,{name : 'one.fax',mapping : 'fax',type : 'string'}
	,{name : 'one.website',mapping : 'website',type : 'string'}
	,{name : 'one.addr',mapping : 'addr',type : 'string'}
	,{name : 'one.zipCode',mapping : 'zipCode',type : 'string'}
	,{name : 'one.rem',mapping : 'rem',type : 'string'}
	,{name : 'one.updatedBy',mapping : 'updatedBy',type : 'string',outkey : true}
	,{name : 'one.updatedDateTime',mapping : 'updatedDateTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'one.createdBy',mapping : 'createdBy',type : 'string',outkey : true}
	,{name : 'one.createdDateTime',mapping : 'createdDateTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'one.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});