Ext.define('mvc.store.wa.WaActItem',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wa.WaActItem',
model : 'mvc.model.wa.WaActItem',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wa_WaActItem_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});