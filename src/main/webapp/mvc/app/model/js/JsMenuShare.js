Ext.define('mvc.model.js.JsMenuShare',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/js_JsMenuShare_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.enabled',mapping : 'enabled',type : 'int',useNull : true}
	,{name : 'bean.title',mapping : 'title',type : 'string'}
	,{name : 'bean.des',mapping : 'des',type : 'string'}
	,{name : 'bean.link',mapping : 'link',type : 'string'}
	,{name : 'bean.imgUrl',mapping : 'imgUrl',type : 'string'}
	,{name : 'bean.type',mapping : 'type',type : 'int',useNull : true}
	,{name : 'bean.dataUrl',mapping : 'dataUrl',type : 'string'}
	,{name : 'bean.appMessage',mapping : 'appMessage',type : 'int',useNull : true}
	,{name : 'bean.timeLine',mapping : 'timeLine',type : 'int',useNull : true}
	,{name : 'bean.qq',mapping : 'qq',type : 'int',useNull : true}
	,{name : 'bean.weibo',mapping : 'weibo',type : 'int',useNull : true}
	,{name : 'bean.qzone',mapping : 'qzone',type : 'int',useNull : true}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});