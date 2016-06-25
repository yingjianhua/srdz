Ext.define('mvc.store.wa.WaActSet',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wa.WaActSet',
model : 'mvc.model.wa.WaActSet',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wa_WaActSet_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});