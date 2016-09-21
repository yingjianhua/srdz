Ext.define('mvc.model.wpt.WptBanquet',{
extend : 'Ext.data.Model',
idProperty : 'pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptBanquet_load'
},
fields : [{name : 'pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'name',mapping : 'name',type : 'string'}
	,{name : 'rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});