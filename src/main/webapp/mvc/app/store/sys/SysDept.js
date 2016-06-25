Ext.define('mvc.store.sys.SysDept',{
extend : 'Ext.data.Store',
requires : 'mvc.model.sys.SysDept',
model : 'mvc.model.sys.SysDept',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/sys_SysDept_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});