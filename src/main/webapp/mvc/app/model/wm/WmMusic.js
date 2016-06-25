Ext.define('mvc.model.wm.WmMusic',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wm_WmMusic_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.title',mapping : 'title',type : 'string'}
	,{name : 'bean.description',mapping : 'description',type : 'string'}
	,{name : 'bean.musicUrl',mapping : 'musicUrl',type : 'string'}
	,{name : 'bean.hqmusicUrl',mapping : 'hqmusicUrl',type : 'string'}
	,{name : 'bean.thumbMediaId',mapping : 'thumbMediaId',type : 'string'}
	,{name : 'bean.thumbUrl',mapping : 'thumbUrl',type : 'string'}
	,{name : 'bean.status',mapping : 'status',type : 'int',useNull : true}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});