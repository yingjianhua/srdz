Ext.define('mvc.store.wpt.WptRedPackRule',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wpt.WptRedPackRule',
model : 'mvc.model.wpt.WptRedPackRule',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptRedPackRule_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});