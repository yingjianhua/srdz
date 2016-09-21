Ext.define('mvc.model.wpt.WptRedPackRule',{
extend : 'Ext.data.Model',
idProperty : 'pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptRedPackRule_load'
},
fields : [{name : 'pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'sendName',mapping : 'sendName',type : 'string'}
	,{name : 'wishing',mapping : 'wishing',type : 'string'}
	,{name : 'actName',mapping : 'actName',type : 'string'}
	,{name : 'remark',mapping : 'remark',type : 'string'}
	,{name : 'leastAmt',mapping : 'leastAmt',type : 'float',useNull : true}
	,{name : 'rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});