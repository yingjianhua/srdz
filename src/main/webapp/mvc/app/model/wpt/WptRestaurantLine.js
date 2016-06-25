Ext.define('mvc.model.wpt.WptRestaurantLine',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptRestaurantLine_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.restaurant',mapping : 'restaurant',type : 'string',outkey : true}
	,{name : 'bean.banquet',mapping : 'banquet',type : 'string',outkey : true}
	,{name : 'bean.account',mapping : 'account',type : 'string',outkey : true}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});