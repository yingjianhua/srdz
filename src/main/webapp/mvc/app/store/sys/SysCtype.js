Ext.define('mvc.store.sys.SysCtype',{
extend : 'Ext.data.Store',
requires : 'mvc.model.sys.SysCtype',
model : 'mvc.model.sys.SysCtype',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/sys_SysCtype_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});