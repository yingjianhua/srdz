Ext.define('mvc.store.wm.WmVoice',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wm.WmVoice',
model : 'mvc.model.wm.WmVoice',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wm_WmVoice_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});