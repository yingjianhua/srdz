Ext.define('mvc.combo.lg.LgOClient', {
	extend : 'Ext.data.Store',
	fields : ['value', 'text'],
	data : [
		{value : 0, text : 'Windows'},
		{value : 1, text : 'IOS'},
		{value : 2, text : 'Andriod'}
	]
})
