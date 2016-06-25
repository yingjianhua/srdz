Ext.define('mvc.store.wm.WmText',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wm.WmText',
model : 'mvc.model.wm.WmText',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wm_WmText_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});