Ext.define('mvc.view.sys.SysRole.List',{
	extend : 'Ext.grid.Panel',
	oldId : 'btn_SysRole',
	lock : true,
	disableSelection : false,
	loadMask : true,
	multiSelect : true,
	roles : '',
	selModel : {selType : 'checkboxmodel'},
	initComponent : function(){
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
				itemId : this.oldId+'ins',
				scope : this,
				handler : this.onIns
			});
		if (this.roles.indexOf('upd') != -1)
			mainActs.push({
				text : '修改',
				iconCls : 'upd-icon',
				itemId : this.oldId+'upd',
				scope : this,
				handler : this.onUpd,
				disabled : this.lock
			});
		var lineActs = [];
		if (this.roles.indexOf('updCtrl') != -1)
			mainActs.push({
				text : '权限设置',
				iconCls : 'upd-icon',
				itemId : this.oldId+'updCtrl',
				scope : this,
				handler : this.onUpdCtrl,
				disabled : this.lock
			});
		if (this.roles.indexOf('del') != -1)
			mainActs.push({
				text : '删除',
				disabled : this.lock,
				itemId : this.oldId+'del',
				iconCls : 'del-icon',
				scope : this,
				handler : this.onDel
			});
		this.columns = [ 
			{text : '代码', width : 100, dataIndex : 'bean.code', sortable : true},
			{text : '名称', width : 100, dataIndex : 'bean.name', sortable : true},
			{text : '启用标志', width : 100, dataIndex : 'bean.enabled', sortable : true, renderer : mvc.Tools.optRenderer('sys','Sys','OEnabled')},
			{text : '可视范围', width : 100, dataIndex : 'bean.tbObj', sortable : true, renderer : mvc.Tools.beanRenderer()},
			{text : '更新员', width : 100, dataIndex : 'bean.updatedBy', sortable : true, renderer : mvc.Tools.beanRenderer()},
			{text : '更新时间', width : 140, dataIndex : 'bean.updatedDateTime', sortable : true, renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')},
			{
				xtype : 'actioncolumn',
				width : 80,
				sortable : false,
				menuDisabled : true,
				header : '操作',
				items : lineActs
			}
		];
		this.tbar=mainActs;
		this.store=Ext.create('mvc.store.sys.SysRole');
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.dockedItems=[{
			dock : 'top', 
			xtype : 'toolbar',
			items : [{
				xtype : 'label',
				text : '代码：'
			},{
				xtype : 'textfield',
				name : 'code'
			},' ',{
				xtype : 'label',
				text : '名称：'
			},{
				xtype : 'textfield',
				name : 'name'
			},' ',{
				xtype : 'button',
				text : '撤销',
				scope : this,
				iconCls : 'win-close-icon',
				handler : this.onSearchCancel
			},{
				xtype : 'button',
				text : '搜索',
				scope : this,
				iconCls : 'win-ok-icon',
				handler : this.onSearch
			}]
		},{
			xtype : 'pagingtoolbar',
			store : this.store,
			dock : 'bottom',
			displayInfo : true,
			displayMsg : '显示 {0} - {1} 条，共计 {2} 条',
			emptyMsg : '没有数据',
			items : [{xtype : Ext.create('mvc.tools.ComboxPaging',{myList : this})}]
		}];
		this.callParent(arguments);
	},
	listeners : {
		selectionchange : function(selModel, selected) {
			if (this.roles.indexOf('upd') != -1)
				this.down('#'+this.oldId+'upd').setDisabled(selected.length === 0);
			if (this.roles.indexOf('updCtrl') != -1)
				this.down('#'+this.oldId+'updCtrl').setDisabled(selected.length === 0);
			if (this.roles.indexOf('del') != -1)
				this.down('#'+this.oldId+'del').setDisabled(selected.length === 0);
		}
	},
	
	onSaveRecord : function(form, data){
		this.store.insert(0,data);
		this.getView().select(0);
		Ext.example.msg(msg_title, msg_text);
	},
	onIns : function(){
		var win = Ext.create('mvc.view.sys.SysRole.Win',{
			title : this.title+'>新增'
		});
		win.on('create',this.onSaveRecord,this);
		win.show();
	},
	onUpdateRecord : function(form, data){
		var selection = this.getView().getSelectionModel().getSelection()[0];
		var bean = Ext.create('mvc.model.sys.SysRole', data);
		Ext.apply(selection.data,bean.data);
		selection.commit();
		this.getView().select(selection);
		Ext.example.msg(msg_title, msg_text);
	},
	onUpd : function(){
		var selection = this.getView().getSelectionModel().getSelection()[0];
		this.onUpdWin(selection);
	},
	onUpdRow : function(grid, rowIndex){
		var selection = this.getStore().getAt(rowIndex);
		this.getView().deselect(this.getView().getSelectionModel().getSelection());
		this.getView().select(selection);
		this.onUpdWin(selection);
	},
	onUpdWin : function(selection){
		if (selection){
			mvc.model.sys.SysRole.load(selection.get('bean.pkey'), {
				scope : this,
				failure : function(response, operation) {
					Ext.example.msg(msg_title,msg_ajax);
				},
				success : function(response, operation) {
					Ext.apply(selection.data,response.data);
					var win = Ext.create('mvc.view.sys.SysRole.Win',{
						title : this.title+'>修改',
						insFlag : false
					});
					win.on('create',this.onUpdateRecord,this);
					win.show();
					win.setActiveRecord(selection);
				}
			});
		}
	},
	onDel : function(){
		var selection = this.getView().getSelectionModel().getSelection();
		if (selection){
			var me = this;
			Ext.MessageBox.confirm(msg_confirm_title, msg_confirm_msg, 
				function(btn) {
					if (btn != 'yes')
						return;
					var arr=new Array();
					for(var i = 0; i < selection.length; i++){
						arr.push(selection[i].get('bean.pkey'));
					}
					Ext.Ajax.request({
						url : base_path+'/sys_SysRole_delMulti',
						params : { pkeys : arr.toString() },
						success : function (response, options) {
							var result = Ext.decode(response.responseText);
							if (result.success){
								me.getStore().remove(selection);
								Ext.example.msg(msg_title, msg_del);
							}else{
								Ext.MessageBox.show({
									title : msg_title, 
									msg : result.msg,
									buttons : Ext.MessageBox.OK,
									icon : Ext.MessageBox.ERROR
								});
							}
						}
					});
				}
			);
		}
	},
	onDelRow : function(grid, rowIndex){
		var me = this;
		Ext.MessageBox.confirm(msg_confirm_title, msg_confirm_msg,
			function(btn) {
				if (btn != 'yes')
					return;
				Ext.Ajax.request({
					url : base_path+'/sys_SysRole_del?pkey='+me.getStore().getAt(rowIndex).get('bean.pkey'),
					success : function (response, options) {
						var result = Ext.decode(response.responseText);
						if (result.success){
							me.getStore().removeAt(rowIndex);
							Ext.example.msg(msg_title, msg_del);
						}else{
							Ext.MessageBox.show({ 
								title : msg_title,
								msg : result.msg,
								buttons : Ext.MessageBox.OK,
								icon : Ext.MessageBox.ERROR
							});
						}
					}
				});
			}
		);
	},
	onSearchCancel : function(){
		this.getSelectionModel().deselectAll();
		mvc.Tools.searchClear(this.down('toolbar'));
		this.store.clearFilter();
	},
	onSearch : function(){
		this.getSelectionModel().deselectAll();
		var array = mvc.Tools.searchValues(this.down('toolbar'));
		if (array.length == 0){
			this.store.clearFilter();
			return;
		}
		this.store.clearFilter(true);
		this.store.filter(array);
	},
	onUpdCtrl : function(grid, rowIndex) {
		var selection = this.getView().getSelectionModel().getSelection()[0];
		var win = Ext.create('mvc.view.sys.SysRole.Authority',{pkey : selection.get('bean.pkey')});
		win.show();;
	}
});
