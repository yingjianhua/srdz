Ext.define('mvc.store.wa.WxQRCode',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wa.WxQRCode',
model : 'mvc.model.wa.WxQRCode',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wa_WxQRCode_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});