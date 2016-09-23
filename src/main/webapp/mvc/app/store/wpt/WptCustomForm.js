Ext.define('mvc.store.wpt.WptCustomForm',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wpt.WptCustomForm',
model : 'mvc.model.wpt.WptCustomForm',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wpt/resource/customForm_page',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});