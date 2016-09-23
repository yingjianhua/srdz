Ext.define('mvc.view.wpt.WptServiceCenter.Form', {
	extend : 'Ext.form.Panel',
	requires : [ 'Ext.ux.DataTip' ],
	layout : 'form',
	border : false,
	frame : false,
	insFlag : true,
	bodyPadding : '5 5 5 5',
	url : base_path + '/wpt/resource/serviceCenter_insOrUpd',
	fieldDefaults : {
		labelWidth : 100,
		labelStyle : 'font-weight : bold'
	},
	initComponent : function() {
		var formFlds = [];
		formFlds.push({
			xtype : 'textfield',
			name : 'bean.mobile',
			id : 'mobile',
			fieldLabel : '客服电话'
		}, {
			xtype : 'imagefield',
			name : 'bean.qrcode',
			id : 'qrcode',
			afterLabelTextTpl : required,
			allowBlank : false,
			labelWidth : this.fieldDefaults.labelWidth,
			fieldLabel : '二维码'
		},
		{
			xtype : 'textfield',
			name : 'bean.smsTips',
			id : 'smsTips',
			fieldLabel : '短信提醒',
			emptyText : "多个手机号之间用逗号分隔"
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