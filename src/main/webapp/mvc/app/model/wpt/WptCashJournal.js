Ext.define('mvc.model.wpt.WptCashJournal',{
extend : 'Ext.data.Model',
idProperty : 'pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptCashJournal_load'
},
fields : [{name : 'pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'price',mapping : 'price',type : 'float',useNull : true}
	,{name : 'member.pkey',mapping : 'member.pkey',type : 'string',outkey : true,
		convert: function(value, record) {
			if(record.raw.member)
				return record.raw.member.pkey+bean_split+record.raw.member.nickname;
			return value;
		}}
	,{name : 'createTime',mapping : 'createTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'account',mapping : 'account',type : 'string',outkey : true}
	,{name : 'rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});