Ext.define('mvc.tools.FileUrlField', {
	extend : 'Ext.form.Panel',
	alias : 'widget.fileurlfield',
	field1 : null,
	field2 : null,
	name : null,
	margin : '0 0 5 0',
	layout : "hbox",
	url : base_path + '/sys_SysAccessory_uploadFile',
	initComponent : function() {
		var me = this;
		me.items = [ {
			xtype : 'textfield',
			fieldLabel : me.fieldLabel,
			flex : 1,
			name : me.name,
			afterLabelTextTpl : me.afterLabelTextTpl,
			allowBlank : me.allowBlank,
			margin : '0 5 0 0',
			readOnly : true,
		}, {
			xtype : 'filefield',
			name : 'file',
			buttonText : "浏览",
			buttonOnly : true,
			listeners : {
				scope : this,
				change : function(field, newv, oldv, opts) {
					this.uploads();
				}
			}
		} ], this.callParent(arguments);
		me.field1 = me.child();
		me.field2 = me.field1.nextNode();
	},
	uploads : function() {
		var me = this;
		me.field1.setDisabled(true);
		if (me.isValid()) {
			me.submit({
				url : me.url,
				submitEmptyText : false,
				type : 'ajax',
				params : {
					insFlag : this.insFlag
				},
				success : function(form, action) {
					var result = action.result;
					me.field1.setValue(result.url);
					me.field1.setDisabled(false);
				},
				failure : function(form, action) {
					Ext.MessageBox.show({
						title : msg_title,
						msg : action.result.msg,
						buttons : Ext.MessageBox.OK,
						icon : Ext.MessageBox.ERROR
					});
					me.field1.setDisabled(false);
				},
				waitTitle : wait_title,
				waitMsg : wait_msg,
				scope : this
			});
		}
	}
});
