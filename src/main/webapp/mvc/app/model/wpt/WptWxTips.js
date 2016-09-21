Ext.define('mvc.model.wpt.WptWxTips',{
extend : 'Ext.data.Model',
idProperty : 'pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptWxTips_load'
},
fields : [{name : 'pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'nickname',mapping : 'nickname',type : 'string'}
	,{name : 'imageUrl',mapping : 'imageUrl',type : 'string'}
	,{name : 'rem',mapping : 'rem',type : 'string'}
	,{name : 'status',mapping : 'status',type : 'int',useNull : true}
	,{name : 'rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});