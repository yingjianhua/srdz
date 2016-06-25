Ext.define('mvc.model.prv.PrvTranData',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/prv_PrvTranData_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.name',mapping : 'name',type : 'string'}
	,{name : 'bean.tranName',mapping : 'tranName',type : 'string'}
	,{name : 'bean.isForm',mapping : 'isForm',type : 'int',useNull : true}
	,{name : 'bean.grp',mapping : 'grp',type : 'string',outkey : true}
	,{name : 'bean.userName',mapping : 'userName',type : 'string'}
	,{name : 'bean.deptName',mapping : 'deptName',type : 'string'}
	,{name : 'bean.cellName',mapping : 'cellName',type : 'string'}
	,{name : 'bean.orgName',mapping : 'orgName',type : 'string'}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});