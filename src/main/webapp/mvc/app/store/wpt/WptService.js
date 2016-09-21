Ext.define('mvc.store.wpt.WptService',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wpt.WptService',
model : 'mvc.model.wpt.WptService',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wpt/resource/service_page',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});