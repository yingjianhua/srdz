Ext.define('mvc.store.sys.SysTeller',{
extend : 'Ext.data.Store',
requires : 'mvc.model.sys.SysTeller',
model : 'mvc.model.sys.SysTeller',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/sys_SysTeller_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});