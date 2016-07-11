Ext.define('mvc.model.wpt.WptWxTips',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptWxTips_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.nickname',mapping : 'nickname',type : 'string'}
	,{name : 'bean.imageUrl',mapping : 'imageUrl',type : 'string'}
	,{name : 'bean.rem',mapping : 'rem',type : 'string'}
	,{name : 'bean.status',mapping : 'status',type : 'int',useNull : true}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});