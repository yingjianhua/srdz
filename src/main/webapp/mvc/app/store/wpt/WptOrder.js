Ext.define('mvc.store.wpt.WptOrder',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wpt.WptOrder',
model : 'mvc.model.wpt.WptOrder',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptOrder_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});