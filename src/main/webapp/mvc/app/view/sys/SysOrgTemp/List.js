Ext.define('mvc.view.sys.SysOrgTemp.List', {
	extend : 'mvc.tools.RowexpanderGrid',
	oldId : 'btn_SysOrg',
	lock : true,
	disableSelection : false,
	loadMask : true,
	multiSelect : true,
	roles : '',
	selModel : {
		selType : 'checkboxmodel'
	},
	initComponent : function() {
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
		mainActs.push({
					text : '测试',
					iconCls : 'unEnabled-icon',
					itemId : this.oldId + 'testEnabled',
					scope : this,
					handler : this.getFields
				});
		this.columns = [{
					text : '机构代码',
					width : 100,
					dataIndex : 'bean.codeThis',
					sortable : true
				}, {
					text : '机构名称',
					width : 100,
					dataIndex : 'bean.name',
					sortable : true
				}, {
					text : '机构简称',
					width : 100,
					dataIndex : 'bean.shortName',
					sortable : true
				}, {
					text : '启用标志',
					width : 100,
					dataIndex : 'bean.enabled',
					sortable : true,
					renderer : mvc.Tools.optRenderer('sys', 'Sys', 'OEnabled')
				}, {
					text : '上级机构',
					width : 100,
					dataIndex : 'bean.orgUp',
					sortable : true,
					renderer : mvc.Tools.beanRenderer(),
					expandCol : true,
					hidden : true
				}, {
					text : '工作日期',
					width : 100,
					dataIndex : 'bean.workDate',
					sortable : true,
					renderer : Ext.util.Format.dateRenderer('Y-m-d')
				}, {
					text : '机构状态',
					width : 100,
					dataIndex : 'bean.state',
					sortable : true,
					renderer : mvc.Tools.optRenderer('sys', 'Sys', 'OOrgState')
				}, {
					text : '科目模板',
					width : 100,
					dataIndex : 'bean.templat',
					sortable : true,
					renderer : mvc.Tools.beanRenderer(),
					expandCol : true,
					hidden : true
				}, {
					text : '存货计价方式',
					width : 100,
					dataIndex : 'bean.valuationMethods',
					sortable : true,
					renderer : mvc.Tools.optRenderer('sys', 'Sys',
							'OValuationMethods'),
					expandCol : true,
					hidden : true
				}, {
					text : '是否有国际贸易',
					width : 100,
					dataIndex : 'bean.internationTrade',
					sortable : true,
					renderer : mvc.Tools.optRenderer('sys', 'Sys', 'OYn'),
					expandCol : true,
					hidden : true
				}, {
					text : '币种',
					width : 100,
					dataIndex : 'bean.currency',
					sortable : true,
					renderer : mvc.Tools.optRenderer('sys', 'Sys', 'OCurrency'),
					expandCol : true,
					hidden : true
				}, {
					text : '电话1',
					width : 100,
					dataIndex : 'one.tel1',
					sortable : true
				}, {
					text : '电话2',
					width : 100,
					dataIndex : 'one.tel2',
					sortable : true,
					expandCol : true,
					hidden : true
				}, {
					text : '传真',
					width : 100,
					dataIndex : 'one.fax',
					sortable : true
				}, {
					text : '网址',
					width : 100,
					dataIndex : 'one.website',
					sortable : true,
					expandCol : true,
					hidden : true
				}, {
					text : '地址',
					width : 100,
					dataIndex : 'one.addr',
					sortable : true,
					expandCol : true,
					hidden : true
				}, {
					text : '邮编',
					width : 100,
					dataIndex : 'one.zipCode',
					sortable : true,
					expandCol : true,
					hidden : true
				}, {
					text : '备注',
					width : 100,
					dataIndex : 'one.rem',
					sortable : true,
					expandCol : true,
					hidden : true
				}, {
					text : '更新员',
					width : 100,
					dataIndex : 'one.updatedBy',
					sortable : true,
					renderer : mvc.Tools.beanRenderer(),
					expandCol : true,
					hidden : true
				}, {
					text : '更新时间',
					width : 140,
					dataIndex : 'one.updatedDateTime',
					sortable : true,
					renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s'),
					expandCol : true,
					hidden : true
				}, {
					text : '建档员',
					width : 100,
					dataIndex : 'one.createdBy',
					sortable : true,
					renderer : mvc.Tools.beanRenderer(),
					expandCol : true,
					hidden : true
				}, {
					text : '建档时间',
					width : 140,
					dataIndex : 'one.createdDateTime',
					sortable : true,
					renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s'),
					expandCol : true,
					hidden : true
				}];
		var result = "";
		Ext.Ajax.request({
			async : false, //加上同步限制后，单元格之间切换会中断	
				url : base_path+ '/sys_SysOrg_getFields',
				success : function(response, options) {
					result = response.responseText;
				}
			}
		);
		var fields = [];
		Ext.each(result.match(/\{(.*?)\}/gi),function(data){
			fields.push(Ext.JSON.decode(data,true));
		})
		Ext.define('model',{
			extend : 'Ext.data.Model',
			idProperty : 'bean.pkey',
			proxy : {
				type : 'ajax',
				url : base_path+'/sys_SysOrg_load'
			},
			fields :fields
		});
		
		var store = new Ext.data.Store({
			requires : 'model',
			model :'model',
			pageSize : 20,
			remoteSort : false,
			autoLoad : false,
			proxy : {
				type : 'ajax',
				url : base_path+'/sys_SysOrg_list',
				reader : {
					type : 'json',
					root : 'items',
					totalProperty : 'total'
				},
				simpleSortMode : true
			}
		});
		this.tbar = mainActs;
		this.store = store;
		// this.store=Ext.create('mvc.store.sys.SysOrg');
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.dockedItems = [{
					dock : 'top',
					xtype : 'toolbar',
					items : [{
								xtype : 'label',
								text : '机构代码：'
							}, {
								xtype : 'textfield',
								name : 'codeThis'
							}, '', {
								xtype : 'label',
								text : '机构名称：'
							}, {
								xtype : 'textfield',
								name : 'name'
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
								menu : [{
											text : '高级搜索',
											iconCls : 'win-ok-icon',
											scope : this,
											handler : this.onSearchAdv
										}]
							}]
				}, {
					xtype : 'pagingtoolbar',
					store : this.store,
					dock : 'bottom',
					displayInfo : true,
					displayMsg : '显示 {0} - {1} 条，共计 {2} 条',
					emptyMsg : '没有数据',
					items : [{
								xtype : Ext.create('mvc.tools.ComboxPaging', {
											myList : this
										})
							}]
				}];
		this.callParent(arguments);
	},
	listeners : {
		selectionchange : function(selModel, selected) {
			if (this.roles.indexOf('upd') != -1)
				this.down('#' + this.oldId + 'upd')
						.setDisabled(selected.length === 0);
			if (this.roles.indexOf('doEnabled') != -1)
				this.down('#' + this.oldId + 'doEnabled')
						.setDisabled(selected.length === 0);
			if (this.roles.indexOf('unEnabled') != -1)
				this.down('#' + this.oldId + 'unEnabled')
						.setDisabled(selected.length === 0);
		}
	},
	getFields : function() {
		var col = "[rn,sm,";
		var stor_str = "[";
		var result = "";
		var json = {
			'coders' : [{
						'pro' : 'ADM',
						'id' : '1',
						'name' : 'McLaughlin',
						'descn' : 'brett@newInstance.com',
						'test' : 'test'
					}, {
						'pro' : 'RMS',
						'id' : '2',
						'name' : 'Hunter',
						'descn' : 'jason@servlets.com'
					}, {
						'pro' : 'fdks',
						'id' : '3',
						'name' : 'Harold',
						'descn' : 'elharo@macfaq.com'
					}, {
						'pro' : 'fkas',
						'id' : '4',
						'name' : 'Harolds',
						'descn' : 'elhaross@macfaq.com'
					}, {
						'pro' : 'qfks',
						'id' : '5',
						'name' : 'Karolds',
						'descn' : 'lhaross@macfaq.com'
					}, {
						'pro' : 'wfks',
						'id' : '6',
						'name' : 'Yarolds',
						'descn' : 'elaross@macfaq.com'
					}, {
						'pro' : 'efks',
						'id' : '7',
						'name' : 'Jarolds',
						'descn' : 'elharss@macfaq.com'
					}, {
						'pro' : 'rfks',
						'id' : '8',
						'name' : 'Larolds',
						'descn' : 'elhass@macfaq.com'
					}, {
						'pro' : 'tfks',
						'id' : '9',
						'name' : 'Haroldws',
						'descn' : 'elhaross@macfaq.com'
					}, {
						'pro' : 'yfks',
						'id' : '10',
						'name' : 'WHarolds',
						'descn' : 'elhaross@macfaq.com'
					}]
		};
		// 遍历外层对象
		for (data in json) {
			// 第二层循环,遍历数组
			for (dt1 in json[data]) {
				// 第三层循环，遍历hash数据
				for (dt2 in json[data][dt1]) {
					// 将header和dataIndex的值一hash格式传到数组中，在声明columnModel时用
					// var temp = {};
					col += "{header:" + "'" + dt2 + "',";
					col += "dataIndex:" + "'" + dt2 + "',";
					// col += sm+"'"+dt2+"',";
					// col +=new Ext.grid.RowNumberer()+"'"+dt2+"',";
					col += "editor:{ xtype:'textfield',allowBlank: false}},";
					stor_str += "{name:" + "'" + dt2 + "'},";
				}
				col = col.substring(0, col.lastIndexOf(","));
				stor_str = stor_str.substring(0, stor_str.lastIndexOf(","));
				col += "]";
				stor_str += "]";
				result = col + "$" + stor_str;
				alert(result);

				var index = result.indexOf("$");
				var columnModel = result.substring(0, index);
				var storeStr = result.substring(index + 1);
				// var sm = new Ext.grid.CheckboxSelectionModel();
				var cm = new Ext.grid.ColumnModel(
						// new Ext.grid.RowNumberer(),
						// sm,
						eval(columnModel)

				);
				cm.defaultSortable = true;
				// ArrayReader
				var store = new Ext.data.Store({
							// remoteSort: true,
							proxy : {
								type : 'ajax',
								url : base_path + '/sys_SysOrg_list',
								reader : {
									type : 'json',
									root : 'items',
									totalProperty : 'total'
								},
								simpleSortMode : true
							}
						});
				return result;
			}
		}
	},
	onSaveRecord : function(form, data) {
		this.store.insert(0, data);
		this.getView().select(0);
		Ext.example.msg(msg_title, msg_text);
	},
	onIns : function() {
		var win = Ext.create('mvc.view.sys.SysOrg.Win', {
					title : this.title + '>新增'
				});
		win.on('create', this.onSaveRecord, this);
		win.show();
	},
	onUpdateRecord : function(form, data) {
		var selection = this.getView().getSelectionModel().getSelection()[0];
		var bean = Ext.create('mvc.model.sys.SysOrg', data);
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
		this.getView().deselect(this.getView().getSelectionModel()
				.getSelection());
		this.getView().select(selection);
		this.onUpdWin(selection);
	},
	onUpdWin : function(selection) {
		if (selection) {
			mvc.model.sys.SysOrg.load(selection.get('bean.pkey'), {
						scope : this,
						failure : function(response, operation) {
							Ext.example.msg(msg_title, msg_ajax);
						},
						success : function(response, operation) {
							Ext.apply(selection.data, response.data);
							var win = Ext.create('mvc.view.sys.SysOrg.Win', {
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
	onDoEnabled : function() {
		var selection = this.getView().getSelectionModel().getSelection();
		if (selection) {
			var me = this;
			Ext.MessageBox.confirm(msg_confirm_title, '您确认要启用所选的记录吗?',
					function(btn) {
						if (btn != 'yes')
							return;
						var arr = new Array();
						for (var i = 0; i < selection.length; i++) {
							arr.push(selection[i].get('bean.pkey'));
						}
						Ext.Ajax.request({
									url : base_path + '/sys_SysOrg_doEnabled',
									params : {
										pkeys : arr.toString()
									},
									success : function(response, options) {
										var result = Ext
												.decode(response.responseText);
										if (result.success) {
											me.getStore().reload();
											Ext.example.msg(msg_title,
													msg_submit);
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
			Ext.MessageBox.confirm(msg_confirm_title, '您确认要停用所选的记录吗?',
					function(btn) {
						if (btn != 'yes')
							return;
						var arr = new Array();
						for (var i = 0; i < selection.length; i++) {
							arr.push(selection[i].get('bean.pkey'));
						}
						Ext.Ajax.request({
									url : base_path + '/sys_SysOrg_unEnabled',
									params : {
										pkeys : arr.toString()
									},
									success : function(response, options) {
										var result = Ext
												.decode(response.responseText);
										if (result.success) {
											me.getStore().reload();
											Ext.example.msg(msg_title,
													msg_submit);
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
	onSearchCancel : function() {
		this.getSelectionModel().deselectAll();
		mvc.Tools.searchClear(this.down('toolbar'));
		this.store.clearFilter();
	},
	onSearch : function() {
		this.getSelectionModel().deselectAll();
		var array = mvc.Tools.searchValues(this.down('toolbar'));
		if (array.length == 0) {
			this.store.clearFilter();
			return;
		}
		this.store.clearFilter(true);
		this.store.filter(array);
	},
	onSearchAdv : function() {
		var win = Ext.create('mvc.view.sys.SysOrg.WinSearch', {
					title : this.title + '>高级搜索',
					listStore : this.store
				});
		win.show();
	}
});