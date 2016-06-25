Ext.define('mvc.view.sys.SysCtype.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/sys_SysCtype_',
fieldDefaults : {
	labelWidth : 100,
	labelStyle : 'font-weight : bold'
},
plugins : {
	ptype : 'datatip'
},
initComponent : function(){
			if (this.insFlag)
				this.url = this.url + 'ins';
			else
				this.url = this.url + 'upd';
			var formFlds = [];
			formFlds.push
({
		xtype : 'textfield',
		name : 'bean.pkey',
		afterLabelTextTpl : required,
		allowBlank : false,
		afterLabelTextTpl : required,
		allowBlank : false,
		fieldLabel : '编号'
	},{xtype : 'textfield',name : 'bean.ctypeName',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '名称'}
	,{xtype : 'textfield',name : 'bean.ctypeDes',fieldLabel : '描述'}
	,{xtype : 'numberfield',name : 'bean.ctypeLen',value : '0',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '代码长度',allowDecimals : false}
	,{xtype : 'numberfield',name : 'bean.rowVersion',value : '0',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '版本',hidden : true,allowDecimals : false}
	);
	this.items = [{
		layout : {
			type : 'vbox',
			align : 'stretch'
		},
		border : false,
		items : formFlds
	}];
	this.callParent(arguments);
}
});