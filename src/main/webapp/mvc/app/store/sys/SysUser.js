Ext.define('mvc.store.sys.SysUser',{
extend : 'Ext.data.Store',
requires : 'mvc.model.sys.SysUser',
model : 'mvc.model.sys.SysUser',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/sys_SysUser_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});