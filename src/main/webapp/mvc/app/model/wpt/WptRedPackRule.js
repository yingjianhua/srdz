Ext.define('mvc.model.wpt.WptRedPackRule',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptRedPackRule_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.sendName',mapping : 'sendName',type : 'string'}
	,{name : 'bean.wishing',mapping : 'wishing',type : 'string'}
	,{name : 'bean.actName',mapping : 'actName',type : 'string'}
	,{name : 'bean.remark',mapping : 'remark',type : 'string'}
	,{name : 'bean.leastAmt',mapping : 'leastAmt',type : 'float',useNull : true}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});