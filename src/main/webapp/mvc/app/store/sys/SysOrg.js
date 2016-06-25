Ext.define('mvc.store.sys.SysOrg',{
extend : 'Ext.data.Store',
requires : 'mvc.model.sys.SysOrg',
model : 'mvc.model.sys.SysOrg',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/sys_SysOrg_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});