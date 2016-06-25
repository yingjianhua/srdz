Ext.define('mvc.store.wm.WmNews',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wm.WmNews',
model : 'mvc.model.wm.WmNews',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wm_WmNews_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});