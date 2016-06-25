Ext.define('mvc.model.wp.WpArt',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wp_WpArt_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.artShow',mapping : 'artShow',type : 'string',outkey : true}
	,{name : 'bean.title',mapping : 'title',type : 'string'}
	,{name : 'bean.imgUrl',mapping : 'imgUrl',type : 'string'}
	,{name : 'bean.url',mapping : 'url',type : 'string'}
	,{name : 'bean.description',mapping : 'description',type : 'string'}
	,{name : 'bean.date',mapping : 'date',type : 'date'}
	,{name : 'bean.theme',mapping : 'theme',type : 'string',outkey : true}
	,{name : 'bean.bsn',mapping : 'bsn',type : 'string',outkey : true}
	,{name : 'bean.createBy',mapping : 'createBy',type : 'string',outkey : true}
	,{name : 'bean.createTime',mapping : 'createTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.updateBy',mapping : 'updateBy',type : 'string',outkey : true}
	,{name : 'bean.updateTime',mapping : 'updateTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});