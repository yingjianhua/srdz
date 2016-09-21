Ext.define('mvc.model.wpt.WptSpecial',{
extend : 'Ext.data.Model',
idProperty : 'pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptSpecial_load'
},
fields : [{name : 'pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'title',mapping : 'title',type : 'string'}
	,{name : 'city.pkey',mapping : 'city.pkey',type : 'string',outkey : true,
		 convert: function(value, record) {
			 if(record.raw.city)
				 return record.raw.city.pkey+bean_split+record.raw.city.name;
			 return value;
         }}
	,{name : 'ignoreCity',mapping : 'ignoreCity',type : 'int',useNull : true}
	,{name : 'topImgUrl',mapping : 'topImgUrl',type : 'string'}
	,{name : 'baseImgUrl',mapping : 'baseImgUrl',type : 'string'}
	,{name : 'intro',mapping : 'intro',type : 'string'}
	,{name : 'sort',mapping : 'sort',type : 'int',useNull : true}
	,{name : 'rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});