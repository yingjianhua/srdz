Ext.define('mvc.model.wpt.WptCity',{
extend : 'Ext.data.Model',
idProperty : 'pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptCity_load'
},
fields : [{name : 'pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'name',mapping : 'name',type : 'string'}
	,{name : 'rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});