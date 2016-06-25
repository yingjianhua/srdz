Ext.define('mvc.store.wa.WaActVote',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wa.WaActVote',
model : 'mvc.model.wa.WaActVote',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wa_WaActVote_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});