Ext.define('mvc.store.wp.WpArtTheme',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wp.WpArtTheme',
model : 'mvc.model.wp.WpArtTheme',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wp_WpArtTheme_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});