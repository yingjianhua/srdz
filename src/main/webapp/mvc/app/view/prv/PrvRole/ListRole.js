Ext.define('mvc.view.prv.PrvRole.ListRole',{
	extend : 'Ext.grid.Panel',
	oldId : 'btn_prvrole_',
	lock : true,
	disableSelection : false,
	loadMask : true,
	roles : '',
	initComponent : function(){
		var me = this;
		var mainActs = [];
		if (this.roles.indexOf('ins') != -1)
			mainActs.push({
				iconCls : 'ins-icon',
				itemId : this.oldId+'ins',
				scope : this,
				handler : this.onIns
			});
		if (this.roles.indexOf('upd') != -1){
			mainActs.push({
				iconCls : 'upd-icon',
				itemId : this.oldId+'upd',
				scope : this,
				handler : this.onUpd,
				disabled : this.lock
			},{
				iconCls : 'app-trigger-icon',
				itemId : this.oldId+'updSub',
				scope : this,
				handler : this.onEdit,
				disabled : this.lock
			});
		}
		if (this.roles.indexOf('del') != -1)
			mainActs.push({
				disabled : this.lock,
				itemId : this.oldId+'del',
				iconCls : 'del-icon',
				scope : this,
				handler : this.onDel
			});
		this.tbar=mainActs;
		this.columns = [
			{text : '代码', width : 60, dataIndex : 'bean.code', sortable : true},
			{text : '名称', width : 120, dataIndex : 'bean.name', sortable : true}
		];
		this.store=Ext.create('mvc.store.prv.PrvRole',{pageSize:200}); //不作分页处理
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.store.load();
		this.callParent(arguments);
	},
	listeners : {
		selectionchange : function(selModel, selected) {
			if (this.roles.indexOf('upd') != -1){
				this.down('#'+this.oldId+'upd').setDisabled(selected.length === 0);
				this.down('#'+this.oldId+'updSub').setDisabled(selected.length === 0);
			}
			if (this.roles.indexOf('del') != -1)
				this.down('#'+this.oldId+'del').setDisabled(selected.length === 0);
			//刷新右侧的功能面板
			var rightPanel = this.up('panel[action=mainPanel]').down('panel[action=rightPanel]');
			if (selected.length === 0)
				rightPanel.resetMainPkey(null);
			else
				rightPanel.resetMainPkey(selected[0].get('bean.pkey'));
		}
	},
	onSaveRecord : function(form, data){
		this.store.insert(0,data);
		this.getView().select(0);
		Ext.example.msg(msg_title, msg_text);
	},
	onIns : function(){
		var win = Ext.create('mvc.view.prv.PrvRole.Win',{
			title : '角色管理>新增'
		});
		win.on('create',this.onSaveRecord,this);
		win.show();
	},
	onUpdateRecord : function(form, data){
		var selection = this.getView().getSelectionModel().getSelection()[0];
		var bean = Ext.create('mvc.model.prv.PrvRole', data);
		Ext.apply(selection.data,bean.data);
		selection.commit();
		//this.getView().select(selection); 速度响应会变慢，在刷新右侧页面
		Ext.example.msg(msg_title, msg_text);
	},
	onUpd : function(){
		var selection = this.getView().getSelectionModel().getSelection()[0];
		this.onUpdWin(selection);
	},
	onUpdWin : function(selection){
		if (selection){
			mvc.model.prv.PrvRole.load(selection.get('bean.pkey'), {
				scope : this,
				failure : function(response, operation) {
					Ext.example.msg(msg_title,msg_ajax);
				},
				success : function(response, operation) {
					Ext.apply(selection.data,response.data);
					var win = Ext.create('mvc.view.prv.PrvRole.Win',{
						title : '角色管理>修改',
						insFlag : false
					});
					win.on('create',this.onUpdateRecord,this);
					win.show();
					win.setActiveRecord(selection);
				}
			});
		}
	},
	onEdit : function(){
		var selection = this.getView().getSelectionModel().getSelection()[0];
		if (selection){
			var win = Ext.create('mvc.view.prv.PrvRole.WinEdit',{
				title : '角色 ['+selection.get('bean.name')+']>编辑',
				record : selection
			});
			win.show();
			win.loadData();
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
						url : base_path+'/prv_PrvRole_delMulti',
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
});