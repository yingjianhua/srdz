Ext.define('mvc.model.wm.WmNews',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wm_WmNews_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.title',mapping : 'title',type : 'string'}
	,{name : 'bean.imageUrl',mapping : 'imageUrl',type : 'string'}
	,{name : 'bean.showCoverPic',mapping : 'showCoverPic',type : 'int',useNull : true}
	,{name : 'bean.author',mapping : 'author',type : 'string'}
	,{name : 'bean.summary',mapping : 'summary',type : 'string'}
	,{name : 'bean.content',mapping : 'content',type : 'string'}
	,{name : 'bean.relUrl',mapping : 'relUrl',type : 'string'}
	,{name : 'bean.type',mapping : 'type',type : 'int',useNull : true}
	,{name : 'bean.exp',mapping : 'exp',type : 'string',outkey : true}
	,{name : 'bean.url',mapping : 'url',type : 'string'}
	,{name : 'bean.picUp',mapping : 'picUp',type : 'string',outkey : true}
	,{name : 'bean.sort',mapping : 'sort',type : 'int',useNull : true}
	,{name : 'bean.mediaId',mapping : 'mediaId',type : 'string'}
	,{name : 'bean.status',mapping : 'status',type : 'int',useNull : true}
	,{name : 'bean.account',mapping : 'account',type : 'string',outkey : true}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});