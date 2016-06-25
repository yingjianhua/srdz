Ext.define('mvc.view.wx.WxUser.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/wx_WxUser_',
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
(
		mvc.Tools.crtComboForm(false,{
					name : 'bean.status',
					fieldLabel : '关注状态',
					store : Ext.create('mvc.combo.wx.WxOStatus'),
					value : 1
				})
	,{xtype : 'textfield',name : 'bean.openId',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '粉丝ID'}
	,{xtype : 'textfield',name : 'bean.nickname',fieldLabel : '昵称'}
	,
		mvc.Tools.crtComboForm(false,{
					name : 'bean.sex',
					fieldLabel : '性别',
					store : Ext.create('mvc.combo.sys.SysOSex'),
					value : 0
				})
	,{xtype : 'textfield',name : 'bean.city',fieldLabel : '城市'}
	,{xtype : 'textfield',name : 'bean.province',fieldLabel : '省份'}
	,{xtype : 'textfield',name : 'bean.country',fieldLabel : '国家'}
	,{xtype : 'textfield',name : 'bean.imageUrl',fieldLabel : '头像'}
	,{xtype : 'textfield',name : 'bean.rem',fieldLabel : '备注'}
	,
		mvc.Tools.crtComboTrigger(false,'wx_WxUserGroup','',{
					name : 'bean.userGroup',
					fieldLabel : '用户分组'
				})
	,{xtype : 'datefield',name : 'bean.subscribeTime',fieldLabel : '关注时间',afterLabelTextTpl : required,allowBlank : false,format : 'Y-m-d H:i:s'}
	,{xtype : 'datefield',name : 'bean.syncTime',value : 'Env.getTranBeginTime()',fieldLabel : '同步时间',afterLabelTextTpl : required,allowBlank : false,format : 'Y-m-d H:i:s'}
	,{xtype : 'numberfield',name : 'bean.rowVersion',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '版本',hidden : true,allowDecimals : false}
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