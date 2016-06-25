Ext.define('mvc.model.wax.WaxManBsn',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wax_WaxManBsn_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.man',mapping : 'man',type : 'string',outkey : true}
	,{name : 'bean.bsn',mapping : 'bsn',type : 'string',outkey : true}
	,{name : 'bean.account',mapping : 'account',type : 'string',outkey : true}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});