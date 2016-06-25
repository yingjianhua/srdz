Ext.define('mvc.store.wax.WaxBnftEntry',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wax.WaxBnftEntry',
model : 'mvc.model.wax.WaxBnftEntry',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wax_WaxBnftEntry_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});