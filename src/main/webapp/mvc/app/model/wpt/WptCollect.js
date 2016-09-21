Ext.define('mvc.model.wpt.WptCollect',{
extend : 'Ext.data.Model',
idProperty : 'pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptCollect_load'
},
fields : [{name : 'pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'member.pkey',mapping : 'member.pkey',type : 'string',outkey : true,
		 convert: function(value, record) {
			 if(record.raw.member)
				 return record.raw.member.pkey+bean_split+record.raw.member.nickname;
			 return value;
         }}
	,{name : 'headline.pkey',mapping : 'headline.pkey',type : 'string',outkey : true,
		convert: function(value, record) {
			 if(record.raw.headline)
				 return record.raw.headline.pkey+bean_split+record.raw.headline.title;
			 return value;
        }}
	,{name : 'rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});