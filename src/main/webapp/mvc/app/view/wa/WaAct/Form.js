Ext.define('mvc.view.wa.WaAct.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/wa_WaAct_',
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
({xtype : 'textfield',name : 'bean.name',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '活动名称'}
	,{xtype : 'textfield',name : 'bean.title',fieldLabel : '活动描述'}
	,{xtype : 'numberfield',name : 'bean.doCount',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '抽奖次数',allowDecimals : false}
	,{xtype : 'datefield',name : 'bean.startTime',value : 'Env.getTranBeginTime()',fieldLabel : '开始时间',afterLabelTextTpl : required,allowBlank : false,format : 'Y-m-d H:i:s'}
	,{xtype : 'datefield',name : 'bean.endTime',value : 'Env.getTranBeginTime()',fieldLabel : '结束时间',afterLabelTextTpl : required,allowBlank : false,format : 'Y-m-d H:i:s'}
	,{xtype : 'numberfield',name : 'bean.rate',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '中奖概率(%)',decimalPrecision : 4}
	,
		mvc.Tools.crtComboForm(false,{
					name : 'bean.type',
					fieldLabel : '抽奖类型',
					store : Ext.create('mvc.combo.wa.WaOActDrawType'),
					value : 1
				})
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