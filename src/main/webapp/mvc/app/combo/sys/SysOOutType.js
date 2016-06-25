Ext.define('mvc.combo.sys.SysOOutType', {
	extend : 'Ext.data.Store',
	fields : ['value', 'text'],
	data : [
		{value : 1, text : '选项'},
		{value : 11, text : '万元显示'},
		{value : 12, text : '亿元显示'},
		{value : 13, text : '百分比显示'},
		{value : 14, text : '千分比显示'},
		{value : 15, text : '金额千分位显示'},
		{value : 16, text : '金额'}
	]
})
