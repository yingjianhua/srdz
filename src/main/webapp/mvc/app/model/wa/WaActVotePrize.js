Ext.define('mvc.model.wa.WaActVotePrize',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wa_WaActVotePrize_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.vote',mapping : 'vote',type : 'string',outkey : true}
	,{name : 'bean.prizeitem',mapping : 'prizeitem',type : 'string',outkey : true}
	,{name : 'bean.prize',mapping : 'prize',type : 'string',outkey : true}
	,{name : 'bean.amount',mapping : 'amount',type : 'int',useNull : true}
	,{name : 'bean.unit',mapping : 'unit',type : 'string'}
	,{name : 'bean.sort',mapping : 'sort',type : 'int',useNull : true}
	,{name : 'bean.account',mapping : 'account',type : 'string',outkey : true}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});