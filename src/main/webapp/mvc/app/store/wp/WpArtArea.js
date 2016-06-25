Ext.define('mvc.store.wp.WpArtArea',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wp.WpArtArea',
model : 'mvc.model.wp.WpArtArea',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wp_WpArtArea_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});