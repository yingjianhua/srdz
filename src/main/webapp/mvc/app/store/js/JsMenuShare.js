Ext.define('mvc.store.js.JsMenuShare',{
extend : 'Ext.data.Store',
requires : 'mvc.model.js.JsMenuShare',
model : 'mvc.model.js.JsMenuShare',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/js_JsMenuShare_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});