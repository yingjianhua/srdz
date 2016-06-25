Ext.define('mvc.store.sys.SysCustom',{
extend : 'Ext.data.Store',
requires : 'mvc.model.sys.SysCustom',
model : 'mvc.model.sys.SysCustom',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/sys_SysCustom_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});