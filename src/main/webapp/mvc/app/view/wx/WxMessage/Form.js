Ext.define('mvc.view.wx.WxMessage.Form', {
	extend : 'Ext.form.Panel',
	requires : [ 'Ext.ux.DataTip' ],
	layout : 'form',
	border : false,
	frame : false,
	insFlag : true,
	bodyPadding : '5 5 5 5',
	url : base_path + '/wx_WxMessage_',
	fieldDefaults : {
		labelWidth : 100,
		labelStyle : 'font-weight : bold'
	},
	initComponent : function() {
		this.url = this.url + 'reply';
		var formFlds = [];
		formFlds.push({
			xtype : 'datefield',
			name : 'bean.createdTime',
			value : 'Env.getTranBeginTime()',
			fieldLabel : '关注用户',
			labelWidth : 240,
			afterLabelTextTpl : required,
			allowBlank : false,
			readOnly : true,
			format : 'Y-m-d H:i:s',
			disabled : true,
			disabledCls : ''
		}, {
			xtype : 'textareafield',
			grow : true,
			name : 'bean.content',
			anchor : '100%',
			disabled : true,
			disabledCls : '',
		}, mvc.Tools.crtComboForm(false, {
			name : 'bean.msgType',
			fieldLabel : '消息类型',
			store : Ext.create('mvc.combo.wx.WxOWxMsgType'),
			value : 1,
      listeners : {
        scope : this,
        change : function(field, newv, oldv, opts) {
            if(newv==1){
                this.down('[itemId=content]').show();
            }
            if(newv==2){
                this.down('[itemId=content]').hide();
            }
        }
    }
		}), {
			xtype : 'textareafield',
			grow : true,
			itemId : 'content',
			name : 'bean.content',
			anchor : '80%',
			fieldLabel : '回复',
			labelAlign : 'top'
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
		}, {
			xtype : 'beantrigger',
			name : 'bean.wxUser',
			hidden : true
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