Ext.define('mvc.store.wa.WaActDo',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wa.WaActDo',
model : 'mvc.model.wa.WaActDo',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wa_WaActDo_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});