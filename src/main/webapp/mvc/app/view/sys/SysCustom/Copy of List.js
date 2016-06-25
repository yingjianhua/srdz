Ext.define('mvc.view.sys.SysCustom.List', {
	extend : 'Ext.grid.Panel',
	oldId : 'btn_SysCustom',
	lock : true,
	disableSelection : false,
	loadMask : true,
	multiSelect : true,
	roles : '',
	selModel : {
		selType : 'checkboxmodel'
	},
	initComponent : function() {
		var lineActs = [];
		if (this.roles.indexOf('upd') != -1)
			lineActs.push({
				iconCls : 'upd-icon-table',
				tooltip : '修改',
				scope : this,
				handler : this.onUpdRow
			});
		if (this.roles.indexOf('del') != -1)
			lineActs.push({
				iconCls : 'del-icon-table',
				tooltip : '删除',
				scope : this,
				handler : this.onDelRow
			});
		var mainActs = [];
		if (this.roles.indexOf('ins') != -1)
			mainActs.push({
				text : '新增',
				iconCls : 'ins-icon',
				itemId : this.oldId + 'ins',
				scope : this,
				handler : this.onIns
			});
		if (this.roles.indexOf('upd') != -1)
			mainActs.push({
				text : '修改',
				iconCls : 'upd-icon',
				itemId : this.oldId + 'upd',
				scope : this,
				handler : this.onUpd,
				disabled : this.lock
			});
		if (this.roles.indexOf('edit') != -1)
			mainActs.push({
				text : '编辑',
				iconCls : 'edit-icon',
				itemId : this.oldId + 'edit',
				scope : this,
				handler : this.onEdit,
				disabled : this.lock
			});
		if (this.roles.indexOf('doEnabled') != -1)
			mainActs.push({
				text : '启用',
				iconCls : 'doEnabled-icon',
				itemId : this.oldId + 'doEnabled',
				scope : this,
				handler : this.onDoEnabled,
				disabled : this.lock
			});
		if (this.roles.indexOf('unEnabled') != -1)
			mainActs.push({
				text : '停用',
				iconCls : 'unEnabled-icon',
				itemId : this.oldId + 'unEnabled',
				scope : this,
				handler : this.onUnEnabled,
				disabled : this.lock
			});
		this.columns = [ {
			text : '代码',
			width : 100,
			dataIndex : 'bean.code',
			sortable : true
		}, {
			text : '名称',
			width : 100,
			dataIndex : 'bean.name',
			sortable : true
		}, {
			text : '简称',
			width : 100,
			dataIndex : 'bean.shortName',
			sortable : true
		}, {
			text : '性质',
			width : 100,
			dataIndex : 'bean.comPersonFlag',
			sortable : true,
			renderer : mvc.Tools.optRenderer('sys', 'Sys', 'OComPersonFlag')
		}, {
			text : '启用标志',
			width : 100,
			dataIndex : 'bean.enabled',
			sortable : true,
			renderer : mvc.Tools.optRenderer('sys', 'Sys', 'OEnabled')
		}, {
			text : '管理机构',
			width : 100,
			dataIndex : 'bean.mngOrg',
			sortable : true,
			renderer : mvc.Tools.beanRenderer()
		}, {
			text : '管理部门',
			width : 100,
			dataIndex : 'bean.mngDept',
			sortable : true,
			renderer : mvc.Tools.beanRenderer()
		}, {
			text : '业务代表',
			width : 100,
			dataIndex : 'bean.businessMember',
			sortable : true,
			renderer : mvc.Tools.beanRenderer()
		}, {
			text : '电话1',
			width : 100,
			dataIndex : 'one.tel1',
			sortable : true
		}, {
			text : '电话2',
			width : 100,
			dataIndex : 'one.tel2',
			sortable : true
		}, {
			text : '传真',
			width : 100,
			dataIndex : 'one.fax',
			sortable : true
		}, {
			text : '网址',
			width : 100,
			dataIndex : 'one.website',
			sortable : true
		}, {
			text : '地址',
			width : 100,
			dataIndex : 'one.addr',
			sortable : true
		}, {
			text : '邮编',
			width : 100,
			dataIndex : 'one.zipCode',
			sortable : true
		}, {
			text : '备注',
			width : 100,
			dataIndex : 'one.rem',
			sortable : true
		}, {
			text : '更新员',
			width : 100,
			dataIndex : 'one.updatedBy',
			sortable : true,
			renderer : mvc.Tools.beanRenderer()
		}, {
			text : '更新时间',
			width : 140,
			dataIndex : 'one.updatedDateTime',
			sortable : true,
			renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')
		}, {
			text : '建档员',
			width : 100,
			dataIndex : 'one.createdBy',
			sortable : true,
			renderer : mvc.Tools.beanRenderer()
		}, {
			text : '建档时间',
			width : 140,
			dataIndex : 'one.createdDateTime',
			sortable : true,
			renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')
		}, {
			xtype : 'actioncolumn',
			width : 80,
			sortable : false,
			menuDisabled : true,
			header : '操作',
			items : lineActs
		} ];
		this.tbar = mainActs;
		this.store = Ext.create('mvc.store.sys.SysCustom');
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.dockedItems = [ {
			dock : 'top',
			xtype : 'toolbar',
			items : [ {
				xtype : 'label',
				text : '代码：'
			}, {
				xtype : 'textfield',
				name : 'code'
			}, '', {
				xtype : 'label',
				text : '名称：'
			}, {
				xtype : 'textfield',
				name : 'name'
			}, '', {
				xtype : 'label',
				text : '启用标志：'
			}, {
				xtype : 'combo',
				name : 'enabled',
				mode : 'local',
				valueField : 'value',
				triggerAction : 'all',
				forceSelection : true,
				typeAhead : true,
				eitable : false,
				emptyText : form_empty_text,
				store : Ext.create('mvc.combo.sys.SysOEnabled')
			}, '', {
				xtype : 'button',
				text : '撤销',
				scope : this,
				iconCls : 'win-close-icon',
				handler : this.onSearchCancel
			}, {
				xtype : 'splitbutton',
				text : '搜索',
				scope : this,
				iconCls : 'win-ok-icon',
				handler : this.onSearch,
				menu : [ {
					text : '高级搜索',
					iconCls : 'win-ok-icon',
					scope : this,
					handler : this.onSearchAdv
				} ]
			} ]
		}, {
			xtype : 'pagingtoolbar',
			store : this.store,
			dock : 'bottom',
			displayInfo : true,
			displayMsg : '显示 {0} - {1} 条，共计 {2} 条',
			emptyMsg : '没有数据',
			items : [ {
				xtype : Ext.create('mvc.tools.ComboxPaging', {
					myList : this
				})
			} ]
		} ];
		this.callParent(arguments);
	},
	listeners : {
		selectionchange : function(selModel, selected) {
			if (this.roles.indexOf('upd') != -1)
				this.down('#' + this.oldId + 'upd').setDisabled(
						selected.length === 0);
			if (this.roles.indexOf('edit') != -1)
				this.down('#' + this.oldId + 'edit').setDisabled(
						selected.length === 0);
			if (this.roles.indexOf('doEnabled') != -1)
				this.down('#' + this.oldId + 'doEnabled').setDisabled(
						selected.length === 0);
			if (this.roles.indexOf('unEnabled') != -1)
				this.down('#' + this.oldId + 'unEnabled').setDisabled(
						selected.length === 0);
		}
	},
	onSaveRecord : function(form, data) {
		this.store.insert(0, data);
		this.getView().select(0);
		Ext.example.msg(msg_title, msg_text);
	},
	onIns : function() {
		var win = Ext.create('mvc.view.sys.SysCustom.Win', {
			title : this.title + '>新增'
		});
		win.on('create', this.onSaveRecord, this);
		win.show();
	},
	onUpdateRecord : function(form, data) {
		var selection = this.getView().getSelectionModel().getSelection()[0];
		var bean = Ext.create('mvc.model.sys.SysCustom', data);
		Ext.apply(selection.data, bean.data);
		selection.commit();
		this.getView().select(selection);
		Ext.example.msg(msg_title, msg_text);
	},
	onUpd : function() {
		var selection = this.getView().getSelectionModel().getSelection()[0];
		this.onUpdWin(selection);
	},
	onUpdRow : function(grid, rowIndex) {
		var selection = this.getStore().getAt(rowIndex);
		this.getView().deselect(
				this.getView().getSelectionModel().getSelection());
		this.getView().select(selection);
		this.onUpdWin(selection);
	},
	onUpdWin : function(selection) {
		if (selection) {
			mvc.model.sys.SysCustom.load(selection.get('bean.pkey'), {
				scope : this,
				failure : function(response, operation) {
					Ext.example.msg(msg_title, msg_ajax);
				},
				success : function(response, operation) {
					Ext.apply(selection.data, response.data);
					var win = Ext.create('mvc.view.sys.SysCustom.Win', {
						title : this.title + '>修改',
						insFlag : false
					});
					win.on('create', this.onUpdateRecord, this);
					win.show();
					win.setActiveRecord(selection);
				}
			});
		}
	},
	onEdit : function() {
		var selection = this.getView().getSelectionModel().getSelection()[0];
		if (selection) {
			var win = Ext.create('mvc.view.sys.SysCustom.WinEdit', {
				title : this.title + '[' + selection.get('bean.name') + ']>编辑',
				record : selection
			});
			win.show();
			win.loadData();
		}
	},
	onSearchCancel : function() {
		mvc.Tools.searchClear(this.down('toolbar'));
		this.store.clearFilter();
	},
	onSearch : function() {
		var array = mvc.Tools.searchValues(this.down('toolbar'));
		if (array.length == 0) {
			this.store.clearFilter();
			return;
		}
		this.store.clearFilter(true);
		this.store.filter(array);
	},
	onSearchAdv : function() {
		var win = Ext.create('mvc.view.sys.SysCustom.WinSearch', {
			title : this.title + '>高级搜索',
			listStore : this.store
		});
		win.show();
	},
	onDoEnabled : function() {
		var selection = this.getView().getSelectionModel().getSelection();
		if (selection) {
			var me = this;
			Ext.MessageBox.confirm(msg_confirm_title, msg_confirm_msg,
					function(btn) {
						if (btn != 'yes')
							return;
						var arr = new Array();
						for (var i = 0; i < selection.length; i++) {
							arr.push(selection[i].get('bean.pkey'));
						}
						Ext.Ajax.request({
							url : base_path + '/sys_SysCustom_doEnabled',
							params : {
								pkeys : arr.toString()
							},
							success : function(response, options) {
								var result = Ext.decode(response.responseText);
								if (result.success) {
									me.getStore().reload();
									Ext.example.msg(msg_title, msg_del);
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
					});
		}
	},
	onUnEnabled : function() {
		var selection = this.getView().getSelectionModel().getSelection();
		if (selection) {
			var me = this;
			Ext.MessageBox.confirm(msg_confirm_title, msg_confirm_msg,
					function(btn) {
						if (btn != 'yes')
							return;
						var arr = new Array();
						for (var i = 0; i < selection.length; i++) {
							arr.push(selection[i].get('bean.pkey'));
						}
						Ext.Ajax.request({
							url : base_path + '/sys_SysCustom_unEnabled',
							params : {
								pkeys : arr.toString()
							},
							success : function(response, options) {
								var result = Ext.decode(response.responseText);
								if (result.success) {
									me.getStore().reload();
									Ext.example.msg(msg_title, msg_del);
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
					});
		}
	},
});