Ext.define('mvc.store.wax.WaxBnftPrize',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wax.WaxBnftPrize',
model : 'mvc.model.wax.WaxBnftPrize',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wax_WaxBnftPrize_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});