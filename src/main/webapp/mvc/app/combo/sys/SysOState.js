Ext.define('mvc.combo.sys.SysOState',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 0,text : '试用期'}
	,{value : 1,text : '在职'}
	,{value : 11,text : '停薪留职'}
	,{value : 81,text : '退休'}
	,{value : 91,text : '辞职'}
	,{value : 92,text : '试用期离职'}
	,{value : 93,text : '开除'}
	]
});