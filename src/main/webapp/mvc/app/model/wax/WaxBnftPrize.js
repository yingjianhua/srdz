Ext.define('mvc.model.wax.WaxBnftPrize',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wax_WaxBnftPrize_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.bnft',mapping : 'bnft',type : 'string',outkey : true}
	,{name : 'bean.entry',mapping : 'entry',type : 'string',outkey : true}
	,{name : 'bean.sendSms',mapping : 'sendSms',type : 'int',useNull : true}
	,{name : 'bean.account',mapping : 'account',type : 'string',outkey : true}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});