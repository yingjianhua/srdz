Ext.define('mvc.model.sys.SysEm',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/sys_SysEm_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.code',mapping : 'code',type : 'string'}
	,{name : 'bean.name',mapping : 'name',type : 'string'}
	,{name : 'bean.nickname',mapping : 'nickname',type : 'string'}
	,{name : 'bean.state',mapping : 'state',type : 'int',useNull : true}
	,{name : 'bean.org',mapping : 'org',type : 'string',outkey : true}
	,{name : 'bean.dept',mapping : 'dept',type : 'string',outkey : true}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	,{name : 'one.peCardType',mapping : 'peCardType',type : 'int',useNull : true}
	,{name : 'one.peCardNumb',mapping : 'peCardNumb',type : 'string'}
	,{name : 'one.peMobile',mapping : 'peMobile',type : 'string'}
	,{name : 'one.peEmail',mapping : 'peEmail',type : 'string'}
	,{name : 'one.peWx',mapping : 'peWx',type : 'string'}
	,{name : 'one.peQq',mapping : 'peQq',type : 'string'}
	,{name : 'one.peSex',mapping : 'peSex',type : 'int',useNull : true}
	,{name : 'one.peBirthday',mapping : 'peBirthday',type : 'date'}
	,{name : 'one.peMerry',mapping : 'peMerry',type : 'int',useNull : true}
	,{name : 'one.hoTel',mapping : 'hoTel',type : 'string'}
	,{name : 'one.hoAddr',mapping : 'hoAddr',type : 'string'}
	,{name : 'one.hoZipCode',mapping : 'hoZipCode',type : 'string'}
	,{name : 'one.rem',mapping : 'rem',type : 'string'}
	,{name : 'one.updatedBy',mapping : 'updatedBy',type : 'string',outkey : true}
	,{name : 'one.updatedDateTime',mapping : 'updatedDateTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'one.createdBy',mapping : 'createdBy',type : 'string',outkey : true}
	,{name : 'one.createdDateTime',mapping : 'createdDateTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'user.loginName',mapping : 'loginName',type : 'string'}
	]
});