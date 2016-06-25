Ext.define('mvc.store.wax.WaxLadyBsn',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wax.WaxLadyBsn',
model : 'mvc.model.wax.WaxLadyBsn',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wax_WaxLadyBsn_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});