Ext.define('mvc.combo.sys.SysOOptCht', {
	extend : 'Ext.data.Store',
	fields : ['value', 'text'],
	data : [
		{value : 1, text : '包含'},
		{value : 2, text : '开头'},
		{value : 3, text : '='},
		{value : 4, text : '≠'},
		{value : 5, text : '>'},
		{value : 6, text : '<'},
		{value : 7, text : '≥'},
		{value : 8, text : '≤'},
		{value : 9, text : '为空'},
		{value : 10, text : '不为空'},
		{value : 11, text : '间于'}
	]
})
