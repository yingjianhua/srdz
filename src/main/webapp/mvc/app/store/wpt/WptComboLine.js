Ext.define('mvc.store.wpt.WptComboLine',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wpt.WptComboLine',
model : 'mvc.model.wpt.WptComboLine',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wpt/resource/comboLine_page',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});