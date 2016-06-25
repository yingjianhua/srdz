Ext.define('mvc.store.wa.WaActVotePrize',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wa.WaActVotePrize',
model : 'mvc.model.wa.WaActVotePrize',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wa_WaActVotePrize_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});