Ext.define('mvc.store.wa.WaActVotePhoto',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wa.WaActVotePhoto',
model : 'mvc.model.wa.WaActVotePhoto',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wa_WaActVotePhoto_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});