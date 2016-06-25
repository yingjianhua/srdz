Ext.define('mvc.view.wx.WxAccount.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/wx_WxAccount_',
fieldDefaults : {
	labelWidth : 150,
	labelStyle : 'font-weight : bold'
},
initComponent : function(){
			if (this.insFlag)
				this.url = this.url + 'ins';
			else
				this.url = this.url + 'upd';
			var formFlds = [];
			formFlds.push
({xtype : 'textfield',name : 'bean.accountName',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '名称'}
	,{xtype : 'textfield',name : 'bean.accountToken',afterLabelTextTpl : required,allowBlank : false,fieldLabel : 'TOKEN'}
	,{xtype : 'textfield',name : 'bean.accountNumber',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '微信号'}
	,{xtype : 'textfield',name : 'bean.accountId',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '原始ID'}
	,
		mvc.Tools.crtComboForm(false,{
					name : 'bean.accountType',
					fieldLabel : '公众号类型',
					store : Ext.create('mvc.combo.wx.WxOAccountType'),
					value : 1,
				})
	,
		mvc.Tools.crtComboTrigger(true,'wx_WxOpenPlat','',{
					name : 'bean.openPlat',
					fieldLabel : '开放平台',
					listeners : {
						scope : this,
						change : function(fld, newv, oldv, opts) {
							if(newv) {
								this.down("[name=bean.agentAccount]").show();
								this.down("[name=bean.agentAccount]").diySql = "account_type=1 AND open_plat="+newv;
							} else {
								this.down("[name=bean.agentAccount]").hide();
								this.down("[name=bean.agentAccount]").disable(true);
							}
						}
					}
				})
	,{
		xtype : 'beantrigger',
		name : 'bean.agentAccount',
		fieldLabel : '代理服务号',
		bean : 'WxAccount',
		beanType : 'wx',
		emptyText : form_empty_text,
		hidden : true
	}
	,{xtype : 'textfield',name : 'bean.mchId', fieldLabel : '商户号'}
	,{xtype : 'textfield',name : 'bean.mchKey', fieldLabel : '商户平台密钥'}
	,{xtype : 'textfield',name : 'bean.accountEmail',fieldLabel : '电子邮箱'}
	,{xtype : 'textfield',name : 'bean.accountDesc',fieldLabel : '描述'}
	,{xtype : 'textfield',name : 'bean.accountAppid',afterLabelTextTpl : required,allowBlank : false,fieldLabel : 'APPID'}
	,{xtype : 'textfield',name : 'bean.accountAppsecret',afterLabelTextTpl : required,allowBlank : false,fieldLabel : 'APPSECRET'}
	,{
		xtype : 'beantrigger',
		name : 'bean.userSys',
		fieldLabel : '负责人',
		bean : 'SysUser',
		beanType : 'sys',
		emptyText : form_empty_text,
		afterLabelTextTpl : required,
		allowBlank : false
	},{xtype : 'numberfield',name : 'bean.rowVersion',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '版本',hidden : true,allowDecimals : false}
	,{
		xtype : 'hiddenfield',
		name : 'bean.pkey'
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