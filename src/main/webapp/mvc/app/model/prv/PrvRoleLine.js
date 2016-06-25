Ext.define('mvc.model.prv.PrvRoleLine',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/prv_PrvRoleLine_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.role',mapping : 'role',type : 'string',outkey : true}
	,{name : 'bean.child',mapping : 'child',type : 'string',outkey : true}
	]
});