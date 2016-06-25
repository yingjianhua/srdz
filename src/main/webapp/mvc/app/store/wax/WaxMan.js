Ext.define('mvc.store.wax.WaxMan',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wax.WaxMan',
model : 'mvc.model.wax.WaxMan',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wax_WaxMan_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});