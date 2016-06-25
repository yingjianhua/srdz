Ext.define('mvc.view.sys.SysSeq.List',{
	extend : 'Ext.grid.Panel',
	oldId : 'btn_SysSeq',
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
		var mainActs = [];
		if (this.roles.indexOf('upd') != -1)
			mainActs.push({
				text : '修改',
				iconCls : 'upd-icon',
				itemId : this.oldId+'upd',
				scope : this,
				handler : this.onUpd,
				disabled : this.lock
			});
		this.columns = [ 
		     {text : '名称', width : 120, dataIndex : 'one.name', sortable : true},
			{text : '是否按机构编号', width : 120, dataIndex : 'bean.orgFlag', sortable : true, renderer : mvc.Tools.optRenderer('sys','Sys','OYn')},
			{text : '前缀字符', width : 100, dataIndex : 'bean.firstStr', sortable : true},
			{text : '编号类型', width : 150, dataIndex : 'bean.type', sortable : true, renderer : mvc.Tools.optRenderer('sys','Sys','OType')},
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
		this.store=Ext.create('mvc.store.sys.SysSeq');
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.dockedItems=[{
			xtype : 'pagingtoolbar',
			store : this.store,
			dock : 'bottom',
			displayInfo : true,
			displayMsg : '显示 {0} - {1} 条，共计 {2} 条',
			emptyMsg : '没有数据',
			items : [{xtype : Ext.create('mvc.tools.ComboxPaging',{myList : this})}]
		}];
		this.callParent(arguments);
		this.store.load();
	},
	listeners : {
		selectionchange : function(selModel, selected) {
			if (this.roles.indexOf('upd') != -1)
				this.down('#'+this.oldId+'upd').setDisabled(selected.length === 0);
		}
	},
	
	
	onUpdateRecord : function(form, data){
		var selection = this.getView().getSelectionModel().getSelection()[0];
		var bean = Ext.create('mvc.model.sys.SysSeq', data);
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
			mvc.model.sys.SysSeq.load(selection.get('bean.pkey'), {
				scope : this,
				failure : function(response, operation) {
					Ext.example.msg(msg_title,msg_ajax);
				},
				success : function(response, operation) {
					Ext.apply(selection.data,response.data);
					var win = Ext.create('mvc.view.sys.SysSeq.Win',{
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
	}
});
