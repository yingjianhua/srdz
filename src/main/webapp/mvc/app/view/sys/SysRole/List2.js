Ext.define('mvc.view.sys.SysRole.List',{
	extend : 'Ext.grid.Panel',
	oldId : 'btn_sysrole_list_',
	lock : true,
	disableSelection : false,
	loadMask : true,
	multiSelect : true,
	roles : '',
	selModel : {selType : 'checkboxmodel'},
	viewConfig : {
		trackOver : false,
		stripeRows : true
	},
	adminFlag : '02', //00 超级管理员 01管理员 02员工
	initComponent : function(){
		var me = this;
		Ext.Ajax.request({
			url : base_path+ '/sys_SysUser_adminFlag',
			async : false,
			success : function (response, options) {
				var result = Ext.decode(response.responseText);
				if (result.success){
					me.adminFlag = result.adminFlag;
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
		var lineActs = [];
		if (this.roles.indexOf('updCtrl') != -1)
			lineActs.push({
				icon : app_icon_set,
				tooltip : '权限',
				scope : this,
				handler : this.onAthRow
			});
		var mainActs = [];
		if (this.roles.indexOf('ins') != -1 && this.adminFlag=='00')
			mainActs.push({
				text : '新增',
				iconCls : 'ins-icon',
				itemId : this.oldId+'ins',
				scope : this,
				handler : this.onIns
			});
		if (this.roles.indexOf('upd') != -1 && this.adminFlag=='00')
			mainActs.push({
				text : '修改',
				iconCls : 'upd-icon',
				itemId : this.oldId+'upd',
				scope : this,
				handler : this.onUpd,
				disabled : this.lock
			});
		if (this.roles.indexOf('del') != -1 && this.adminFlag=='00')
			mainActs.push({
				text : '删除',
				disabled : this.lock,
				itemId : this.oldId+'del',
				iconCls : 'del-icon',
				scope : this,
				handler : this.onDel
			});
		this.columns = [ 
			{text : '角色名称', width : 120, dataIndex : 'bean.roleName', sortable : true},
			{text : '角色编码', width : 100, dataIndex : 'bean.roleCode', sortable : true},
			{text : '备注', width : 200, dataIndex : 'bean.roleDes', sortable : true},
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
			dock :  'top', 
			xtype : 'toolbar',
			items : [{
				xtype : 'label',
				text : '角色名称：'
			},{
				xtype : 'textfield',
				name : 'roleName'
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
			if (this.roles.indexOf('upd') != -1 && this.adminFlag=='00')
				this.down('#'+this.oldId+'upd').setDisabled(selected.length === 0);
			if (this.roles.indexOf('del') != -1 && this.adminFlag=='00')
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
					win.setActiveRecord(selection);
					win.show();
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
						url : base_path+ '/sys_SysRole_delMulti',
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
	onSearchCancel : function(){
		mvc.Tools.searchClear(this.down('toolbar'));
		this.store.clearFilter();
	},
	onSearch : function(){
		var array = mvc.Tools.searchValues(this.down('toolbar'));
		if (array.length == 0){
			this.store.clearFilter();
			return;
		}
		this.store.clearFilter(true);
		this.store.filter(array);
	},
	onAthRow : function(grid, rowIndex) {
		var selection = this.getStore().getAt(rowIndex);
		var win = Ext.create('mvc.view.sys.SysRole.Authority',{pkey : selection.get('bean.pkey')});
		win.show();;
	}
});