Ext.define('mvc.store.wp.WpBsnCtg',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wp.WpBsnCtg',
model : 'mvc.model.wp.WpBsnCtg',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wp_WpBsnCtg_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});