Ext.define('mvc.store.wa.WaActVoteEntry',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wa.WaActVoteEntry',
model : 'mvc.model.wa.WaActVoteEntry',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wa_WaActVoteEntry_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});