Ext.define('mvc.store.wpt.WptHot',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wpt.WptHot',
model : 'mvc.model.wpt.WptHot',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptHot_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});