Ext.define('mvc.store.wpt.WptTop',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wpt.WptTop',
model : 'mvc.model.wpt.WptTop',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wpt/resource/headline_page',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});