Ext.define('mvc.store.wa.WaActTemplate',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wa.WaActTemplate',
model : 'mvc.model.wa.WaActTemplate',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wa_WaActTemplate_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});