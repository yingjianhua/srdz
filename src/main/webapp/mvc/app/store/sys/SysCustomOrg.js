Ext.define('mvc.store.sys.SysCustomOrg',{
extend : 'Ext.data.Store',
requires : 'mvc.model.sys.SysCustomOrg',
model : 'mvc.model.sys.SysCustomOrg',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/sys_SysCustomOrg_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});