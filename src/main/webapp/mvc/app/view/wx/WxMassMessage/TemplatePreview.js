Ext.define('mvc.view.wx.WxMassMessage.TemplatePreview', {
			extend : 'Ext.window.Window',
			width : 350,
			autoScroll:true,
			height : '80%',
			pkey : null,
			layout : {
				type : 'hbox',
				pack : 'start',
				align : 'stretchmax'
			},
			form : null,
			resizable : true,
			url : base_path + '/wm_WmNews_',
			modal : true,
			iconCls : 'app-icon',
			insFlag : true,
			articles : null,
			form : null,
			initComponent : function() {
				var me = this;
				var articles = []
				this.items = [
					Ext.create('Ext.panel.Panel', {
						margin : 10,
						layout : {
							type : 'vbox',
							pack : 'start',
							align : 'stretchmax'
						}
					})];
				this.buttonAlign = 'right', 
				this.buttons = [{
							text : '返回',
							scope : this,
							iconCls : 'arrowLeft-icon',
							handler : this.onReturn
						}, {
							text : '修改',
							scope : this,
							iconCls : 'upd-icon',
							handler : this.onUpd
						}, {
							text : '群发',
							scope : this,
							iconCls : 'win-save-icon',
							handler : this.onSend
						}];
				this.callParent(arguments);
				this.addEvents('onReturn');
				this.addEvents('onUpd');
				this.addEvents('onSend');
				this.articles = this.items.items[0];
			},
			onReload : function(pkey) {
				this.pkey = pkey;
				var data;
				Ext.Ajax.request({
					async : false,
					url : base_path+ '/wm_WmNews_load?pkey='+ pkey,
					success : function(response, options) {
						var result = Ext.decode(response.responseText);
						if (result.success) {
							data = result;
						} else {
							Ext.MessageBox.show({
								title : msg_title,
								msg : result.msg,
								buttons : Ext.MessageBox.OK,
								icon : Ext.MessageBox.ERROR
							});
						}
					}
				});
				this.articles.removeAll();
				for (var i = 0; i < data.count; i++) {
					var com = Ext.create('mvc.view.wm.WmNews.ContainerPic',{
							data : data.items[i],
							main : i == 0
						})
					this.articles.insert(i,com)
				}
			},
			onReturn : function() {
				this.fireEvent('onReturn');	 
			},
			onUpd : function() {
				this.fireEvent('onUpd', this.pkey);	 
			},
			onSend : function() {
				this.fireEvent('onSend', this.pkey);	 
			}
		});