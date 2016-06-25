Ext.define('mvc.model.sys.SysCtypeCode',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/sys_SysCtypeCode_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.ctypeType',mapping : 'ctypeType',type : 'string',outkey : true}
	,{name : 'bean.codeValue',mapping : 'codeValue',type : 'string'}
	,{name : 'bean.codeName',mapping : 'codeName',type : 'string'}
	,{name : 'bean.codeDes',mapping : 'codeDes',type : 'string'}
	]
});