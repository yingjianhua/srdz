Ext.define('mvc.store.wpt.WptCommissionJournal',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wpt.WptCommissionJournal',
model : 'mvc.model.wpt.WptCommissionJournal',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wpt/resource/commissionJournal_page',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});