Ext.define('mvc.store.sys.SysBranch',{
extend : 'Ext.data.Store',
requires : 'mvc.model.sys.SysBranch',
model : 'mvc.model.sys.SysBranch',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/sys_SysBranch_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});