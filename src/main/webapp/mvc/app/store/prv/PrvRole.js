Ext.define('mvc.store.prv.PrvRole',{
extend : 'Ext.data.Store',
requires : 'mvc.model.prv.PrvRole',
model : 'mvc.model.prv.PrvRole',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/prv_PrvRole_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});