Ext.define('mvc.view.sys.SysCom.List',{
	extend : 'Ext.grid.Panel',
	oldId : 'btn_SysCom',
	lock : true,
	disableSelection : false,
	loadMask : true,
	multiSelect : true,
	roles : '',
	selModel : {selType : 'checkboxmodel'},
	initComponent : function(){
		
		var mainActs = [];
		this.columns = [ 
			{text : '机构名称', width : 100, dataIndex : 'bean.name', sortable : true},
			{text : '机构简称', width : 100, dataIndex : 'bean.shortName', sortable : true},
			{text : '电话1', width : 100, dataIndex : 'bean.tel1', sortable : true},
			{text : '电话2', width : 100, dataIndex : 'bean.tel2', sortable : true},
			{text : '传真', width : 100, dataIndex : 'bean.fax', sortable : true},
			{text : '网址', width : 100, dataIndex : 'bean.website', sortable : true},
			{text : '地址', width : 100, dataIndex : 'bean.addr', sortable : true},
			{text : '邮编', width : 100, dataIndex : 'bean.zipCode', sortable : true},
			{text : '备注', width : 100, dataIndex : 'bean.rem', sortable : true},
			{text : '更新员', width : 100, dataIndex : 'bean.updatedBy', sortable : true, renderer : mvc.Tools.beanRenderer()},
			{text : '更新时间', width : 140, dataIndex : 'bean.updatedDateTime', sortable : true, renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')},
			{text : '建档员', width : 100, dataIndex : 'bean.createdBy', sortable : true, renderer : mvc.Tools.beanRenderer()},
			{text : '建档时间', width : 140, dataIndex : 'bean.createdDateTime', sortable : true, renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')}
		];
		this.tbar=mainActs;
		this.store=Ext.create('mvc.store.sys.SysCom');
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.dockedItems=[{
			dock : 'top', 
			xtype : 'toolbar',
			items : [{
				xtype : 'label',
				text : '机构名称：'
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
	}
});
