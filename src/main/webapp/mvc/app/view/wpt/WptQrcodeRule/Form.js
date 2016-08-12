Ext.define('mvc.view.wpt.WptQrcodeRule.Form', {
	extend : 'Ext.form.Panel',
	layout : 'form',
	border : false,
	frame : false,
	insFlag : true,
	bodyPadding : '5 5 5 5',
	url : base_path + '/wpt_WptQrcodeRule_upd',
	fieldDefaults : {
		labelWidth : 100,
		labelStyle : 'font-weight : bold'
	},
	initComponent : function() {
		var formFlds = [];
		formFlds.push({
			xtype : 'numberfield',
			name : 'bean.single',
			fieldLabel : '单笔消费金额'
		}, {
			xtype : 'numberfield',
			name : 'bean.amount',
			fieldLabel : '累计消费金额'
		},
		{
			xtype : 'numberfield',
			name : 'bean.validityPeriod',
			fieldLabel : '有效天数',
		},
		{
			xtype : 'numberfield',
			name : 'bean.aheadUpdate',
			fieldLabel : '提前更新天数',
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