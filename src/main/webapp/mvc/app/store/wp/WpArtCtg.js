Ext.define('mvc.store.wp.WpArtCtg',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wp.WpArtCtg',
model : 'mvc.model.wp.WpArtCtg',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wp_WpArtCtg_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});