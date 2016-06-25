Ext.define('mvc.combo.prv.PrvOPrvType',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 0,text : '无法查看'}
	,{value : 1,text : '本人'}
	,{value : 11,text : '本部门'}
	,{value : 12,text : '本级及下级部门'}
	,{value : 21,text : '本核算单元'}
	,{value : 22,text : '本级及下级核算单元'}
	,{value : 31,text : '本机构'}
	,{value : 32,text : '本级及下级机构'}
	,{value : 99,text : '不限制'}
	]
});