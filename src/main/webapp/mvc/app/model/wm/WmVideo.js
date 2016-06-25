Ext.define('mvc.model.wm.WmVideo',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wm_WmVideo_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.title',mapping : 'title',type : 'string'}
	,{name : 'bean.description',mapping : 'description',type : 'string'}
	,{name : 'bean.videoUrl',mapping : 'videoUrl',type : 'string'}
	,{name : 'bean.mediaId',mapping : 'mediaId',type : 'string'}
	,{name : 'bean.status',mapping : 'status',type : 'int',useNull : true}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});