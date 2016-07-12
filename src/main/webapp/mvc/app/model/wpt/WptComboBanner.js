Ext.define('mvc.model.wpt.WptComboBanner',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptComboBanner_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.combo',mapping : 'combo',type : 'string',outkey : true}
	,{name : 'bean.imgUrl',mapping : 'imgUrl',type : 'string'}
	,{name : 'bean.sort',mapping : 'sort',type : 'int',useNull : true}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});