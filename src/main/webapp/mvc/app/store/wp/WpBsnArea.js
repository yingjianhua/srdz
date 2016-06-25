Ext.define('mvc.store.wp.WpBsnArea',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wp.WpBsnArea',
model : 'mvc.model.wp.WpBsnArea',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wp_WpBsnArea_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});