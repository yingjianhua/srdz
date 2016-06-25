Ext.define('mvc.model.wa.WaActVotePrizeRecord',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wa_WaActVotePrizeRecord_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.vote',mapping : 'vote',type : 'string',outkey : true}
	,{name : 'bean.winner',mapping : 'winner',type : 'string',outkey : true}
	,{name : 'bean.prizeitem',mapping : 'prizeitem',type : 'string',outkey : true}
	,{name : 'bean.account',mapping : 'account',type : 'string',outkey : true}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});