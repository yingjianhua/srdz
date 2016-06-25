Ext.define('mvc.comboCust.sysPost',{
extend : 'Ext.data.Store',
fields : ['value','text'],
autoLoad : true,
proxy : {
	type : 'ajax',
	url : base_path+'/sys_SysCtype_getCombo?ctype=sysPost',
	reader : {
		type : 'json',
		root : 'items'
	}
}
});