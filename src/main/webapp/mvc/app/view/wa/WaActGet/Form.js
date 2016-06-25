Ext.define('mvc.view.wa.WaActGet.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/wa_WaActGet_',
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
		mvc.Tools.crtComboTrigger(false,'wa_WaAct','',{
					name : 'bean.act',
					fieldLabel : '抽奖活动',
					readOnly : !this.insFlag
				})
	,
		mvc.Tools.crtComboTrigger(false,'wa_WaActItem','',{
					name : 'bean.actItem',
					fieldLabel : '奖项设置',
					readOnly : !this.insFlag
				})
	,
		mvc.Tools.crtComboTrigger(false,'wa_WaActPrize','',{
					name : 'bean.actPrize',
					fieldLabel : '奖品设置',
					readOnly : !this.insFlag
				})
	,{xtype : 'datefield',name : 'bean.actTime',value : 'Env.getTranBeginTime()',fieldLabel : '抽奖时间',afterLabelTextTpl : required,allowBlank : false,readOnly : !this.insFlag,format : 'Y-m-d H:i:s'}
	,{xtype : 'textfield',name : 'bean.actKey',fieldLabel : '兑奖KEY',readOnly : !this.insFlag}
	,{xtype : 'textfield',name : 'bean.recName',fieldLabel : '收货人名称'}
	,{xtype : 'textfield',name : 'bean.recAddr',fieldLabel : '收货人地址'}
	,{xtype : 'textfield',name : 'bean.recMobile',fieldLabel : '手机号码'}
	,{xtype : 'textfield',name : 'bean.sendName',fieldLabel : '快递名称'}
	,{xtype : 'textfield',name : 'bean.sendNum',fieldLabel : '快递编号'}
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