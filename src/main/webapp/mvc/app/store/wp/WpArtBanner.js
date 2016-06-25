Ext.define('mvc.store.wp.WpArtBanner',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wp.WpArtBanner',
model : 'mvc.model.wp.WpArtBanner',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wp_WpArtBanner_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});