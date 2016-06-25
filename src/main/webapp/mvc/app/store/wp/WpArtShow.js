Ext.define('mvc.store.wp.WpArtShow',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wp.WpArtShow',
model : 'mvc.model.wp.WpArtShow',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wp_WpArtShow_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});