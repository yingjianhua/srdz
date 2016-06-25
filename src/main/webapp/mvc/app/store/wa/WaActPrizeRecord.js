Ext.define('mvc.store.wa.WaActPrizeRecord',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wa.WaActPrizeRecord',
model : 'mvc.model.wa.WaActPrizeRecord',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wa_WaActPrizeRecord_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});