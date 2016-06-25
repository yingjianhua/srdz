Ext.define('mvc.store.wa.WaActHomePage',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wa.WaActHomePage',
model : 'mvc.model.wa.WaActHomePage',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wa_WaActHomePage_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});