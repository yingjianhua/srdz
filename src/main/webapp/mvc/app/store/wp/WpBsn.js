Ext.define('mvc.store.wp.WpBsn',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wp.WpBsn',
model : 'mvc.model.wp.WpBsn',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wp_WpBsn_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});