Ext.define('mvc.store.wpt.WptOrderCustomService',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wpt.WptOrderCustomService',
model : 'mvc.model.wpt.WptOrderCustomService',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wpt/resource/orderCustomService_page',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});