Ext.define('mvc.store.wa.WaActGet',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wa.WaActGet',
model : 'mvc.model.wa.WaActGet',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wa_WaActGet_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});