Ext.define('mvc.tools.ComboxPaging', {
			extend : 'Ext.form.ComboBox',
			fieldLabel: '每页显示',
			width: 115,
    		labelWidth: 60,
    		typeAhead: true,
            lazyRender: true,
            value:20,
			store: [10, 20, 50],
			myList:null,
			
			initComponent : function() {
				this.callParent(arguments);
			},
			
			listeners: {
				select: function(combo, records, opt) {
					var paging = this.myList.down('pagingtoolbar');
					paging.store.pageSize = Number(this.getValue());
					paging.doRefresh();
				}
			}
		});
