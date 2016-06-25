Ext.define('mvc.model.sys.SysSeq', {
	extend : 'Ext.data.Model',
	idProperty : 'bean.pkey',
	proxy : {
		type : 'ajax',
		url : base_path+'/sys_SysSeq_load'
	},
	fields : [
		{name : 'bean.pkey' , mapping : 'pkey' , type : 'int', useNull : true},
		{name : 'bean.orgFlag' , mapping : 'orgFlag' , type : 'int', useNull : true},
		{name : 'bean.firstStr' , mapping : 'firstStr' , type : 'string'},
		{name : 'bean.type' , mapping : 'type' , type : 'int', useNull : true},
		
		{name : 'one.code' , mapping : 'code' , type : 'string'},
		{name : 'one.name' , mapping : 'name' , type : 'string'}
	]
});
