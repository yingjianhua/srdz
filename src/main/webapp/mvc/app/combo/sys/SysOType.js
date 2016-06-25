Ext.define('mvc.combo.sys.SysOType', {
	extend : 'Ext.data.Store',
	fields : ['value', 'text'],
	data : [
		{value : 1, text : '连续编号'},
		{value : 2, text : '4位年份(YYYY)'},
		{value : 3, text : '2位年份(YY)'},
		{value : 4, text : '2位年份与月份(YYMM)'},
		{value : 5, text : '按日编号'}
	]
})
