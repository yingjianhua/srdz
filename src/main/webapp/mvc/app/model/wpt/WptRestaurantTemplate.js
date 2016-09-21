Ext.define('mvc.model.wpt.WptRestaurantTemplate',{
extend : 'Ext.data.Model',
idProperty : 'pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptRestaurantTemplate_load'
},
fields : [{name : 'pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'name',mapping : 'name',type : 'string',outkey : true}
	,{name : 'imgUrl',mapping : 'imgUrl',type : 'string'}
	,{name : 'path',mapping : 'path',type : 'string'}
	,{name : 'rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});