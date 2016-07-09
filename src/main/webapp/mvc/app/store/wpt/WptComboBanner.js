Ext.define('mvc.store.wpt.WptComboBanner',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wpt.WptComboBanner',
model : 'mvc.model.wpt.WptComboBanner',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptComboBanner_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});