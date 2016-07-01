Ext.define('mvc.view.wpt.WptRedPackRule.Form', {
	extend : 'Ext.form.Panel',
	layout : 'form',
	border : false,
	frame : false,
	insFlag : true,
	bodyPadding : '5 5 5 5',
	url : base_path + '/wpt_WptRedPackRule_upd',
	fieldDefaults : {
		labelWidth : 100,
		labelStyle : 'font-weight : bold'
	},
	initComponent : function() {
		var formFlds = [];
		formFlds.push({
			xtype : 'textfield',
			name : 'bean.sendName',
			fieldLabel : '商户名称',
			afterLabelTextTpl : required,
			allowBlank : false
		}, {
			xtype : 'textfield',
			name : 'bean.wishing',
			fieldLabel : '红包祝福语',
			afterLabelTextTpl : required,
			allowBlank : false
		},
		{
			xtype : 'textfield',
			name : 'bean.actName',
			fieldLabel : '活动名称',
			afterLabelTextTpl : required,
			allowBlank : false
		},
		{
			xtype : 'numberfield',
			name : 'bean.rowVersion',
			value : 0,
			afterLabelTextTpl : required,
			allowBlank : false,
			fieldLabel : '版本',
			hidden : true,
			allowDecimals : false
		}, {
			xtype : 'hiddenfield',
			name : 'bean.pkey'
		});
		this.items = [ {
			layout : {
				type : 'vbox',
				align : 'stretch'
			},
			border : false,
			items : formFlds
		} ];
		this.callParent(arguments);
	}
});