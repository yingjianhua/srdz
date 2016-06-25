Ext.define('mvc.model.wpt.WptFeedBack',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptFeedBack_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.content',mapping : 'content',type : 'string'}
	,{name : 'bean.contactWay',mapping : 'contactWay',type : 'string'}
	,{name : 'bean.contactType',mapping : 'contactType',type : 'int',useNull : true}
	,{name : 'bean.handleMan',mapping : 'handleMan',type : 'string',outkey : true}
	,{name : 'bean.handleTime',mapping : 'handleTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.isHandle',mapping : 'isHandle',type : 'int',useNull : true}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});