Ext.define('mvc.model.wa.WaActVoteEntry',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wa_WaActVoteEntry_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.vote',mapping : 'vote',type : 'string',outkey : true}
	,{name : 'bean.wxUser',mapping : 'wxUser',type : 'string',outkey : true}
	,{name : 'bean.number',mapping : 'number',type : 'int',useNull : true}
	,{name : 'bean.namePerson',mapping : 'namePerson',type : 'string'}
	,{name : 'bean.phonePerson',mapping : 'phonePerson',type : 'string'}
	,{name : 'bean.des',mapping : 'des',type : 'string'}
	,{name : 'bean.entryTime',mapping : 'entryTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.voteCount',mapping : 'voteCount',type : 'int',useNull : true}
	,{name : 'bean.status',mapping : 'status',type : 'int',useNull : true}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});