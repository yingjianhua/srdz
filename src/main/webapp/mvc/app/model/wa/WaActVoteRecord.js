Ext.define('mvc.model.wa.WaActVoteRecord',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wa_WaActVoteRecord_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.act',mapping : 'act',type : 'string',outkey : true}
	,{name : 'bean.entryRecord',mapping : 'entryRecord',type : 'string',outkey : true}
	,{name : 'bean.wxUser',mapping : 'wxUser',type : 'string',outkey : true}
	,{name : 'bean.voteTime',mapping : 'voteTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.ip',mapping : 'ip',type : 'string'}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});