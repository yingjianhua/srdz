Ext.define('mvc.store.sys.SysCell',{
extend : 'Ext.data.Store',
requires : 'mvc.model.sys.SysCell',
model : 'mvc.model.sys.SysCell',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/sys_SysCell_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});