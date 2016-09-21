Ext.define('mvc.store.wpt.WptOrderService',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wpt.WptOrderService',
model : 'mvc.model.wpt.WptOrderService',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wpt/resource/orderService_page',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});