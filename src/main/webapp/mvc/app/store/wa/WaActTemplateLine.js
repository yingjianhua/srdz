Ext.define('mvc.store.wa.WaActTemplateLine',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wa.WaActTemplateLine',
model : 'mvc.model.wa.WaActTemplateLine',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wa_WaActTemplateLine_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});