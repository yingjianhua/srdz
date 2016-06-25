Ext.define('mvc.store.prv.PrvRoleTran',{
extend : 'Ext.data.Store',
requires : 'mvc.model.prv.PrvRoleTran',
model : 'mvc.model.prv.PrvRoleTran',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/prv_PrvRoleTran_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});