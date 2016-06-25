Ext.define('mvc.view.TabPanel', {
	extend : 'Ext.tab.Panel',
	alias : 'widget.mainTab',
	id : 'mainTab',
	uses : ['mvc.view.portal.PortalPanel', 'mvc.view.portal.PortalColumn',
			'mvc.view.portal.GridPortlet', 'mvc.view.portal.ChartPortlet',
			'Ext.ux.DataView.DragSelector', 'Ext.ux.DataView.LabelEditor'],
	getTools : function() {
		return [{
					xtype : 'tool',
					type : 'refresh',
					handler : function(e, target, panelHeader, tool) {
						var portlet = panelHeader.ownerCt;
//						portlet.setLoading('Loading...');
//						Ext.defer(function() {
//							portlet.setLoading(false);
//						}, 2000);
//						
//						var grid = portlet.down('grid');
//						var chart = portlet.down('chart');
//						var view = portlet.down('dataview');
//						if(grid){
//							grid.getStore().load();
//						}else if(chart){
//							chart.getStore().load();
//						}else if(view){
//							view.getStore().load();
//						}
					}
				}];
	},
	
	initComponent : function() {
		Ext.apply(this, {
			region : 'center',
			defaults : {
				autoScroll : true
			},
			activeTab : 0,
			plugins: [{
                ptype: 'tabscrollermenu',
                maxText  : 15,
                pageSize : 5
            }],
			items : [{
				id : 'HomePage',
				title : '首页',
				iconCls : 'home',
				layout : 'fit',
				xtype : 'container',
				region : 'center',
				layout : 'table',
				layoutConfig:{columns:4},
				defaults: {frame:true, width:280, height: 500},
				style: 'background-color : #fff',
				items:[{
					xtype : 'pilotagechart',
					width : 550,
				}]
//				items : [{
//					xtype : 'portalpanel',
//					region : 'center',
//					border : false,
//					items : [{
//						id : 'col-1',
//						items : [{
//									id : 'portlet-1',
//									title : '我的项目',
//									tools : this.getTools(),
//									items : Ext
//											.create('mvc.view.portal.GridPortlet'),
//									listeners : {
//										'close' : Ext.bind(this.onPortletClose,
//												this)
//									}
//								}, {
//									id : 'portlet-6',
//									title : '项目里程碑',
//									tools : this.getTools(),
//									items : Ext
//											.create('mvc.view.portal.GridPortlet3'),
//									listeners : {
//										'close' : Ext.bind(this.onPortletClose,
//												this)
//									}
//								}, {
//									id : 'portlet-rz',
//									title : '我的日志',
//									tools : this.getTools(),
//									items : Ext
//											.create('mvc.view.portal.GridPortletRZ'),
//									listeners : {
//										'close' : Ext.bind(this.onPortletClose,
//												this)
//									}
//								}]
//					}, {
//						id : 'col-2',
//						items : [{
//									id : 'portlet-2',
//									title : '近期项目回款',
//									tools : this.getTools(),
//									items : Ext
//											.create('mvc.view.portal.GridPortlet2'),
//									listeners : {
//										'close' : Ext.bind(this.onPortletClose,
//												this)
//									}
//								},{
//									id : 'portlet-5',
//									title : '项目交付',
//									tools : this.getTools(),
//									items : Ext
//											.create('mvc.view.portal.GridPortlet4'),
//									listeners : {
//										'close' : Ext.bind(this.onPortletClose,
//												this)
//									}
//								}, {
//									id : 'portlet-db',
//									title : '我的待办',
//									tools : this.getTools(),
//									items : Ext
//											.create('mvc.view.portal.GridPortletDB'),
//									listeners : {
//										'close' : Ext.bind(this.onPortletClose,
//												this)
//									}
//								}]
//					}, {
//						id : 'col-3',
//						items : [{
//							id : 'portlet-3',
//							title : '回款分析',
//							tools : this.getTools(),
//							items : Ext
//									.create('mvc.view.portal.PieChartPortlet'),
//							listeners : {
//								'close' : Ext.bind(this.onPortletClose,
//										this)
//							}
//						}, {
//							id : 'portlet-4',
//							title : '项目预算预警(万元)',
//							tools : this.getTools(),
//							items : Ext
//									.create('mvc.view.portal.GaugeChartPortlet2'),
//							listeners : {
//								'close' : Ext.bind(this.onPortletClose,
//										this)
//							}
//						}, {
//							id : 'portlet-jh',
//							title : '我的计划',
//							tools : this.getTools(),
//							items : Ext
//									.create('mvc.view.portal.GridPortletJH'),
//							listeners : {
//								'close' : Ext.bind(this.onPortletClose,
//										this)
//							}
//						}]
//					}]
//				}]
			}],
			plugins : new Ext.create('mvc.tools.MyTabCloseMenu', {
						closeTabText : '关闭面板',
						closeOthersTabsText : '关闭其他',
						closeAllTabsText : '关闭所有'
					})
		});
		this.callParent(arguments);
	},
	
	listeners:{
        'tabchange':function(tab,panel){
        	if (panel.menuType){
				Ext.Array.each(sys_menus,function(line){
					var cmp = Ext.getCmp("menu_"+line); //过滤没有模块没有任何权限的情况
					if (cmp){
						if(line != panel.menuType){
							Ext.getCmp("menu_"+line).hide();
						}else{
							Ext.getCmp("menu_"+line).show();
						}
					}
				});
        	}
        }
    },

	onPortletClose : function(portlet) {
		this.showMsg('"' + portlet.title + '" was removed');
	},

	showMsg : function(msg) {
		var el = Ext.get('app-msg'), msgId = Ext.id();

		this.msgId = msgId;
		el.update(msg).show();

		Ext.defer(this.clearMsg, 3000, this, [msgId]);
	},

	clearMsg : function(msgId) {
		if (msgId === this.msgId) {
			Ext.get('app-msg').hide();
		}
	}
});