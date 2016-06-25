Ext.define('mvc.store.wa.WaActPrize',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wa.WaActPrize',
model : 'mvc.model.wa.WaActPrize',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wa_WaActPrize_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});