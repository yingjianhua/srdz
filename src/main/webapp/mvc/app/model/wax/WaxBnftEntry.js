Ext.define('mvc.model.wax.WaxBnftEntry',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/wax_WaxBnftEntry_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.bnft',mapping : 'bnft',type : 'string',outkey : true}
	,{name : 'bean.name',mapping : 'name',type : 'string'}
	,{name : 'bean.mobile',mapping : 'mobile',type : 'string'}
	,{name : 'bean.imgUrl',mapping : 'imgUrl',type : 'string'}
	,{name : 'bean.wxUser',mapping : 'wxUser',type : 'string',outkey : true}
	,{name : 'bean.account',mapping : 'account',type : 'string',outkey : true}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});