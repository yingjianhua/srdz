Ext.define('mvc.view.wpt.WptDistributionRule.Form', {
	extend : 'Ext.form.Panel',
	layout : 'form',
	border : false,
	frame : false,
	insFlag : true,
	bodyPadding : '5 5 5 5',
	url : base_path + '/wpt/resource/distributionRule_upd',
	fieldDefaults : {
		labelWidth : 100,
		labelStyle : 'font-weight : bold'
	},
	initComponent : function() {
		var formFlds = [];
		formFlds.push({
			xtype : 'numberfield',
			name : 'bean.bonus2',
			id : 'bonus2',
			fieldLabel : '二级邀请人提成百分比'
		},
		{
			xtype : 'numberfield',
			name : 'bean.rowVersion',
			id : 'rowVersion',
			value : 0,
			afterLabelTextTpl : required,
			allowBlank : false,
			fieldLabel : '版本',
			hidden : true,
			allowDecimals : false
		}, {
			xtype : 'hiddenfield',
			name : 'bean.pkey',
			id : 'pkey'
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