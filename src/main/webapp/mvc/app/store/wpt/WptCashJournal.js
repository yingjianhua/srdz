Ext.define('mvc.store.wpt.WptCashJournal',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wpt.WptCashJournal',
model : 'mvc.model.wpt.WptCashJournal',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wpt/resource/cashJournal_page',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});