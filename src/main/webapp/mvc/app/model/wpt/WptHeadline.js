Ext.define('mvc.model.wpt.WptHeadline',{
extend : 'Ext.data.Model',
idProperty : 'pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptHeadline_load'
},
fields : [{name : 'pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'city.pkey',mapping : 'city.pkey',type : 'string',outkey : true,
		 convert: function(value, record) {
			 if(record.raw.city)
				 return record.raw.city.pkey+bean_split+record.raw.city.name;
			 return value;
         }}
	,{name : 'cityline.pkey',mapping : 'cityline.pkey',type : 'string',outkey : true,
		 convert: function(value, record) {
			 if(record.raw.cityline)
				 return record.raw.cityline.pkey+bean_split+record.raw.cityline.name;
			 return value;
         }}
	,{name : 'banquet.pkey',mapping : 'banquet.pkey',type : 'string',outkey : true,
		 convert: function(value, record) {
			 if(record.raw.banquet)
				 return record.raw.banquet.pkey+bean_split+record.raw.banquet.name;
			 return value;
         }}
	,{name : 'title',mapping : 'title',type : 'string'}
	,{name : 'content',mapping : 'content',type : 'string'}
	,{name : 'imgUrl',mapping : 'imgUrl',type : 'string'}
	,{name : 'date',mapping : 'date',type : 'date'}
	,{name : 'url',mapping : 'url',type : 'string'}
	,{name : 'top',mapping : 'top',type : 'int',useNull : true}
	,{name : 'sort',mapping : 'sort',type : 'int',useNull : true}
	,{name : 'rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});