Ext.define('mvc.store.wm.WmVideo',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wm.WmVideo',
model : 'mvc.model.wm.WmVideo',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wm_WmVideo_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});