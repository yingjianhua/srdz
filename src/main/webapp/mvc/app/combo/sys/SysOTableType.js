Ext.define('mvc.combo.sys.SysOTableType', {
	extend : 'Ext.data.Store',
	fields : ['value', 'text'],
	data : [
		{value : 1, text : '系统表'},
		{value : 2, text : '单据'},
		{value : 3, text : '凭证'},
		{value : 4, text : '便签'}
	]
})
