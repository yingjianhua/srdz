Ext.define('mvc.combo.sys.SysOUserType', {
	extend : 'Ext.data.Store',
	fields : ['value', 'text'],
	data : [
		{value : 1, text : '系统用户'},
		{value : 2, text : '职员'},
		{value : 5, text : '个人客户'},
		{value : 6, text : '企业客户'}
	]
})
