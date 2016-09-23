Ext.define('mvc.store.wpt.WptServiceCenter',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wpt.WptServiceCenter',
model : 'mvc.model.wpt.WptServiceCenter',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wpt/resource/serviceCenter_page',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});