Ext.define('mvc.model.wpt.WptPetitionCity',{
extend : 'Ext.data.Model',
idProperty : 'pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptPetitionCity_load'
},
fields : [{name : 'pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'name',mapping : 'name',type : 'string'}
	,{name : 'count',mapping : 'count',type : 'int',useNull : true}
	,{name : 'enabled',mapping : 'enabled',type : 'int',useNull : true}
	,{name : 'rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});