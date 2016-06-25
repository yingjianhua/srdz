Ext.define('mvc.store.sys.SysSeq', {
	extend : 'Ext.data.Store',
	requires : 'mvc.model.sys.SysSeq',
	model : 'mvc.model.sys.SysSeq',
	pageSize : 20,
	remoteSort : false,
	autoLoad : false,
	proxy : {
		type : 'ajax',
		url : base_path+'/sys_SysSeq_list',
		reader : {
			type : 'json',
			root : 'items',
			totalProperty : 'total'
		},
		simpleSortMode : true
	}
});
