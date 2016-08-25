Ext.define('mvc.model.wpt.WptCombo',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptCombo_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.restaurant.pkey',mapping : 'restaurant.pkey',type : 'string',outkey : true,
		 convert: function(value, record) {
			 return record.raw.restaurant.pkey+bean_split+record.raw.restaurant.name;
         }}
	,{name : 'bean.name',mapping : 'name',type : 'string'}
	,{name : 'bean.imgUrl',mapping : 'imgUrl',type : 'string'}
	,{name : 'bean.des',mapping : 'des',type : 'string'}
	,{name : 'bean.origPrice',mapping : 'origPrice',type : 'float',useNull : true}
	,{name : 'bean.price',mapping : 'price',type : 'float',useNull : true}
	,{name : 'bean.numberMin',mapping : 'numberMin',type : 'int',useNull : true}
	,{name : 'bean.numberMax',mapping : 'numberMax',type : 'int',useNull : true}
	,{name : 'bean.serviceDate',mapping : 'serviceDate',type : 'string'}
	,{name : 'bean.serviceTime',mapping : 'serviceTime',type : 'string'}
	,{name : 'bean.rem',mapping : 'rem',type : 'string'}
	,{name : 'bean.sort',mapping : 'sort',type : 'int', defaultValue : 0, useNull : true}
	,{name : 'bean.enabled',mapping : 'enabled',type : 'int',useNull : true}	
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});