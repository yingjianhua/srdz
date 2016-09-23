Ext.define('mvc.store.wpt.WptOrderDetail',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wpt.WptOrderDetail',
model : 'mvc.model.wpt.WptOrderDetail',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wpt/resource/orderDetail_page',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});