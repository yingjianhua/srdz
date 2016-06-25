Ext.define('mvc.view.lg.LgLogin.List',{
	extend : 'Ext.grid.Panel',
	oldId : 'btn_LgLogin',
	lock : true,
	disableSelection : false,
	loadMask : true,
	multiSelect : true,
	roles : '',
	selModel : {selType : 'checkboxmodel'},
	initComponent : function(){
		
		var mainActs = [];
		this.columns = [ 
			{text : '登录时间', width : 140, dataIndex : 'bean.loginTime', sortable : true, renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')},
			{text : '用户', width : 100, dataIndex : 'bean.userSys', sortable : true, renderer : mvc.Tools.beanRenderer()},
			{text : '客户端类型', width : 100, dataIndex : 'bean.client', sortable : true, renderer : mvc.Tools.optRenderer('lg','Lg','OClient')},
			{text : 'IP地址', width : 100, dataIndex : 'bean.ip', sortable : true},
			{text : '系统', width : 100, dataIndex : 'bean.system', sortable : true},
			{text : '浏览器', width : 80, dataIndex : 'bean.browser', sortable : true},
			{text : '退出时间', width : 120, dataIndex : 'bean.logoutTime', sortable : true, renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')},
			{text : '累计处理时间', width : 100, dataIndex : 'bean.countProcTime', sortable : true},
			{text : '成功交易数', width : 100, dataIndex : 'bean.countSuccess', sortable : true},
			{text : '失败交易数', width : 100, dataIndex : 'bean.countFail', sortable : true},
			{text : '成功加载数', width : 100, dataIndex : 'bean.loadSuccess', sortable : true},
			{text : '失败加载数', width : 100, dataIndex : 'bean.loadFail', sortable : true}
		];
		this.tbar=mainActs;
		this.store=Ext.create('mvc.store.lg.LgLogin');
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.dockedItems=[{
			dock : 'top', 
			xtype : 'toolbar',
			items : [{
				xtype : 'label',
				text : '用户：'
			},mvc.Tools.crtComboTrigger(true,'sys_SysUser','',{
				name : 'userSys'
			}),' ',{
				xtype : 'label',
				text : '登录时间：'
			},{
				xtype : 'datefield',
				name : 'loginTime',
				format : 'Y-m-d'
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
