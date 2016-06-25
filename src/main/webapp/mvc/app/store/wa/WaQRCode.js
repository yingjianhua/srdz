Ext.define('mvc.store.wa.WaQRCode',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wa.WaQRCode',
model : 'mvc.model.wa.WaQRCode',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wa_WaQRCode_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});