Ext.define('mvc.store.wa.WaActHomeTemplateBarnner',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wa.WaActHomeTemplateBarnner',
model : 'mvc.model.wa.WaActHomeTemplateBarnner',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wa_WaActHomeTemplateBarnner_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});