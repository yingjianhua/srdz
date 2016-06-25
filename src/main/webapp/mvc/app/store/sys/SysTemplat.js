Ext.define('mvc.store.sys.SysTemplat',{
extend : 'Ext.data.Store',
requires : 'mvc.model.sys.SysTemplat',
model : 'mvc.model.sys.SysTemplat',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/sys_SysTemplat_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});