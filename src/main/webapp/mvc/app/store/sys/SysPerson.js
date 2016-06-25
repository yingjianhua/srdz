Ext.define('mvc.store.sys.SysPerson',{
extend : 'Ext.data.Store',
requires : 'mvc.model.sys.SysPerson',
model : 'mvc.model.sys.SysPerson',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/sys_SysPerson_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});