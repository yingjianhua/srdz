Ext.define('mvc.view.sys.SysAccessory.List',{
	extend : 'Ext.grid.Panel',
	oldId : 'btn_SysAccessory',
	lock : true,
	disableSelection : false,
	loadMask : true,
	multiSelect : true,
	parentTb : null,//传递过来的参数
	insFlag : null,
	isEdit : true,
	tbandkeyTable : null,
	tbandkeyPkey : null,
	roles : '',
	selModel : {selType : 'checkboxmodel'},
	initComponent : function(){
		var mainActs = [];
		if (this.isEdit){
			mainActs.push({
				text : '新增',
				iconCls : 'ins-icon',
				itemId : this.oldId+'ins',
				scope : this,
				handler : this.onIns
			});
			mainActs.push({
				text : '删除',
				disabled : this.lock,
				itemId : this.oldId+'del',
				iconCls : 'del-icon',
				scope : this,
				handler : this.onDel
			});
		}
			mainActs.push({
				text : '下载',
				disabled : this.lock,
				itemId : this.oldId+'down',
				iconCls : 'down-icon',
				scope : this,
				handler : this.onDown
			});
		this.columns = [ 
			{text : '附件类型', width : 100, dataIndex : 'bean.type', sortable : true, renderer : mvc.Tools.optRenderer('sys','Sys','OAccType')},
			{text : '名称', width : 100, dataIndex : 'bean.name', sortable : true},
			{text : '路径', width : 100, dataIndex : 'bean.path', sortable : true,hidden:true},
			{text : '附件大小(kb)', width : 100, dataIndex : 'bean.size', sortable : true, renderer : mvc.Tools.numberRenderer(), align:'right'}
		];
		this.tbar=mainActs;
		this.store = Ext.create('mvc.store.sys.SysAccessory');
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		if (this.tbandkeyPkey) {
			var array = [{"property":"tbandkeyTable","value":this.tbandkeyTable},{"property":"tbandkeyPkey","value":this.tbandkeyPkey}];
			//array.push();
			
			if (array.length == 0){
				this.store.clearFilter();
			}
			this.store.clearFilter(true);
			this.store.filter(array);
		}
		this.dockedItems=[
		{
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
			if(this.isEdit){
				this.down('#'+this.oldId+'del').setDisabled(selected.length === 0);
				this.down('#'+this.oldId+'down').setDisabled(selected.length === 0);
			}
		}
	},
	
	onSaveRecord : function(form, data){
		this.store.insert(0,data);
		this.getView().select(0);
		Ext.example.msg(msg_title, msg_text);
	},
	onIns : function(){
		var win = Ext.create('mvc.view.sys.SysAccessory.Win',{
			title : this.title+'>新增',
			parentTb : this.parentTb,
			tbandkeyTable : this.tbandkeyTable,
			tbandkeyPkey : this.tbandkeyPkey
		});
		win.on('create',this.onSaveRecord,this);
		win.show();
	},
	onDown : function(){
		var selection = this.getView().getSelectionModel().getSelection()[0];
		if (selection){
			//根路径时,请求'/download?xxx'时,会变成'/download/?xxx'
			if (base_path=='')
				window.open('download.action?fileUrl='+selection.get('bean.path')
					+'&fileName='+selection.get('bean.name')+'&isReal=1');
			else
				window.open(base_path+'/download.action?fileUrl='+selection.get('bean.path')
					+'&fileName='+selection.get('bean.name')+'&isReal=1');
		}
	},
	onUpdateRecord : function(form, data){
		var selection = this.getView().getSelectionModel().getSelection()[0];
		var bean = Ext.create('mvc.model.sys.SysAccessory', data);
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
			mvc.model.tp.TpAccessory.load(selection.get('bean.pkey'), {
				scope : this,
				failure : function(response, operation) {
					Ext.example.msg(msg_title,msg_ajax);
				},
				success : function(response, operation) {
					Ext.apply(selection.data,response.data);
					var win = Ext.create('mvc.view.sys.SysAccessory.Win',{
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
						url : base_path+'/sys_SysAccessory_delMulti',
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
					url : base_path+'/sys_SysAccessory_del?pkey='+me.getStore().getAt(rowIndex).get('bean.pkey'),
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
	}
});
