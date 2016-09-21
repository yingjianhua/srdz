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
	,{name : 'handleMan.pkey',mapping : 'handleMan.pkey',type : 'string',outkey : true,
		 convert: function(value, record) {
			 if(record.raw.handleMan)
				 return record.raw.cihandleManty.pkey+bean_split+record.raw.handleMan.nickname;
			 return value;
         }}
	,{name : 'handleTime',mapping : 'handleTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'isHandle',mapping : 'isHandle',type : 'int',useNull : true}
	,{name : 'rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});