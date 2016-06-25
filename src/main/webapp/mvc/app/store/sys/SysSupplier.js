Ext.define('mvc.store.sys.SysSupplier',{
extend : 'Ext.data.Store',
requires : 'mvc.model.sys.SysSupplier',
model : 'mvc.model.sys.SysSupplier',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/sys_SysSupplier_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});