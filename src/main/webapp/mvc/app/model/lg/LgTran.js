Ext.define('mvc.model.lg.LgTran', {
	extend : 'Ext.data.Model',
	idProperty : 'bean.pkey',
	proxy : {
		type : 'ajax',
		url : base_path+'/lg_LgTran_load'
	},
	fields : [
		{name : 'bean.pkey' , mapping : 'pkey' , type : 'int', useNull : true},
		{name : 'bean.login' , mapping : 'login' , type : 'string', outkey : true},
		{name : 'bean.bTime' , mapping : 'bTime' , type : 'date' ,dateFormat : 'Y-m-d H:i:s'},
		{name : 'bean.act' , mapping : 'act' , type : 'string', outkey : true},
		{name : 'bean.procTime' , mapping : 'procTime' , type : 'int', useNull : true},
		{name : 'bean.successFlag' , mapping : 'successFlag' , type : 'int', useNull : true},
		{name : 'bean.objTable' , mapping : 'objTable' , type : 'string', outkey : true},
		{name : 'bean.objPkey' , mapping : 'objPkey' , type : 'int', useNull : true},
		{name : 'bean.rem' , mapping : 'rem' , type : 'string'}
	]
});
