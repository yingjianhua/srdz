Ext.define('mvc.model.wpt.WptServiceCen',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptServiceCen_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.mobile',mapping : 'mobile',type : 'string'}
	,{name : 'bean.qrcode',mapping : 'qrcode',type : 'string'}
	,{name : 'bean.smsTips',mapping : 'smsTips',type : 'string'}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});