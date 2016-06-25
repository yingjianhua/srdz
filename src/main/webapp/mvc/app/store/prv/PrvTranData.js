Ext.define('mvc.store.prv.PrvTranData',{
extend : 'Ext.data.Store',
requires : 'mvc.model.prv.PrvTranData',
model : 'mvc.model.prv.PrvTranData',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/prv_PrvTranData_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});