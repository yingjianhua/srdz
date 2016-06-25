Ext.define('mvc.store.wa.WaActConfig',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wa.WaActConfig',
model : 'mvc.model.wa.WaActConfig',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wa_WaActConfig_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});