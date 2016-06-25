Ext.define('mvc.store.wpt.WptFeedBack',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wpt.WptFeedBack',
model : 'mvc.model.wpt.WptFeedBack',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptFeedBack_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});