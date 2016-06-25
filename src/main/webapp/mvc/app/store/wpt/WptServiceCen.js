Ext.define('mvc.store.wpt.WptServiceCen',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wpt.WptServiceCen',
model : 'mvc.model.wpt.WptServiceCen',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptServiceCen_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});