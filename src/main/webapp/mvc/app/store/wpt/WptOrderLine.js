Ext.define('mvc.store.wpt.WptOrderLine',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wpt.WptOrderLine',
model : 'mvc.model.wpt.WptOrderLine',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wpt/resource/orderLine_page',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});