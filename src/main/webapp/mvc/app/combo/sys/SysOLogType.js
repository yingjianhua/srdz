Ext.define('mvc.combo.sys.SysOLogType', {
	extend : 'Ext.data.Store',
	fields : ['value', 'text'],
	data : [
		{value : 1, text : '系统'},
		{value : 2, text : '调度'}
	]
})
