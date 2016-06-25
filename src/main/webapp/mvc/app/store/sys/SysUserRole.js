Ext.define('mvc.store.sys.SysUserRole',{
extend : 'Ext.data.Store',
requires : 'mvc.model.sys.SysUserRole',
model : 'mvc.model.sys.SysUserRole',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/sys_SysUserRole_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});