Ext.define('mvc.store.wa.WaActVoteBanner',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wa.WaActVoteBanner',
model : 'mvc.model.wa.WaActVoteBanner',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wa_WaActVoteBanner_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});