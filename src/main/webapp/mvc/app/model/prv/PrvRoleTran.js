Ext.define('mvc.model.prv.PrvRoleTran',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/prv_PrvRoleTran_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.role',mapping : 'role',type : 'string',outkey : true}
	,{name : 'bean.grp',mapping : 'grp',type : 'string',outkey : true}
	,{name : 'bean.type',mapping : 'type',type : 'int',useNull : true}
	,{name : 'bean.day',mapping : 'day',type : 'int',useNull : true}
	]
});