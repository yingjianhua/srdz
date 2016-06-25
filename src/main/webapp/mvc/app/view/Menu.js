Ext.define('mvc.view.Menu', {
			extend : 'Ext.tree.Panel',
			alias : 'widget.mainmenu',
			hidden : true,
			requires : ['mvc.store.Menus'],
			expanded : true,
			beanType : "sys",
			iconCls : 'Module-mgr-ico',
			initComponent : function() {
				this.enableDD = false;
				this.rootVisible = false;
				this.containerScroll = true;
				this.collapsible = true;
				this.autoScroll = false;
				var st = Ext.create('mvc.store.Menus');
				this.store = st;
				this.callParent(arguments);
			},
			
			onLoadFirst : function(){
				if (this.isLoadFirst)
					return;
				this.isLoadFirst = true;
				this.store.getProxy().extraParams={"type":this.beanType};
				this.store.load();
				this.expand();
			}
		
		})
