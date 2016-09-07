Ext.define('mvc.model.wpt.WptCollect',{
extend : 'Ext.data.Model',
idProperty : 'pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptCollect_load'
},
fields : [{name : 'pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'wxuser',mapping : 'wxuser',type : 'string',outkey : true}
	,{name : 'headline.pkey',mapping : 'headline.pkey',type : 'string',outkey : true,
		convert: function(value, record) {
			 if(record.raw.headline)
				 return record.raw.headline.pkey+bean_split+record.raw.headline.title;
			 return value;
        }}
	,{name : 'rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});