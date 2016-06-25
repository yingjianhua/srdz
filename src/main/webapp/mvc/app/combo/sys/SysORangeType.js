Ext.define('mvc.combo.sys.SysORangeType',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 1,text : '集团级'}
	,{value : 11,text : '上下级机构'}
	,{value : 12,text : '及下级机构'}
	,{value : 13,text : '及上级机构'}
	,{value : 14,text : '本机构'}
	,{value : 21,text : '上下级核算单元'}
	,{value : 22,text : '及下级核算单元'}
	,{value : 23,text : '及上级核算单元'}
	,{value : 24,text : '本核算单元'}
	]
});