Ext.define('mvc.controller.Menu', {
			extend : 'Ext.app.Controller',
			views : ['TabPanel'],
			refs : [{
						ref : 'mainTab',
						selector : 'mainTab'
					},{
						ref : 'grid',
						selector : 'xxjxx'
					}
			],

			init : function() {
				this.control({
							'mainmenu' : {
								itemmousedown : this.loadMenu
							},
							'xxjxx' : {
								cellclick : this.test
							}
						})
			},
			loadMenu : function(selModel, record) {
				if (record.data.leaf) {
					var mainTab = this.getMainTab();
					var tab = mainTab.getComponent(record.get('id'));
					if (!tab) {
						var p = {
							title : record.get('text'),
							id : record.get('id'),
							iconCls : 'tab-user-icon',
							roles : record.get('roles'),
							menuType : record.get('beanType'), //用于TAB点击时，自动跳到对应的菜单模块下
							closable : true,
							autoDestroy:true
//							onDestroy: function(){
//						        this.callParent();
//						    	if(Ext.isIE){
//                                    CollectGarbage();
//                                }
//						    }
						};
						var url = "mvc." + record.get('url');
						tab = mainTab.add(Ext.create(url, p));
					}
					mainTab.setActiveTab(tab);
//					if (tab.getStore() && typeof tab.isSt == "undefined")
//						tab.getStore().load({
//						    scope   : this,
//						    callback: function(records, operation, success) {
//						        if (!success){
//						        	Ext.example.msg(msg_title,tab.getStore().getProxy().getReader().jsonData.msg);
//						        }
//						    }
//						});
				}
			},
			test : function(grid, td, cellIndex, record, tr, rowIndex, e, eOpts) {
				var update = e.getTarget('.update');

				if(update) {
					this.getGrid().onUpdRow(grid,rowIndex,record);
					/*var tableType = record.get('bean.tableType');
					Ext.example.msg("tableType",""+tableType);
					if(tableType == 1) {
						this.getGrid().up('panel').down('#GlReport_list_maintableZC').onUpdRow(grid,rowIndex);
					} else if (tableType == 2 || tableType == 3) {
						this.getGrid().up('panel').down('#GlReport_list_maintableFS').onUpdRow(grid,rowIndex);
					} else if (tableType == 4) {						
						this.getGrid().up('panel').down('#GlReport_list_maintableLR').onUpdRow(grid,rowIndex);
					}*/
				}
			}

		})
