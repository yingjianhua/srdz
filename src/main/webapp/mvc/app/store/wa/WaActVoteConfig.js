Ext.define('mvc.store.wa.WaActVoteConfig',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wa.WaActVoteConfig',
model : 'mvc.model.wa.WaActVoteConfig',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wa_WaActVoteConfig_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});