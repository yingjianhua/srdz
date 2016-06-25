Ext.define('mvc.store.sys.SysSupplierOrg',{
extend : 'Ext.data.Store',
requires : 'mvc.model.sys.SysSupplierOrg',
model : 'mvc.model.sys.SysSupplierOrg',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/sys_SysSupplierOrg_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});