Ext.define('mvc.view.wm.WmImage.Form', {
	extend : 'Ext.form.Panel',
	requires : [ 'Ext.ux.DataTip' ],
	layout : 'form',
	border : false,
	frame : false,
	insFlag : true,
	bodyPadding : '5 5 5 5',
	url : base_path + '/wm_WmImage_',
	fieldDefaults : {
		labelWidth : 100,
		labelStyle : 'font-weight : bold'
	},
	initComponent : function() {
		if (this.insFlag)
			this.url = this.url + 'ins';
		else
			this.url = this.url + 'upd';
		var formFlds = [];
		formFlds.push({
			xtype : 'textfield',
			name : 'bean.name',
			afterLabelTextTpl : required,
			allowBlank : false,
			fieldLabel : '名称'
		}, {
			xtype : 'imagefield',
			name : 'bean.imgUrl',
			labelWidth : this.fieldDefaults.labelWidth,
			afterLabelTextTpl : required,
			allowBlank : false,
			fieldLabel : '图片'
		}, {
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