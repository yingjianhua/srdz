Ext.define('mvc.store.wax.WaxLadyPic',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wax.WaxLadyPic',
model : 'mvc.model.wax.WaxLadyPic',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wax_WaxLadyPic_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});