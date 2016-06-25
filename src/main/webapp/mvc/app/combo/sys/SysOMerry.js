Ext.define('mvc.combo.sys.SysOMerry',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 0,text : '未知'}
	,{value : 1,text : '未婚'}
	,{value : 2,text : '已婚'}
	,{value : 3,text : '离异'}
	,{value : 4,text : '丧偶'}
	]
});