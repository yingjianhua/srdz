Ext.define('mvc.comboCust.saleGross',{
extend : 'Ext.data.Store',
fields : ['value','text'],
autoLoad : true,
proxy : {
	type : 'ajax',
	url : base_path+'/sys_SysCtype_getCombo?ctype=saleGross',
	reader : {
		type : 'json',
		root : 'items'
	}
}
});