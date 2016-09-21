Ext.define('mvc.store.wpt.WptQrcodeRule',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wpt.WptQrcodeRule',
model : 'mvc.model.wpt.WptQrcodeRule',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wpt/resource/qrcodeRule_page',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});