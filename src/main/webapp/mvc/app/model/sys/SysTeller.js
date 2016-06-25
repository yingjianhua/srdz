Ext.define('mvc.model.sys.SysTeller',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/sys_SysTeller_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.code',mapping : 'code',type : 'string'}
	,{name : 'bean.name',mapping : 'name',type : 'string'}
	,{name : 'bean.pwd',mapping : 'pwd',type : 'string'}
	,{name : 'bean.branch',mapping : 'branch',type : 'string',outkey : true}
	,{name : 'bean.openId',mapping : 'openId',type : 'string'}
	,{name : 'bean.tel',mapping : 'tel',type : 'string'}
	,{name : 'bean.mobile',mapping : 'mobile',type : 'string'}
	,{name : 'bean.addr',mapping : 'addr',type : 'string'}
	,{name : 'bean.enabled',mapping : 'enabled',type : 'int',useNull : true}
	,{name : 'bean.rem',mapping : 'rem',type : 'string'}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});