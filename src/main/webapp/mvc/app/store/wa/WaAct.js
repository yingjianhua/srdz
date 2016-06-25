Ext.define('mvc.store.wa.WaAct',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wa.WaAct',
model : 'mvc.model.wa.WaAct',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wa_WaAct_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});