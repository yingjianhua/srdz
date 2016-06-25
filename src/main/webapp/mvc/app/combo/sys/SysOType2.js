Ext.define('mvc.combo.sys.SysOType2', {
	extend : 'Ext.data.Store',
	fields : ['value', 'text'],
	data : [
		{value : 1, text : '角色'},
		{value : 2, text : '组'},
		{value : 3, text : '功能'},
		{value : 4, text : '用户'}
	]
})
