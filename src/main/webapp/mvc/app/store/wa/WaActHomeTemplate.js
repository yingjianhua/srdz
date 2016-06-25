Ext.define('mvc.store.wa.WaActHomeTemplate',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wa.WaActHomeTemplate',
model : 'mvc.model.wa.WaActHomeTemplate',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wa_WaActHomeTemplate_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});