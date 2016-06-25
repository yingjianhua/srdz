Ext.define('mvc.store.wax.WaxLady',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wax.WaxLady',
model : 'mvc.model.wax.WaxLady',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wax_WaxLady_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});