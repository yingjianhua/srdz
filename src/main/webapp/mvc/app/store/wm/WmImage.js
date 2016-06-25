Ext.define('mvc.store.wm.WmImage',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wm.WmImage',
model : 'mvc.model.wm.WmImage',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wm_WmImage_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});