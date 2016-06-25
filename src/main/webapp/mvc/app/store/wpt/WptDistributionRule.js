Ext.define('mvc.store.wpt.WptDistributionRule',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wpt.WptDistributionRule',
model : 'mvc.model.wpt.WptDistributionRule',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptDistributionRule_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});