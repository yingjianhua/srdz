Ext.define('mvc.view.Viewport', {
			extend : 'Ext.container.Viewport',
			layout : 'border',
			initComponent : function() {
				this.padding = '0 0 0 0';
				this.items = [Ext.create('mvc.view.Header'),
						Ext.create('mvc.view.West'),
						Ext.create('mvc.view.TabPanel')
						//Ext.create('mvc.view.East')
						];
						//Ext.create('mvc.view.South')
				this.callParent(arguments);
			},
			listeners: {
	            'afterrender': function() {  
	            	this.down('#nav_sys').handler();
	            }  
	        }
		});