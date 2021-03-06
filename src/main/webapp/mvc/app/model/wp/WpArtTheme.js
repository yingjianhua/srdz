Ext.define('mvc.model.wp.WpArtTheme',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wp_WpArtTheme_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.artShow',mapping : 'artShow',type : 'string',outkey : true}
	,{name : 'bean.name',mapping : 'name',type : 'string'}
	,{name : 'bean.imgUrl',mapping : 'imgUrl',type : 'string'}
	,{name : 'bean.sort',mapping : 'sort',type : 'int',useNull : true}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});