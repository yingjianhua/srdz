Ext.define('mvc.model.sys.SysCtype',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/sys_SysCtype_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'string'}
	,{name : 'bean.ctypeName',mapping : 'ctypeName',type : 'string'}
	,{name : 'bean.ctypeDes',mapping : 'ctypeDes',type : 'string'}
	,{name : 'bean.ctypeLen',mapping : 'ctypeLen',type : 'int',useNull : true}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});