Ext.define('mvc.view.West', {
			extend : 'Ext.panel.Panel',
			id : 'myWest',
			title : '导航',
			region : "west",
			iconCls : 'nav-ico',
			width : 180,
			minWidth : 100,
			maxWidth : 300,
			split : true,
			collapsible : true,
			hidden : true,
			layout : 'accordion',
			defaults : {
				layout : 'fit',
				anchor : '100%'
			},
//			reload : function(type){
//				Ext.getCmp("menu_"+type).show();
////				Ext.getCmp("menu_"+type).expand();
//			},
			initComponent : function() {
				var types = '';
				Ext.Ajax.request({
					async : false,
					url : base_path+'/sys_SysMenu_initAct',
					success : function (response, options) {
						types = response.responseText;
					}
				});
				var menus = [];
				menus.push(Ext.create('mvc.view.Menu',{title:'首页'}));
				var strs = types.split(',');
				for(var i=0;i< strs.length;i++){
					menus.push(Ext.create('mvc.view.Menu',{title:sys_menuOBJ[strs[i]], beanType:strs[i], iconCls:'menu-lv1-ico-1', id:'menu_'+strs[i]}));
				}
				this.items = menus;
				this.callParent(arguments);
			}
		}
	)