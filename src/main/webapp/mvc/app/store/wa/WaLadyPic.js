Ext.define('mvc.store.wa.WaLadyPic',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wa.WaLadyPic',
model : 'mvc.model.wa.WaLadyPic',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wa_WaLadyPic_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});