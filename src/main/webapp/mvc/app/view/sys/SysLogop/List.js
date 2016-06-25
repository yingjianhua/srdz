Ext.define('mvc.view.sys.SysLogop.List',{
	extend : 'Ext.grid.Panel',
	oldId : 'btn_SysLogop',
	lock : true,
	disableSelection : false,
	loadMask : true,
	multiSelect : true,
	roles : '',
	selModel : {selType : 'checkboxmodel'},
	initComponent : function(){
		
		var mainActs = [];
		this.columns = [ 
			{text : '日志类型', width : 80, dataIndex : 'bean.logType', sortable : true, renderer : mvc.Tools.optRenderer('sys','Sys','OLogType')},
			{text : '涉及功能', width : 100, dataIndex : 'bean.logFun', sortable : true},
			{text : '操作类型', width : 80, dataIndex : 'bean.logOptype', sortable : true, renderer : mvc.Tools.optRenderer('sys','Sys','OLogOptype')},
			{text : '操作内容', width : 200, dataIndex : 'bean.logOpdes', sortable : true},
			{text : '操作人员', width : 100, dataIndex : 'bean.logOper', sortable : true},
			{text : '操作时间', width : 140, dataIndex : 'bean.logOptime', sortable : true, renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')},
			{text : '备注', width : 100, dataIndex : 'bean.logRem', sortable : true}
		];
		this.tbar=mainActs;
		this.store=Ext.create('mvc.store.sys.SysLogop');
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.dockedItems=[{
			dock : 'top', 
			xtype : 'toolbar',
			items : [{
				xtype : 'label',
				text : '操作人员：'
			},{
				xtype : 'textfield',
				name : 'logOper'
			},' ',{
				xtype : 'label',
				text : '涉及功能：'
			},{
				xtype : 'textfield',
				name : 'logFun'
			},' ',{
				xtype : 'label',
				text : '操作内容：'
			},{
				xtype : 'textfield',
				name : 'logOpdes'
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
