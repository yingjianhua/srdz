Ext.define('mvc.store.wpt.WptWxTips',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wpt.WptWxTips',
model : 'mvc.model.wpt.WptWxTips',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wpt/resource/wxTips_page',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});