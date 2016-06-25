Ext.define('mvc.store.wax.WaxManBsn',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wax.WaxManBsn',
model : 'mvc.model.wax.WaxManBsn',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wax_WaxManBsn_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});