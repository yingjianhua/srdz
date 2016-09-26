Ext.define('mvc.store.wpt.WptHeadline',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wpt.WptHeadline',
model : 'mvc.model.wpt.WptHeadline',
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