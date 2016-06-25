Ext.define('mvc.combo.sys.SysOSex',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 0,text : '未知'}
	,{value : 1,text : '男'}
	,{value : 2,text : '女'}
	]
});