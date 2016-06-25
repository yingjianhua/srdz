Ext.define('mvc.view.sys.SysEm.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/sys_SysEm_',
fieldDefaults : {
	labelWidth : 100,
	width : 275,
	labelStyle : 'font-weight : bold'
},
initComponent : function(){
			if (this.insFlag)
				this.url = this.url + 'ins';
			else
				this.url = this.url + 'upd';
			var formFlds = [];
			formFlds.push
({xtype : 'textfield',name : 'bean.code',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '工号'}
	,{xtype : 'textfield',name : 'user.loginName',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '登录账号',readOnly : !this.insFlag}
	,{xtype : 'textfield',name : 'bean.name',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '名称'}
	,{xtype : 'textfield',name : 'bean.nickname',fieldLabel : '昵称'}
	,
		mvc.Tools.crtComboForm(false,{
					name : 'bean.state',
					fieldLabel : '职员状态',
					store : Ext.create('mvc.combo.sys.SysOState'),
					value : 1
				})
	,
		mvc.Tools.crtComboTrigger(false,'sys_SysOrg','',{
					name : 'bean.org',
					fieldLabel : '机构'
				})
	,{
		xtype : 'beantrigger',
		name : 'bean.dept',
		fieldLabel : '部门',
		bean : 'SysDept',
		beanType : 'sys',
		emptyText : form_empty_text,
		afterLabelTextTpl : required,
		allowBlank : false
	},{xtype : 'numberfield',name : 'bean.rowVersion',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '版本',hidden : true,allowDecimals : false}
	,
		mvc.Tools.crtComboForm(true,{
					name : 'one.peCardType',
					fieldLabel : '个人证件类型',
					store : Ext.create('mvc.combo.sys.SysOCardPersonType'),
					value : 0
				})
	,{xtype : 'textfield',name : 'one.peCardNumb',fieldLabel : '个人证件号码'}
	,{xtype : 'textfield',name : 'one.peMobile',fieldLabel : '个人常用手机'}
	,{xtype : 'textfield',name : 'one.peEmail',fieldLabel : '个人邮箱'}
	,{xtype : 'textfield',name : 'one.peWx',fieldLabel : '个人微信'}
	,{xtype : 'textfield',name : 'one.peQq',fieldLabel : '个人QQ'}
	,
		mvc.Tools.crtComboForm(true,{
					name : 'one.peSex',
					fieldLabel : '个人性别',
					store : Ext.create('mvc.combo.sys.SysOSex'),
					value : 0
				})
	,{xtype : 'datefield',name : 'one.peBirthday',fieldLabel : '个人出生日期',format : 'Y-m-d'}
	,
		mvc.Tools.crtComboForm(true,{
					name : 'one.peMerry',
					fieldLabel : '个人婚姻状况',
					store : Ext.create('mvc.combo.sys.SysOMerry'),
					value : 0
				})
	,{xtype : 'textfield',name : 'one.hoTel',fieldLabel : '家庭电话'}
	,{xtype : 'textfield',name : 'one.hoAddr',fieldLabel : '家庭地址'}
	,{xtype : 'textfield',name : 'one.hoZipCode',fieldLabel : '家庭邮编'}
	,{xtype : 'textfield',name : 'one.rem',fieldLabel : '备注'}
	,{
		xtype : 'hiddenfield',
		name : 'bean.pkey'
	});
	this.items = [{
		layout : {
			type : 'table',
			columns : 2,
			itemCls : 'x-layout-table-items-form'
		},
		border : false,
		items : formFlds
	}];
	this.callParent(arguments);
}
});