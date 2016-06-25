Ext.define('mvc.store.wa.WaActTemplateMenu',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wa.WaActTemplateMenu',
model : 'mvc.model.wa.WaActTemplateMenu',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wa_WaActTemplateMenu_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});