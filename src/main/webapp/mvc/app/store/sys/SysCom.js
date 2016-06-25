Ext.define('mvc.store.sys.SysCom',{
extend : 'Ext.data.Store',
requires : 'mvc.model.sys.SysCom',
model : 'mvc.model.sys.SysCom',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/sys_SysCom_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});