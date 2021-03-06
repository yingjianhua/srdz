Ext.define('mvc.store.wpt.WptCombo',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wpt.WptCombo',
model : 'mvc.model.wpt.WptCombo',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wpt/resource/combo_page',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});