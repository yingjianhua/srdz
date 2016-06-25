Ext.define('mvc.view.wx.WxActionRecord.List',{
extend : 'Ext.grid.Panel',
oldId : 'btn_WxActionRecord',
lock : true,
disableSelection : false,
loadMask : true,
multiSelect : true,
roles : '',
selModel : {selType : 'checkboxmodel'},
viewConfig : {enableTextSelection : true},
initComponent : function(){
var mainActs = [];this.columns = [{text : '访问地址',width : 100,dataIndex : 'bean.actionName',sortable : true}
	,{text : '处理时长',width : 100,dataIndex : 'bean.dealPriod',sortable : true}
	,{text : '访问时间',width : 140,dataIndex : 'bean.visitTime',sortable : true,renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')}
	,{text : '访问ip',width : 100,dataIndex : 'bean.visitHost',sortable : true}
	,{text : '访问结果',width : 100,dataIndex : 'bean.successFlag',sortable : true,renderer : mvc.Tools.optRenderer('sys','Sys','OYn')}
	];
		if (mainActs.length > 0)
			this.tbar=mainActs;
		this.store=Ext.create('mvc.store.wx.WxActionRecord'); 
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.on({cellclick:mvc.Tools.onCellclick});
this.dockedItems=[{
		dock : 'top',
		xtype : 'toolbar',
		items : [{
				xtype : 'label',
				text : '访问地址：'
			},{
				xtype : 'textfield',
				name : 'actionName'
			},'',{
				xtype : 'label',
				text : '访问ip：'
			},{
				xtype : 'textfield',
				name : 'visitHost'
			},'',{
				xtype : 'label',
				text : '访问时间：'
			},{
				xtype : 'datefield',
				name : 'visitTime',
				format : 'Y-m-d H:i:s'
			},'',{
				xtype : 'button',
				text : '撤销',
				scope : this,
				iconCls : 'win-close-icon',
				handler : this.onSearchCancel
			},{
				xtype : 'splitbutton',
				text : '搜索',
				scope : this,
				iconCls : 'win-ok-icon',
				handler : this.onSearch,
				menu : [{text:'高级搜索',iconCls : 'win-ok-icon', scope : this,handler: this.onSearchAdv}]
			}]
	},{
		xtype : 'pagingtoolbar',
		store : this.store,
		dock : 'bottom',
		displayInfo : true,
		displayMsg : '显示 {0} - {1} 条，共计 {2} 条',
		emptyMsg : '没有数据',
		items : [{
				xtype : Ext.create('mvc.tools.ComboxPaging',{myList : this})
			}]
	}];
		this.callParent(arguments);		mvc.Tools.onENTER2SearchBar(this.down('[dock=top]'),this);},
listeners : {
	selectionchange : function(selModel, selected){
}
},
onSearchCancel : function(){
		this.getSelectionModel().deselectAll();
		mvc.Tools.searchClear(this.down('toolbar'));
		this.store.clearFilter();
},
onSearch : function(){
		var array = mvc.Tools.searchValues(this.down('toolbar'));
		this.onSearchDo(array);
},
onSearchAdv : function(){
		var win = Ext.create('mvc.view.wx.WxActionRecord.WinSearch',{
			title : this.title+'>高级搜索',
			listCmp : this
		});
		win.show();
},
onSearchDo : function(array){
		this.getSelectionModel().deselectAll();
		if (array.length == 0){
			this.store.clearFilter();
			return;
		}
		this.store.clearFilter(true);
		this.store.filter(array);
}
});