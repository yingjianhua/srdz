Ext.define('mvc.view.lg.LgTran.List',{
	extend : 'Ext.grid.Panel',
	oldId : 'btn_LgTran',
	lock : true,
	disableSelection : false,
	loadMask : true,
	multiSelect : true,
	roles : '',
	selModel : {selType : 'checkboxmodel'},
	initComponent : function(){
		var mainActs = [];
		this.columns = [ 
			{text : '登录记录', width : 150, dataIndex : 'bean.login', sortable : true, renderer : mvc.Tools.beanRenderer()},
			{text : '开始时间', width : 140, dataIndex : 'bean.bTime', sortable : true, renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')},
			{text : '处理用时', width : 100, dataIndex : 'bean.procTime', sortable : true},
			{text : '成功标志', width : 100, dataIndex : 'bean.successFlag', sortable : true, renderer : mvc.Tools.optRenderer('sys','Sys','OYn')},
			{text : '备注', width : 250, dataIndex : 'bean.rem', sortable : true}
		];
		this.tbar=mainActs;
		this.store=Ext.create('mvc.store.lg.LgTran');
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.dockedItems=[{
			dock : 'top', 
			xtype : 'toolbar',
			items : [{
				xtype : 'label',
				text : '开始时间：'
			},{
				xtype : 'datefield',
				name : 'bTime',
				format : 'Y-m-d'
			},' ',{
				xtype : 'label',
				text : '备注：'
			},{
				xtype : 'textfield',
				name : 'rem'
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
