Ext.define('mvc.combo.sys.SysOLogOptype', {
	extend : 'Ext.data.Store',
	fields : ['value', 'text'],
	data : [
		{value : 1, text : '登录'},
		{value : 2, text : '注销'},
		{value : 3, text : '功能'}
	]
})
