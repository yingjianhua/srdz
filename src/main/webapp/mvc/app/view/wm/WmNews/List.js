Ext.define('mvc.view.wm.WmNews.List',{
extend : 'Ext.grid.Panel',
oldId : 'btn_WmNews',
lock : true,
disableSelection : false,
loadMask : true,
multiSelect : true,
roles : '',
selModel : {selType : 'checkboxmodel'},
viewConfig : {enableTextSelection : true},
initComponent : function(){
var mainActs = [];		
		if (this.roles.indexOf('ins') != -1)
mainActs.push({
		text : '新增',
		iconCls : 'ins-icon',
		itemId : this.oldId+'ins',
		scope : this,
		handler : this.onIns
	});
		if (this.roles.indexOf('upd') != -1)
mainActs.push({
		text : '修改',
		iconCls : 'upd-icon',
		itemId : this.oldId+'upd',
		scope : this,
		handler : this.onUpd,
		disabled : this.lock
	});
		if (this.roles.indexOf('del') != -1)
mainActs.push({
		text : '删除',
		iconCls : 'del-icon',
		itemId : this.oldId+'del',
		scope : this,
		handler : this.onDel,
		disabled : this.lock
	});
		if (this.roles.indexOf('sync') != -1)
mainActs.push({
		text : '同步',
		iconCls : '请在Tb.accActOpt()中指定ICON',
		itemId : this.oldId+'sync',
		scope : this,
		handler : this.onSync,
		disabled : this.lock
	});
		if (this.roles.indexOf('unsync') != -1)
mainActs.push({
		text : '取消同步',
		iconCls : '请在Tb.accActOpt()中指定ICON',
		itemId : this.oldId+'unsync',
		scope : this,
		handler : this.onUnsync,
		disabled : this.lock
	});
		if (this.roles.indexOf('preview') != -1)
mainActs.push({
		text : '群发预览',
		iconCls : '请在Tb.accActOpt()中指定ICON',
		itemId : this.oldId+'preview',
		scope : this,
		handler : this.onPreview,
		disabled : this.lock
	});
		if (this.roles.indexOf('mass') != -1)
mainActs.push({
		text : '群发',
		iconCls : '请在Tb.accActOpt()中指定ICON',
		itemId : this.oldId+'mass',
		scope : this,
		handler : this.onMass,
		disabled : this.lock
	});
this.columns = [{text : '标题',width : 180,dataIndex : 'bean.title',sortable : true}
	,{text : '封面',width : 100,dataIndex : 'bean.imageUrl',sortable : true,renderer:function(v) {return "<img src='../"+v+"'width='90px' height='70px'>"}}
	,{text : '显示封面',width : 70,dataIndex : 'bean.showCoverPic',sortable : true,renderer : mvc.Tools.optRenderer('sys','Sys','OYn')}
	,{text : '作者',width : 100,dataIndex : 'bean.author',sortable : true}
	,{text : '摘要',width : 100,dataIndex : 'bean.summary',sortable : true}
	,{text : '原文链接',width : 100,dataIndex : 'bean.relUrl',sortable : true}
	,{text : '图文类型',width : 100,dataIndex : 'bean.type',sortable : true,renderer : mvc.Tools.optRenderer('wm','Wm','ONewsType')}
	,{text : '扩展链接',width : 80,dataIndex : 'bean.exp',sortable : true,renderer : mvc.Tools.beanRenderer()}
	,{text : '自定义链接',width : 100,dataIndex : 'bean.url',sortable : true}
	,{text : '同步状态',width : 70,dataIndex : 'bean.status',sortable : true,renderer : mvc.Tools.optRenderer('wx','Wx','OSyncStatus')}
	,{text : '公众帐号',width : 100,dataIndex : 'bean.account',sortable : true,renderer : mvc.Tools.beanRendererHref(),md : 'wx',mn : 'view.wx.WxAccount.List'}
	];
		if (mainActs.length > 0)
			this.tbar=mainActs;
		this.store=Ext.create('mvc.store.wm.WmNews'); 
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.on({cellclick:mvc.Tools.onCellclick});
		this.dockedItems=[{
		dock : 'top',
		xtype : 'toolbar',
		items : [{
				xtype : 'label',
				text : '标题：'
			},{
				xtype : 'textfield',
				name : 'title'
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
	this.callParent(arguments);		
	mvc.Tools.onENTER2SearchBar(this.down('[dock=top]'),this);
},
listeners : {
	selectionchange : function(selModel, selected) {
		var INIT = 1,SYNC = 2,DEL = 3;
		if(selected.length === 0) {
			if (this.roles.indexOf('upd') != -1)
				this.down('#'+this.oldId+'upd').setDisabled(true);
			if (this.roles.indexOf('del') != -1)
				this.down('#'+this.oldId+'del').setDisabled(true);
			if (this.roles.indexOf('sync') != -1)
				this.down('#'+this.oldId+'sync').setDisabled(true);
			if (this.roles.indexOf('unsync') != -1)
				this.down('#'+this.oldId+'unsync').setDisabled(true);
			if (this.roles.indexOf('preview') != -1)
				this.down('#'+this.oldId+'preview').setDisabled(true);
			if (this.roles.indexOf('mass') != -1)
				this.down('#'+this.oldId+'mass').setDisabled(true);
		} else {
			if (this.roles.indexOf('upd') != -1)
				this.down('#'+this.oldId+'upd').setDisabled(selected.length != 1||selected[0].get("bean.status") != INIT);
			if (this.roles.indexOf('del') != -1)
				this.down('#'+this.oldId+'del').setDisabled(selected.length != 1);
			if (this.roles.indexOf('sync') != -1)
				this.down('#'+this.oldId+'sync').setDisabled(selected.length != 1||selected[0].get("bean.status") != INIT);
			if (this.roles.indexOf('unsync') != -1)
				this.down('#'+this.oldId+'unsync').setDisabled(selected.length != 1||selected[0].get("bean.status") != SYNC);
			if (this.roles.indexOf('preview') != -1)
				this.down('#'+this.oldId+'preview').setDisabled(selected.length != 1||selected[0].get("bean.status") != SYNC);
			if (this.roles.indexOf('mass') != -1)
				this.down('#'+this.oldId+'mass').setDisabled(selected.length != 1||selected[0].get("bean.status") != SYNC);
		}
}
},
onSaveRecord : function(form, data){
		this.store.insert(0,data);
		this.getView().select(0);
		Ext.example.msg(msg_title, msg_text);	
},
onIns : function(){
		var win = Ext.create('mvc.view.wm.WmNews.WinTemplate',{
			title : this.title+'>新增'
		});
		win.on('create',this.onSaveRecord,this);
		win.show();
},
onUpdateRecord : function(form, data) {
		var selection = this.getView().getSelectionModel().getSelection()[0];
		var bean = Ext.create('mvc.model.wm.WmNews', data);
		Ext.apply(selection.data,bean.data);
		selection.commit();
		this.getView().deselect(this.getView().getSelectionModel().getSelection());
		this.getView().select(selection);
		Ext.example.msg(msg_title, msg_text);
},
onUpd : function() {
		var selection = this.getView().getSelectionModel().getSelection()[0];
		this.onUpdWin(selection);
},
onUpdRow : function(grid, rowIndex) {
		var selection = this.getStore().getAt(rowIndex);
		this.getView().deselect(this.getView().getSelectionModel().getSelection());
		this.getView().select(selection);
		this.onUpdWin(selection);
},
onUpdWin : function(selection){
		if (selection){
			var win = Ext.create('mvc.view.wm.WmNews.WinTemplate',{
				title : this.title+'>修改',
				pkey : selection.get("bean.pkey"),
				insFlag : false
			});
			win.on('create',this.onUpdateRecord,this);
			win.show();
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
					var arrv = new Array();
					for(var i = 0; i < selection.length; i++){
						arr.push(selection[i].get('bean.pkey'));
						arrv.push(selection[i].get(BEAN_VERSION));
					}
					Ext.Ajax.request({
						url : base_path+'/wm_WmNews_delMulti?pkeys='+arr.toString()+'&rowVersions='+arrv.toString(),
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
		var row = me.getStore().getAt(rowIndex);
		Ext.MessageBox.confirm(msg_confirm_title, msg_confirm_msg,
			function(btn) {
				if (btn != 'yes')
					return;
				Ext.Ajax.request({
					url : base_path+'/wm_WmNews_del?pkey='+row.get('bean.pkey')+'&rowVersion='+row.get(BEAN_VERSION),
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
onSync : function(){
	var me = this;
	var selection = this.getView().getSelectionModel().getSelection()[0];
	Ext.MessageBox.confirm(msg_confirm_title, "确认要将该图文同步到微信平台上吗？",
			function(btn) {
				if (btn != 'yes')
					return;
				Ext.Ajax.request({
					url : base_path+'/wm_WmNews_sync?pkey='+selection.get('bean.pkey')+'&rowVersion='+selection.get(BEAN_VERSION),
					success : function (response, options) {
						var result = Ext.decode(response.responseText);
						if (result.success){
							me.onUpdateRecord(me, result);
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
onUnsync : function(){
	var me = this;
	var selection = this.getView().getSelectionModel().getSelection()[0];
	Ext.MessageBox.confirm(msg_confirm_title, "取消同步后，图文将转为离线状态",
			function(btn) {
				if (btn != 'yes')
					return;
				Ext.Ajax.request({
					url : base_path+'/wm_WmNews_unsync?pkey='+selection.get('bean.pkey')+'&rowVersion='+selection.get(BEAN_VERSION),
					success : function (response, options) {
						var result = Ext.decode(response.responseText);
						if (result.success){
							me.onUpdateRecord(me, result);
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
onPreview : function(){
	var selection = this.getView().getSelectionModel().getSelection()[0];
	if(selection) {
		function onTrigger(data, params) {
			Ext.Ajax.request({
				url : base_path+'/wx_WxMassMessage_preview?tempPkey='+selection.get("bean.pkey")+'&tempClass=irille.wx.wm.WmNews&toUser='+data,
				success : function (response, options) {
					var result = Ext.decode(response.responseText);
					if (result.success){
						Ext.example.msg(msg_title, msg_text);
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
		var win = Ext.create("mvc.view.wx.WxPreviewUsr.Trigger");
		win.on('trigger', onTrigger, this);
		win.show();
		var store = win.down('grid').getStore();
		store.load();
	}
},
onMass : function(){
	var selection = this.getView().getSelectionModel().getSelection()[0];
	var win = Ext.create("mvc.view.wx.WxMassMessage.WinMassMessage", {
		tempPkey : selection.get("bean.pkey"),
		tempClass : "irille.wx.wm.WmNews"
	});
	win.show();
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
		var win = Ext.create('mvc.view.wm.WmNews.WinSearch',{
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