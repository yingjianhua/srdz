Ext.define('mvc.store.wa.WaActPage',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wa.WaActPage',
model : 'mvc.model.wa.WaActPage',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wa_WaActPage_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});