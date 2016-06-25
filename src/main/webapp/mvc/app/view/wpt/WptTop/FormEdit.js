Ext.define('mvc.view.wpt.WptTop.FormEdit',{
	extend : 'Ext.form.Panel',
	requires : [ 'Ext.ux.DataTip' ],
	layout : 'form',
	border : false,
	frame : false,
	article : null,
	insFlag : true,
	bodyPadding : '5 5 5 5',
	url : base_path + '/wpt_WptTop_edit',
	fieldDefaults : {
		labelWidth : 100,
		labelStyle : 'font-weight : bold'
	},
	initComponent : function() {
		var formFlds = [];
		formFlds.push({
				xtype : 'panel',
				name : 'rtxt',
				html : "<div style='overflow:auto;max-height:500px;height:500px;width:800px' id='rtxt'></div>",
				items : [ {
					xtype : 'text',
					text : '正文:',
					style : {
						fontWeight : 'bolder'
					}
				} ]
			},  {
				xtype : 'textfield',
				name : 'bean.content',
				fieldLabel : '原文',
				hidden:true,
				listeners : {
					scope : this,
					change : function(field, newv,
							oldv) {
						UM.getEditor('rtxt')
								.setContent(newv);
					}
				}
			},{
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
	},
	getValues : function(asString, dirtyOnly, includeEmptyText,
			useDataValues) {
		var data = this.callParent();
		var content = UM.getEditor('rtxt').getContent();
		this.down("[name=bean.content]").setValue(content);
		Ext.apply(data, {
			"bean.content" : content
		})
		return data;
	},
	listeners : {
		'render' : function() {
			var ue = UM.getEditor('rtxt');
		},
		'destroy' : function() {
			var ue = UM.clearCache('rtxt');
		}

	}
});