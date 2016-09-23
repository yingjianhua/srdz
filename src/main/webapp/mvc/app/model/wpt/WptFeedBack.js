Ext.define('mvc.model.wpt.WptFeedBack',{
extend : 'Ext.data.Model',
idProperty : 'pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptFeedBack_load'
},
fields : [{name : 'pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'content',mapping : 'content',type : 'string'}
	,{name : 'contactWay',mapping : 'contactWay',type : 'string'}
	,{name : 'contactType',mapping : 'contactType',type : 'int',useNull : true}
	,{name : 'handleMan',mapping : 'handleMan',type : 'string',outkey : true}
	,{name : 'handleTime',mapping : 'handleTime',type : 'date',dateFormat : 'Y-m-dTH:i:s'}
	,{name : 'isHandle',mapping : 'isHandle',type : 'boolean',useNull : true}
	,{name : 'rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});