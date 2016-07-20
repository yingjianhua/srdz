Ext.define('mvc.model.wpt.WptRestaurant',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptRestaurant_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.name',mapping : 'name',type : 'string'}
	,{name : 'bean.mobile',mapping : 'mobile',type : 'string'}
	,{name : 'bean.manager',mapping : 'manager',type : 'string'}
	,{name : 'bean.city',mapping : 'city',type : 'string',outkey : true}
	,{name : 'bean.cityline',mapping : 'cityline',type : 'string',outkey : true}
	,{name : 'bean.addr',mapping : 'addr',type : 'string'}
	,{name : 'bean.coordinate',mapping : 'coordinate',type : 'string'}
	,{name : 'bean.imgUrl',mapping : 'imgUrl',type : 'string'}
	,{name : 'bean.rem',mapping : 'rem',type : 'string'}
	,{name : 'bean.display',mapping : 'display',type : 'int',useNull : true}
	,{name : 'bean.des',mapping : 'des',type : 'string'}
	,{name : 'bean.startdate',mapping : 'startdate',type : 'string'}
	,{name : 'bean.stopdate',mapping : 'stopdate',type : 'string'}
	,{name : 'bean.consumption',mapping : 'consumption',type : 'float',useNull : true}
	,{name : 'bean.wifiaccount',mapping : 'wifiaccount',type : 'string'}
	,{name : 'bean.wifipassword',mapping : 'wifipassword',type : 'string'}
	,{name : 'bean.template',mapping : 'template',type : 'string',outkey : true}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});