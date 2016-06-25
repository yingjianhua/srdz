Ext.define('mvc.view.wa.WaActGet.List',{
extend : 'Ext.grid.Panel',
oldId : 'btn_WaActGet',
lock : true,
disableSelection : false,
loadMask : true,
multiSelect : true,
roles : '',
selModel : {selType : 'checkboxmodel'},
viewConfig : {enableTextSelection : true},
initComponent : function(){
	var mainActs = [];		if (this.roles.indexOf('upd') != 1)
		mainActs.push({
				text : '发货',
				iconCls : 'upd-icon',
				itemId : this.oldId+'upd',
				scope : this,
				handler : this.onUpd,
				//disabled : this.lock
			});
	this.columns = [{text : '手机号码',width : 100,dataIndex : 'bean.recMobile',sortable : true}
	,{text : '收货人名称',width : 100,dataIndex : 'bean.recName',sortable : true}
	,{text : '收货人地址',width : 100,dataIndex : 'bean.recAddr',sortable : true}
	,{text : '奖项设置',width : 100,dataIndex : 'bean.actItem',sortable : true,renderer : mvc.Tools.beanRendererHref(),md : 'wa',mn : 'view.wa.WaActItem.List'}
	,{text : '奖品设置',width : 100,dataIndex : 'bean.actPrize',sortable : true,renderer : mvc.Tools.beanRendererHref(),md : 'wa',mn : 'view.wa.WaActPrize.List'}
	,{text : '抽奖时间',width : 140,dataIndex : 'bean.actTime',sortable : true,renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')}
	,{text : '发货状态',width : 100,dataIndex : 'bean.sendStatus',sortable : true,renderer : mvc.Tools.optRenderer('wa','Wa','OActSendStatus')}
	];
		if (mainActs.length > 0)
			this.tbar=mainActs;
		this.store=Ext.create('mvc.store.wa.WaActGet'); 
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.on({cellclick:mvc.Tools.onCellclick});
this.dockedItems=[{
		dock : 'top',
		xtype : 'toolbar',
		items : [{
			xtype : 'label',
			text : '发货状态：'
		},{
			xtype : 'combo',
			name : 'sendStatus',
			mode : 'local',
			width: 100,
			valueField : 'value',
			triggerAction : 'all',
			forceSelection : true,
			typeAhead : true,
			editable : false,
			emptyText : form_empty_text,
			store : Ext.create('mvc.combo.wa.WaOActSendStatus')
		},{
				xtype : 'label',
				text : '手机号码：'
			},{
				xtype : 'textfield',
				name : 'recMobile',
				width: 100
			},{
				xtype : 'label',
				text : '收货人名称：'
			},{
				xtype : 'textfield',
				name : 'recName',
				width: 100
			},{
				xtype : 'button',
				text : '搜索',
				scope : this,
				iconCls : 'win-ok-icon',
				handler : this.onSearch,
				//menu : [{text:'高级搜索',iconCls : 'win-ok-icon', scope : this,handler: this.onSearchAdv}]
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
		if (this.roles.indexOf('upd') != -1)
			this.down('#'+this.oldId+'upd').setDisabled(selected.length === 0);	
}
},
onUpdateRecord : function(form, data){
		var selection = this.getView().getSelectionModel().getSelection()[0];
		var bean = Ext.create('mvc.model.wa.WaActGet', data);
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
			var win = Ext.create('mvc.view.wa.WaActGet.Win',{
				title : '活动奖品>发货',
				insFlag : false
			});
			win.on('create',this.onUpdateRecord,this);
			win.show();
			win.setActiveRecord(selection);
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
		var win = Ext.create('mvc.view.wa.WaActGet.WinSearch',{
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