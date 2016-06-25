Ext.define('mvc.model.wa.WaAct',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wa_WaAct_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.name',mapping : 'name',type : 'string'}
	,{name : 'bean.title',mapping : 'title',type : 'string'}
	,{name : 'bean.doCount',mapping : 'doCount',type : 'int',useNull : true}
	,{name : 'bean.startTime',mapping : 'startTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.endTime',mapping : 'endTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.rate',mapping : 'rate',type : 'float',useNull : true}
	,{name : 'bean.type',mapping : 'type',type : 'int',useNull : true}
	,{name : 'bean.status',mapping : 'status',type : 'int',useNull : true}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});