Ext.define('mvc.guide.guide_sal', {
			extend : 'Ext.panel.Panel',
			id : 'guide_sal',
			loadMask : true,
			multiSelect : true,
			title : "销售模块导航",
			iconCls : 'nav-ico',
			closable : true,
			autoDestroy : true,
			roles : '',
			layout : 'border',
			initComponent : function() {
				this.items = [{
							region : 'north',
							xtype : 'toolbar',
							itemId : '1',
							bodyStyle : 'background:#f00',
							defaults : {
								style : 'background-color:#ffffff',
								border : '0 0 0 0',
								margin : 0
							},
							items : [{
										xtype : 'button',
										itemId : 'SalDiscountPriv',
										icon : '../images/guide/prot.png',
										scale : 'large',
										iconAlign : 'top',
										text : '折扣权限'
									}, {
										xtype : 'button',
										itemId : "2",
										icon : '../images/guide/prot.png',
										scale : 'large',
										iconAlign : 'top',
										text : '销售价格协议'
									}, {
										xtype : 'button',
										icon : '../images/guide/prot.png',
										itemId : '1',
										scale : 'large',
										iconAlign : 'top',
										text : '调拨价格协议'
									}, {
										xtype : 'button',
										icon : '../images/guide/prot.png',
										scale : 'large',
										iconAlign : 'top',
										text : '客户常用商品'
									}]

						}, {
							region : 'center',
							xtype : 'toolbar',
							layout : 'absolute',
							defaults : {
								scope : this,
								style : 'background-color:#ffffff',
								border : 0,
								margin : 0,
								width : 60,
								height : 60,
								handler : this.onClick2
							},
							items : [{
										xtype : 'box',
										x : 100,
										y : 225,
										iconAlign : 'top',
										text : '仓库',
										width : 320,
										height : 10,
										autoEl : {
											tag : 'img',
											src : '../images/line_blue.png',
											width : 320,
											height : 10
										}
									}, {
										xtype : 'box',
										x : 420,
										y : 115,
										iconAlign : 'top',
										text : '仓库',
										width : 10,
										height : 220,
										autoEl : {
											tag : 'img',
											src : '../images/line_blue.png',
											width : 10,
											height : 220
										}
									}, {
										xtype : 'box',
										x : 125,
										y : 325,
										iconAlign : 'top',
										text : '仓库',
										width : 300,
										height : 10,
										autoEl : {
											tag : 'img',
											src : '../images/line_blue.png',
											width : 300,
											height : 10
										}
									}, {
										xtype : 'box',
										x : 125,
										y : 225,
										iconAlign : 'top',
										text : '仓库',
										width : 10,
										height : 100,
										autoEl : {
											tag : 'img',
											src : '../images/line_blue.png',
											width : 10,
											height : 100
										}
									}, {
										xtype : 'box',
										icon : '../images/guide/warehouse.png',
										
										scale : 'large',
										x : 100,
										y : 200,
										iconAlign : 'top',
										text : '仓库',
										autoEl : {
											tag : 'img',
											src : '../images/guide/warehouse.png'
										}
									}, {
										xtype : 'button',
										icon : '../images/sal.png',
										scale : 'large',
										x : 200,
										y : 200,
										iconAlign : 'top',
										text : '销售单'
									}, {
										xtype : 'button',
										icon : '../images/sal.png',
										scale : 'large',
										x : 300,
										y : 200,
										iconAlign : 'top',
										text : '退货单'
									}, {
										xtype : 'button',
										icon : '../images/guide/custom.png',
										scale : 'large',
										x : 400,
										y : 200,
										iconAlign : 'top',
										text : '客户'
									}, {
										xtype : 'button',
										icon : '../images/sal.png',
										scale : 'large',
										x : 400,
										y : 100,
										iconAlign : 'top',
										text : '直销单'
									}, {
										xtype : 'button',
										icon : '../images/sal.png',
										scale : 'large',
										x : 300,
										y : 300,
										iconAlign : 'top',
										text : '赠送单'
									}]
						}]
				this.callParent(arguments);
			},
			onClick2 : function() {
				var me = this;
				Ext.Ajax.request({
							method : 'GET',
							async : false,
							url : 'sys_SysMenu_loadMenu?type=sal&node=root',
							success : function(response, options) {
								var result = Ext.decode(response.responseText);
								alert(result[1].roles);
								var button = me.getComponent('1').getText();
								alert(me.getComponent('2').getText());
								if (result.success) {
									var bean = Ext.create(
											'mvc.model.cst.CstIn', result);
									Ext.apply(selection.data, bean.data);
									selection.commit();
									me.mdMainTable.getSelectionModel()
											.deselectAll();
									me.mdMainTable.getView().select(selection);
									Ext.example.msg(msg_title, '记账--成功');
								} else {
									Ext.MessageBox.show({
												title : msg_title,
												msg : result.msg,
												buttons : Ext.MessageBox.OK,
												icon : Ext.MessageBox.ERROR
											});
								}
							}
						})
				var mainTab = Ext.getCmp('mainTab');
				var tab = mainTab.getComponent(record.get('id'));
				if (!tab) {
					var p = {
						title : record.get('text'),
						id : record.get('id'),
						iconCls : 'tab-user-icon',
						roles : record.get('roles'),
						menuType : record.get('beanType'), // 用于TAB点击时，自动跳到对应的菜单模块下
						closable : true,
						autoDestroy : true
					};
					var url = "mvc." + record.get('url');
					tab = mainTab.add(Ext.create(url, p));
				}
				mainTab.setActiveTab(tab);
			}
		});