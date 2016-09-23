Ext.define('mvc.model.wpt.WptOrderDetail',{
extend : 'Ext.data.Model',
idProperty : 'pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptOrderDetail_load'
},
fields : [{name : 'pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'name',mapping : 'name',type : 'string'}
	,{name : 'number',mapping : 'number',type : 'int',useNull : true}
	,{name : 'price',mapping : 'price',type : 'float',useNull : true}
	,{name : 'order.pkey',mapping : 'order.pkey',type : 'string',outkey : true,
		 convert: function(value, record) {
			 if(record.raw.city)
				 return record.raw.order.pkey+bean_split+record.raw.order.name;
			 return value;
         }}
	]
});