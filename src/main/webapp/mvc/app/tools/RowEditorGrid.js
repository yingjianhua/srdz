Ext.defind('mvc.tools.RowEditorGrid', {
	autoDestroy : true,
	tbar : [ {
		text : 'Add Employee',
		iconCls : 'employee-add',
		handler : function() {
			rowEditing.cancelEdit();

			// Create a model instance
			var r = Ext.create('Employee', {
				name : 'New Guy',
				email : 'new@sencha-test.com',
				start : Ext.Date.clearTime(new Date()),
				salary : 50000,
				active : true
			});

			store.insert(0, r);
			rowEditing.startEdit(0, 0);
		}
	}, {
		itemId : 'removeRow',
		text : '删除',
		iconCls : 'employee-remove',
		handler : function() {
			var sm = grid.getSelectionModel();
			rowEditing.cancelEdit();
			store.remove(sm.getSelection());
			if (store.getCount() > 0) {
				sm.select(0);
			}
		},
		disabled : true
	} ],
	plugins : [ Ext.create('Ext.grid.plugin.RowEditing', {
		clicksToMoveEditor : 1,
		autoCancel : false
	}) ],
	listeners : {
		'selectionchange' : function(view, records) {
			grid.down('#removeRow').setDisabled(!records.length);
		}
	}
});