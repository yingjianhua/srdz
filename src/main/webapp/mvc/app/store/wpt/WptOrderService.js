Ext.define('mvc.store.wpt.WptOrderService',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wpt.WptOrderService',
model : 'mvc.model.wpt.WptOrderService',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptOrderService_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});