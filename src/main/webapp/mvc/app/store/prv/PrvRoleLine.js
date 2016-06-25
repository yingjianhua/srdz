Ext.define('mvc.store.prv.PrvRoleLine',{
extend : 'Ext.data.Store',
requires : 'mvc.model.prv.PrvRoleLine',
model : 'mvc.model.prv.PrvRoleLine',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/prv_PrvRoleLine_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});