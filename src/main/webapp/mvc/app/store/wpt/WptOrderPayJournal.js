Ext.define('mvc.store.wpt.WptOrderPayJournal',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wpt.WptOrderPayJournal',
model : 'mvc.model.wpt.WptOrderPayJournal',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wpt/resource/orderPayJournal_page',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});