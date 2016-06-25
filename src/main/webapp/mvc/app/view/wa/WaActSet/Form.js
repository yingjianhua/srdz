Ext.define('mvc.view.wa.WaActSet.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/wa_WaActSet_',
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
					fieldLabel : '抽奖活动'
				})
	,
		mvc.Tools.crtComboTrigger(false,'wa_WaActItem','',{
					name : 'bean.actItem',
					fieldLabel : '奖项设置'
				})
	,
		mvc.Tools.crtComboTrigger(false,'wa_WaActPrize','',{
					name : 'bean.actPrize',
					fieldLabel : '奖品设置'
				})
	,{xtype : 'numberfield',name : 'bean.actNum',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '奖品数量',allowDecimals : false}
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