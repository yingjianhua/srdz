Ext.define('mvc.view.wpt.WptServiceCen.Form', {
	extend : 'Ext.form.Panel',
	requires : [ 'Ext.ux.DataTip' ],
	layout : 'form',
	border : false,
	frame : false,
	insFlag : true,
	bodyPadding : '5 5 5 5',
	url : base_path + '/wpt_WptServiceCen_insOrUpd',
	fieldDefaults : {
		labelWidth : 100,
		labelStyle : 'font-weight : bold'
	},
	initComponent : function() {
		var formFlds = [];
		formFlds.push({
			xtype : 'textfield',
			name : 'bean.mobile',
			fieldLabel : '客服电话'
		}, {
			xtype : 'imagefield',
			name : 'bean.qrcode',
			afterLabelTextTpl : required,
			allowBlank : false,
			labelWidth : this.fieldDefaults.labelWidth,
			fieldLabel : '二维码'
		},
		{
			xtype : 'textfield',
			name : 'bean.smsTips',
			fieldLabel : '短信提醒',
			emptyText : "多个手机号之间用逗号分隔"
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