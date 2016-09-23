Ext.define('mvc.model.wpt.WptServiceCenter',{
extend : 'Ext.data.Model',
idProperty : 'pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptServiceCenter_load'
},
fields : [{name : 'pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'mobile',mapping : 'mobile',type : 'string'}
	,{name : 'qrcode',mapping : 'qrcode',type : 'string'}
	,{name : 'smsTips',mapping : 'smsTips',type : 'string'}
	,{name : 'rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});