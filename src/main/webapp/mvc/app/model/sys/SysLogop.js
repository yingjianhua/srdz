Ext.define('mvc.model.sys.SysLogop', {
	extend : 'Ext.data.Model',
	idProperty : 'bean.pkey',
	proxy : {
		type : 'ajax',
		url : base_path+'/sys_SysLogop_load'
	},
	fields : [
		{name : 'bean.pkey' , mapping : 'pkey' , type : 'int', useNull : true},
		{name : 'bean.logType' , mapping : 'logType' , type : 'int', useNull : true},
		{name : 'bean.logFun' , mapping : 'logFun' , type : 'string'},
		{name : 'bean.logOptype' , mapping : 'logOptype' , type : 'int', useNull : true},
		{name : 'bean.logOpdes' , mapping : 'logOpdes' , type : 'string'},
		{name : 'bean.logOper' , mapping : 'logOper' , type : 'string'},
		{name : 'bean.logOptime' , mapping : 'logOptime' , type : 'date' ,dateFormat : 'Y-m-d H:i:s'},
		{name : 'bean.logRem' , mapping : 'logRem' , type : 'string'}
	]
});
