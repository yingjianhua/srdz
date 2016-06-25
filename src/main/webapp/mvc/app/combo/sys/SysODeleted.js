Ext.define('mvc.combo.sys.SysODeleted', {
	extend : 'Ext.data.Store',
	fields : ['value', 'text'],
	data : [
		{value : 0, text : '否'},
		{value : 1, text : '是'}
	]
})
