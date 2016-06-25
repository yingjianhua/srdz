Ext.define('mvc.store.wax.WaxManPic',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wax.WaxManPic',
model : 'mvc.model.wax.WaxManPic',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wax_WaxManPic_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});