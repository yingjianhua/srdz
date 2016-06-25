Ext.define('mvc.combo.sys.SysOBufType', {
	extend : 'Ext.data.Store',
	fields : ['value', 'text'],
	data : [
		{value : 0, text : '不缓冲'},
		{value : 1, text : '指定记录缓冲'},
		{value : 2, text : '整表缓冲'}
	]
})
