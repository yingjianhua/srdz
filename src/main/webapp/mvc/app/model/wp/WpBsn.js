Ext.define('mvc.model.wp.WpBsn',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wp_WpBsn_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.name',mapping : 'name',type : 'string'}
	,{name : 'bean.ctg',mapping : 'ctg',type : 'string',outkey : true}
	,{name : 'bean.area',mapping : 'area',type : 'string',outkey : true}
	,{name : 'bean.latitude',mapping : 'latitude',type : 'float',useNull : true}
	,{name : 'bean.longitude',mapping : 'longitude',type : 'float',useNull : true}
	,{name : 'bean.addr',mapping : 'addr',type : 'string'}
	,{name : 'bean.des',mapping : 'des',type : 'string'}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});