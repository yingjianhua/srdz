Ext.define('mvc.view.wpt.WptFeedBack.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/wpt_WptFeedBack_',
fieldDefaults : {
	labelWidth : 100,
	labelStyle : 'font-weight : bold'
},
initComponent : function(){
			if (this.insFlag)
				this.url = this.url + 'ins';
			else
				this.url = this.url + 'upd';
			var formFlds = [];
			formFlds.push
({xtype : 'textfield',name : 'bean.content',id : 'content',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '内容'}
	,{xtype : 'textfield',name : 'bean.contactWay',id : 'contactWay',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '联系方式'}
	,
		mvc.Tools.crtComboForm(false,{
					name : 'bean.contactType',
					id : 'contactType',
					fieldLabel : '联系方式类型',
					store : Ext.create('mvc.combo.wpt.WptOContactStatus'),
					value : 0
				})
	,{
		xtype : 'beantrigger',
		name : 'bean.handleMan',
		id : 'handleMan',
		fieldLabel : '处理人',
		bean : 'SysUser',
		beanType : 'sys',
		emptyText : form_empty_text,
		afterLabelTextTpl : required,
		allowBlank : false
	},
		mvc.Tools.crtComboForm(false,{
					name : 'bean.isHandle',
					id : 'isHandle',
					fieldLabel : '已处理',
					store : Ext.create('mvc.combo.sys.SysOYn'),
					value : 0
				})
	,{xtype : 'numberfield',name : 'bean.rowVersion',id : 'rowVersion',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '版本',hidden : true,allowDecimals : false}
	,{
		xtype : 'hiddenfield',
		name : 'bean.pkey',
		id : 'pkey'
	});
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