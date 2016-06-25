Ext.define('mvc.model.wpt.WptTop',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptTop_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.city',mapping : 'city',type : 'string',outkey : true}
	,{name : 'bean.cityline',mapping : 'cityline',type : 'string',outkey : true}
	,{name : 'bean.banquet',mapping : 'banquet',type : 'string',outkey : true}
	,{name : 'bean.title',mapping : 'title',type : 'string'}
	,{name : 'bean.content',mapping : 'content',type : 'string'}
	,{name : 'bean.imgUrl',mapping : 'imgUrl',type : 'string'}
	,{name : 'bean.date',mapping : 'date',type : 'date'}
	,{name : 'bean.url',mapping : 'url',type : 'string'}
	,{name : 'bean.top',mapping : 'top',type : 'int',useNull : true}
	,{name : 'bean.sort',mapping : 'sort',type : 'int',useNull : true}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});