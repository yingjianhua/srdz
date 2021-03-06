Ext.define('mvc.store.wpt.WptCollect',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wpt.WptCollect',
model : 'mvc.model.wpt.WptCollect',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wpt/resource/collect_page',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});