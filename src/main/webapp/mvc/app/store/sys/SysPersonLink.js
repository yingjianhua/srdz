Ext.define('mvc.store.sys.SysPersonLink',{
extend : 'Ext.data.Store',
requires : 'mvc.model.sys.SysPersonLink',
model : 'mvc.model.sys.SysPersonLink',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/sys_SysPersonLink_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});