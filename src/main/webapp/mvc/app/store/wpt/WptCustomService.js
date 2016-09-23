Ext.define('mvc.store.wpt.WptCustomService',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wpt.WptCustomService',
model : 'mvc.model.wpt.WptCustomService',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wpt/resource/customService_page',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});