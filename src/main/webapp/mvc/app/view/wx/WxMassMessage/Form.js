Ext.define('mvc.view.wx.WxMassMessage.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/wx_WxMassMessage_',
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
					name : 'bean.massmsgType',
					fieldLabel : '群发消息类型',
					store : Ext.create('mvc.combo.wx.WxOMassMsgType'),
					value : 1
				})
	,{xtype : 'beantrigger',name : 'bean.template',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '消息模板'}
	,
		mvc.Tools.crtComboForm(false,{
					name : 'bean.isToAll',
					fieldLabel : '向全部用户',
					store : Ext.create('mvc.combo.sys.SysOYn'),
					value : 0
				})
	,{xtype : 'textfield',name : 'bean.userGroup',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '群发分组'}
	,{xtype : 'datefield',name : 'bean.createdTime',value : 'Env.getTranBeginTime()',fieldLabel : '发送时间',afterLabelTextTpl : required,allowBlank : false,format : 'Y-m-d H:i:s'}
	,{xtype : 'datefield',name : 'bean.completeTime',fieldLabel : '完成时间',format : 'Y-m-d H:i:s'}
	,{xtype : 'textfield',name : 'bean.rem',fieldLabel : '备注'}
	,{xtype : 'numberfield',name : 'bean.msgId',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '消息ID',allowDecimals : false}
	,{xtype : 'numberfield',name : 'bean.msgDataId',value : 0,fieldLabel : '数据ID',allowDecimals : false}
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