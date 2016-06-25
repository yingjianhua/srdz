Ext.define('mvc.store.wpt.WptBanquet',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wpt.WptBanquet',
model : 'mvc.model.wpt.WptBanquet',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptBanquet_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});