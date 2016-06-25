Ext.define('mvc.store.wpt.WptCase',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wpt.WptCase',
model : 'mvc.model.wpt.WptCase',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptCase_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});