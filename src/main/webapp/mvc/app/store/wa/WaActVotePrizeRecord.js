Ext.define('mvc.store.wa.WaActVotePrizeRecord',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wa.WaActVotePrizeRecord',
model : 'mvc.model.wa.WaActVotePrizeRecord',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wa_WaActVotePrizeRecord_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});