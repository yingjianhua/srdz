Ext.define('mvc.combo.sys.SysOSupplierType', {
	extend : 'Ext.data.Store',
	fields : ['value', 'text'],
	data : [
		{value : 1, text : '供货商'},
		{value : 2, text : '运输商'},
		{value : 3, text : '委托加工商'},
		{value : 4, text : '服务提供商'},
		{value : 99, text : '其它'}
	]
})
