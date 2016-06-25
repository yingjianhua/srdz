Ext.define('mvc.store.wa.WaActVoteRecord',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wa.WaActVoteRecord',
model : 'mvc.model.wa.WaActVoteRecord',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wa_WaActVoteRecord_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});