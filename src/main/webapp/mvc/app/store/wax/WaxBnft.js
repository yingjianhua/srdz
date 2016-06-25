Ext.define('mvc.store.wax.WaxBnft',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wax.WaxBnft',
model : 'mvc.model.wax.WaxBnft',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wax_WaxBnft_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});