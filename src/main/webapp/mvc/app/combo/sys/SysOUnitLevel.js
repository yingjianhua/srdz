Ext.define('mvc.combo.sys.SysOCellLevel', {
	extend : 'Ext.data.Store',
	fields : ['value', 'text'],
	data : [
		{value : 1, text : '机构'},
		{value : 2, text : '部门'}
	]
})
