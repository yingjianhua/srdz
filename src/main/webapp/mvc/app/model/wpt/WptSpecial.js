Ext.define('mvc.model.wpt.WptSpecial',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptSpecial_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.title',mapping : 'title',type : 'string'}
	,{name : 'bean.city',mapping : 'city',type : 'string',outkey : true}
	,{name : 'bean.topImgUrl',mapping : 'topImgUrl',type : 'string'}
	,{name : 'bean.baseImgUrl',mapping : 'baseImgUrl',type : 'string'}
	,{name : 'bean.intro',mapping : 'intro',type : 'string'}
	,{name : 'bean.sort',mapping : 'sort',type : 'int',useNull : true}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});