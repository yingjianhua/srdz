Ext.define('mvc.store.sys.SysCtypeCode',{
extend : 'Ext.data.Store',
requires : 'mvc.model.sys.SysCtypeCode',
model : 'mvc.model.sys.SysCtypeCode',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/sys_SysCtypeCode_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});