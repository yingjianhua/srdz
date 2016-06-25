Ext.define('mvc.store.sys.SysAccessory',{
extend : 'Ext.data.Store',
requires : 'mvc.model.sys.SysAccessory',
model : 'mvc.model.sys.SysAccessory',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/sys_SysAccessory_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});