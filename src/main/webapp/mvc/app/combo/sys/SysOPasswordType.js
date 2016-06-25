Ext.define('mvc.combo.sys.SysOPasswordType', {
	extend : 'Ext.data.Store',
	fields : ['value', 'text'],
	data : [
		{value : 1, text : '密码'},
		{value : 2, text : 'QQ验证'},
		{value : 3, text : '新浪验证'}
	]
})
