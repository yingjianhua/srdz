Ext.define('mvc.model.sys.SysPersonLink',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/sys_SysPersonLink_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.name',mapping : 'name',type : 'string'}
	,{name : 'bean.tbObjLong',mapping : 'tbObjLong',type : 'string',outkey : true}
	,{name : 'bean.type',mapping : 'type',type : 'int',useNull : true}
	,{name : 'bean.peCardType',mapping : 'peCardType',type : 'int',useNull : true}
	,{name : 'bean.peCardNumb',mapping : 'peCardNumb',type : 'string'}
	,{name : 'bean.peMobile',mapping : 'peMobile',type : 'string'}
	,{name : 'bean.peMobileOther',mapping : 'peMobileOther',type : 'string'}
	,{name : 'bean.peEmail',mapping : 'peEmail',type : 'string'}
	,{name : 'bean.peWx',mapping : 'peWx',type : 'string'}
	,{name : 'bean.peQq',mapping : 'peQq',type : 'string'}
	,{name : 'bean.peWb',mapping : 'peWb',type : 'string'}
	,{name : 'bean.peMsn',mapping : 'peMsn',type : 'string'}
	,{name : 'bean.peSex',mapping : 'peSex',type : 'int',useNull : true}
	,{name : 'bean.peBirthday',mapping : 'peBirthday',type : 'date'}
	,{name : 'bean.peMerry',mapping : 'peMerry',type : 'int',useNull : true}
	,{name : 'bean.peEdu',mapping : 'peEdu',type : 'string'}
	,{name : 'bean.peDegree',mapping : 'peDegree',type : 'string'}
	,{name : 'bean.pePositionalTitle',mapping : 'pePositionalTitle',type : 'string'}
	,{name : 'bean.peDrivingLicense',mapping : 'peDrivingLicense',type : 'string'}
	,{name : 'bean.peParty',mapping : 'peParty',type : 'string'}
	,{name : 'bean.peBelief',mapping : 'peBelief',type : 'string'}
	,{name : 'bean.ofCompanyName',mapping : 'ofCompanyName',type : 'string'}
	,{name : 'bean.ofDeptName',mapping : 'ofDeptName',type : 'string'}
	,{name : 'bean.ofPost',mapping : 'ofPost',type : 'string'}
	,{name : 'bean.ofTel',mapping : 'ofTel',type : 'string'}
	,{name : 'bean.ofFax',mapping : 'ofFax',type : 'string'}
	,{name : 'bean.ofWebsite',mapping : 'ofWebsite',type : 'string'}
	,{name : 'bean.ofAddr',mapping : 'ofAddr',type : 'string'}
	,{name : 'bean.ofZipCode',mapping : 'ofZipCode',type : 'string'}
	,{name : 'bean.rem',mapping : 'rem',type : 'string'}
	,{name : 'bean.updatedBy',mapping : 'updatedBy',type : 'string',outkey : true}
	,{name : 'bean.updatedDateTime',mapping : 'updatedDateTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.createdBy',mapping : 'createdBy',type : 'string',outkey : true}
	,{name : 'bean.createdDateTime',mapping : 'createdDateTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});