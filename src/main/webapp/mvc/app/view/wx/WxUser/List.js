Ext.define('mvc.view.wx.WxUser.List',{
extend : 'Ext.grid.Panel',
oldId : 'btn_WxUser',
lock : true,
disableSelection : false,
loadMask : true,
multiSelect : true,
roles : '',
selModel : {selType : 'checkboxmodel'},
viewConfig : {enableTextSelection : true},
initComponent : function(){
var mainActs = [];		if (this.roles.indexOf('rem') != -1)
mainActs.push({
		text : '修改备注',
		iconCls : '请在Tb.accActOpt()中指定ICON',
		itemId : this.oldId+'rem',
		scope : this,
		handler : this.onRem,
		disabled : this.lock
	});
		if (this.roles.indexOf('move') != -1)
mainActs.push({
		text : '移动到',
		iconCls : '请在Tb.accActOpt()中指定ICON',
		itemId : this.oldId+'move',
		scope : this,
		handler : this.onMove,
		disabled : this.lock
	});
		if (this.roles.indexOf('toBlack') != -1)
mainActs.push({
		text : '加入黑名单',
		iconCls : '请在Tb.accActOpt()中指定ICON',
		itemId : this.oldId+'toBlack',
		scope : this,
		handler : this.onToBlack,
		disabled : this.lock
	});
		if (this.roles.indexOf('sync') != -1)
mainActs.push({
		text : '同步用户',
		iconCls : '请在Tb.accActOpt()中指定ICON',
		itemId : this.oldId+'sync',
		scope : this,
		handler : this.onSync,
		disabled : false
	});
		if (this.roles.indexOf('refresh') != -1)
mainActs.push({
		text : '更新用户基本信息',
		iconCls : '请在Tb.accActOpt()中指定ICON',
		itemId : this.oldId+'refresh',
		scope : this,
		handler : this.onRefresh,
		disabled : false
	});
		if (this.roles.indexOf('uqs') != -1)
mainActs.push({
		text : '专属二维码设置',
		iconCls : '请在Tb.accActOpt()中指定ICON',
		itemId : this.oldId+'uqs',
		scope : this,
		handler : this.onUqs,
	});
this.columns = [{text : '关注状态',width : 60,dataIndex : 'bean.status',sortable : true,renderer : mvc.Tools.optRenderer('wx','Wx','OStatus')}
	,{text : '昵称',width : 100,dataIndex : 'bean.nickname',sortable : true}
	,{text : '性别',width : 100,dataIndex : 'bean.sex',sortable : true,renderer : mvc.Tools.optRenderer('sys','Sys','OSex')}
	,{text : '城市',width : 100,dataIndex : 'bean.city',sortable : true}
	,{text : '省份',width : 100,dataIndex : 'bean.province',sortable : true}
	,{text : '国家',width : 100,dataIndex : 'bean.country',sortable : true}
	,{text : '头像',width : 100,dataIndex : 'bean.imageUrl',sortable : true, renderer: function(v) {return "<img src='"+v+"' height='50px'/ >"}}
	,{text : '备注',width : 100,dataIndex : 'bean.rem',sortable : true}
	,{text : '用户分组',width : 100,dataIndex : 'bean.userGroup',sortable : true,renderer : mvc.Tools.beanRendererHref(),md : 'wxex',mn : 'view.wx.WxUserGroup.List'}
	,{text : '关注时间',width : 140,dataIndex : 'bean.subscribeTime',sortable : true,renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')}
	,{text : '同步时间',width : 140,dataIndex : 'bean.syncTime',sortable : true,renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')}
	,{text : '粉丝ID',width : 100,dataIndex : 'bean.openId',sortable : true}
	,{text : '唯一ID',width : 100,dataIndex : 'bean.unionId',sortable : true}
	];
		if (mainActs.length > 0)
			this.tbar=mainActs;
		this.store=Ext.create('mvc.store.wx.WxUser'); 
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.on({cellclick:mvc.Tools.onCellclick});
this.dockedItems=[{
		dock : 'top',
		xtype : 'toolbar',
		items : [{
				xtype : 'label',
				text : '昵称：'
			},{
				xtype : 'textfield',
				name : 'nickname'
			},'',{
				xtype : 'label',
				text : '城市：'
			},{
				xtype : 'textfield',
				name : 'city'
			},'',{
				xtype : 'label',
				text : '备注：'
			},{
				xtype : 'textfield',
				name : 'rem'
			},'',{
				xtype : 'label',
				text : '关注时间：'
			},{
				xtype : 'datefield',
				name : 'subscribeTime',
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
		if (this.roles.indexOf('rem') != -1)
			this.down('#'+this.oldId+'rem').setDisabled(selected.length != 1);	
		if (this.roles.indexOf('move') != -1)
			this.down('#'+this.oldId+'move').setDisabled(selected.length === 0);	
		if (this.roles.indexOf('toBlack') != -1)
			this.down('#'+this.oldId+'toBlack').setDisabled(selected.length === 0);	
}
},
onSaveRecord : function(form, data){
		this.store.insert(0,data);
		this.getView().select(0);
		Ext.example.msg(msg_title, msg_text);	
},
onUpdateRecord : function(form, data){
		var selection = this.getView().getSelectionModel().getSelection()[0];
		var bean = Ext.create('mvc.model.wx.WxUser', data);
		Ext.apply(selection.data,bean.data);
		selection.commit();
		this.getView().select(selection);
		Ext.example.msg(msg_title, msg_text);			
},
onRem : function(){
	var selection = this.getView().getSelectionModel().getSelection()[0];
	if (selection){
			var win = Ext.create('mvc.view.wx.WxUser.WinRem',{
				title : this.title+'>修改备注'
			});
			win.on('create',this.onUpdateRecord,this);
			win.show();
			win.setActiveRecord(selection);
		}
},
onMove : function(){
	var selection = this.getView().getSelectionModel().getSelection();
	if (selection){
		var arr=new Array();
		var arrv = new Array();
		for(var i = 0; i < selection.length; i++){
			arr.push(selection[i].get('bean.pkey'));
			arrv.push(selection[i].get(BEAN_VERSION));
		}
		var win = Ext.create('mvc.view.wx.WxUser.WinMove',{
			title : this.title+'>移动分组',
			pkeys : arr.toString(),
			rowVersions : arrv.toString()
		});
		win.on('create',this.onUpdateRecord,this);
		win.show();
		win.setActiveRecord(selection);
	}
},
onToBlack : function(){
	var selection = this.getView().getSelectionModel().getSelection();
	var me = this;
	if (selection){
		var arr=new Array();
		var arrv = new Array();
		for(var i = 0; i < selection.length; i++){
			arr.push(selection[i].get('bean.pkey'));
			arrv.push(selection[i].get(BEAN_VERSION));
		}
		Ext.Ajax.request({
			url : base_path+'/wx_WxUser_toBlack?pkeys='+arr.toString()+'&rowVersions='+arrv.toString(),
			success : function (response, options) {
				var result = Ext.decode(response.responseText);
				if (result.success){
					Ext.example.msg(msg_title, result.msg);
					Ext.each(selection,function(select) {
						select.data["bean.userGroup"]=result["bean.pkey"]+bean_split+result["bean.name"];
						select.commit();
					})
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
},
onSync : function(){
	var me = this;
	Ext.MessageBox.confirm(msg_confirm_title, "确定要同步吗？", 
		function(btn) {
			if (btn != 'yes')
				return;
			Ext.Ajax.request({
				url : base_path+'/wx_WxUser_sync',
				success : function (response, options) {
					var result = Ext.decode(response.responseText);
					if (result.success){
						Ext.example.msg(msg_title, result.msg);
						me.onSearch();
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
		})
},
onRefresh : function(){
	var selection = this.getView().getSelectionModel().getSelection();
	var me = this;
	if (selection){
		var arr=new Array();
		var arrv = new Array();
		for(var i = 0; i < selection.length; i++){
			arr.push(selection[i].get('bean.pkey'));
			arrv.push(selection[i].get(BEAN_VERSION));
		}
		Ext.Ajax.request({
			url : base_path+'/wx_WxUser_refresh?pkeys='+arr.toString()+'&rowVersions='+arrv.toString(),
			success : function (response, options) {
				var result = Ext.decode(response.responseText);
				if (result.success){
					Ext.example.msg(msg_title, result.msg);
					me.onSearch();
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
	} else {
		Ext.MessageBox.confirm(msg_confirm_title, "确定要更新所有用户的基本信息吗？", 
		function(btn) {
			if (btn != 'yes')
				return;
			Ext.Ajax.request({
				url : base_path+'/wx_WxUser_refresh',
				success : function (response, options) {
					var result = Ext.decode(response.responseText);
					if (result.success){
						Ext.example.msg(msg_title, result.msg);
						me.onSearch();
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
		})
	}
},
onUqs : function() {
	var win = Ext.create("mvc.view.wx.WxUser.WinUqs", {
		title : "专属二维码设置",
	})
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
		var win = Ext.create('mvc.view.wx.WxUser.WinSearch',{
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