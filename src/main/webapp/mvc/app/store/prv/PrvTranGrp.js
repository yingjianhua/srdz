Ext.define('mvc.store.prv.PrvTranGrp',{
extend : 'Ext.data.Store',
requires : 'mvc.model.prv.PrvTranGrp',
model : 'mvc.model.prv.PrvTranGrp',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/prv_PrvTranGrp_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});