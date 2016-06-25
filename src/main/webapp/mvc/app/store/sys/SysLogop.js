Ext.define('mvc.store.sys.SysLogop', {
	extend : 'Ext.data.Store',
	requires : 'mvc.model.sys.SysLogop',
	model : 'mvc.model.sys.SysLogop',
	pageSize : 20,
	remoteSort : false,
	autoLoad : false,
	proxy : {
		type : 'ajax',
		url : base_path+'/sys_SysLogop_list',
		reader : {
			type : 'json',
			root : 'items',
			totalProperty : 'total'
		},
		simpleSortMode : true
	}
});
