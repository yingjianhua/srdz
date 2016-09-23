Ext.define('mvc.model.wpt.WptWxTips',{
extend : 'Ext.data.Model',
idProperty : 'pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptWxTips_load'
},
fields : [{name : 'member.pkey',mapping : 'member.pkey',type : 'int',useNull : true,
			 convert: function(value, record) {
				 return record.raw.member.pkey;
		     }}
	,{name : 'member.nickname',mapping : 'member.nickname',type : 'string',
		 convert: function(value, record) {
			 return record.raw.member.nickname;
	     }}
	,{name : 'member.imageUrl',mapping : 'member.imageUrl',type : 'string',
		 convert: function(value, record) {
			 return record.raw.member.imageUrl;
	     }}
	,{name : 'member.rem',mapping : 'member.rem',type : 'string',
		 convert: function(value, record) {
			 return record.raw.member.rem;
	     }}
	,{name : 'member.status',mapping : 'member.status',type : 'int',useNull : true,
		 convert: function(value, record) {
			 return record.raw.member.status;
	     }}
	,{name : 'rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});