Ext.define('mvc.model.wpt.WptCustomForm',{
extend : 'Ext.data.Model',
idProperty : 'pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptCustomForm_load'
},
fields : [{name : 'pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'formid',mapping : 'formid',type : 'string'}
	,{name : 'banquet',mapping : 'banquet',type : 'string'}
	,{name : 'budget',mapping : 'budget',type : 'string'}
	,{name : 'number',mapping : 'number',type : 'string'}
	,{name : 'time',mapping : 'time',type : 'date',dateFormat : 'Y-m-dTH:i:s'}
	,{name : 'customServices',mapping : 'customServices',type : 'string'}
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
	,{name : 'contectMan',mapping : 'contectMan',type : 'string'}
	,{name : 'contectSex',mapping : 'contectSex',type : 'string'}
	,{name : 'contectType',mapping : 'contectType',type : 'string'}
	,{name : 'contectWay',mapping : 'contectWay',type : 'string'}
	,{name : 'rem',mapping : 'rem',type : 'string'}
	,{name : 'member.pkey',mapping : 'member',type : 'string',outkey : true,
		 convert: function(value, record) {
			 if(record.raw.member)
				 return record.raw.member.pkey+bean_split+record.raw.member.nickname;
			 return value;
        }}
	,{name : 'createTime',mapping : 'createTime',type : 'date',dateFormat : 'Y-m-dTH:i:s'}
	,{name : 'account',mapping : 'account',type : 'int'}
	,{name : 'rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});