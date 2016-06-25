Ext.define('mvc.store.wp.WpArt',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wp.WpArt',
model : 'mvc.model.wp.WpArt',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wp_WpArt_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});