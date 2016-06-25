Ext.define('mvc.model.wa.WaActVote',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wa_WaActVote_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.name',mapping : 'name',type : 'string'}
	,{name : 'bean.entryStartTime',mapping : 'entryStartTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.entryEndTime',mapping : 'entryEndTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.actStartTime',mapping : 'actStartTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.actEndTime',mapping : 'actEndTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.actTemplate',mapping : 'actTemplate',type : 'string',outkey : true}
	,{name : 'bean.jsMenushare',mapping : 'jsMenushare',type : 'string',outkey : true}
	,{name : 'bean.actConfig',mapping : 'actConfig',type : 'string',outkey : true}
	,{name : 'bean.voteConfig',mapping : 'voteConfig',type : 'string',outkey : true}
	,{name : 'bean.status',mapping : 'status',type : 'int',useNull : true}
	,{name : 'bean.des',mapping : 'des',type : 'string'}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});