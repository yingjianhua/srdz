Ext.define('mvc.view.wx.WxMessage.WinCheck', {
	extend : 'Ext.window.Window',
	width : 400,
	height : '70%',
	layout : 'fit',
	resizable : true,
	pkey : null,
	itemform : null,
	autoScroll : true,
	modal : true,
	iconCls : 'app-icon',
	initComponent : function() {
		var me = this;
		this.items = {
			xtype : 'form',
			autoScroll : true,
			listeners : {
				scope : this,
				render : function() {
					Ext.Ajax.request({
						url : base_path + '/wx_WxMessage_check?pkey=' + me.pkey,
						success : function(response) {
							var data = Ext.decode(response.responseText);
							Ext.each(data, function(record) {
								if (record['wxmsgDir'] == 1) {
									me.itemform.add({
										xtype : 'textfield',
										fieldLabel : record['wxUser'],
										labelWidth : 220,
										readOnly : true,
										value : record['createdTime']
									})
								}else{
									me.itemform.add({
										xtype : 'textfield',
										fieldLabel : record['account'],
										labelWidth : 220,
										readOnly : true,
										value : record['createdTime']
									})
								}
								me.itemform.add({
									xtype : 'textareafield',
									grow : true,
									readOnly : true,
									value : record['content']
								})
							})
						}
					});
				}
			},
			layout : {
				type : 'vbox',
				align : 'stretch'
			}
		}
		this.callParent();
		this.itemform = this.items.items[0];
	}
})