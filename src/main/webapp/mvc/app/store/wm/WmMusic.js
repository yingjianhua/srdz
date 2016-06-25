Ext.define('mvc.store.wm.WmMusic',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wm.WmMusic',
model : 'mvc.model.wm.WmMusic',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wm_WmMusic_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});