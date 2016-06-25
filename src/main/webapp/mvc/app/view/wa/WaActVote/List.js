Ext.define('mvc.view.wa.WaActVote.List',{
extend : 'Ext.panel.Panel',
oldId : 'WaActVote_list_',
loadMask : true,
multiSelect : true,
roles : '',
layout : 'border',
lock : true,
mdSearch : null,
mdAct : null,
mdMain : null,
mdMainTable : null,
mdLineTable : null,
mdRecordLineTable : null,
initComponent : function(){
var mainActs = [];		if (this.roles.indexOf('ins') != -1)
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
		if (this.roles.indexOf('doPublish') != -1)
mainActs.push({
		text : '发布',
		iconCls : 'green-icon',
		itemId : this.oldId+'doPublish',
		scope : this,
		handler : this.onDoPublish,
		disabled : this.lock
	});
		if (this.roles.indexOf('doClose') != -1)
mainActs.push({
		text : '关闭',
		iconCls : 'red-icon',
		itemId : this.oldId+'doClose',
		scope : this,
		handler : this.onDoClose,
		disabled : this.lock
	});
		if (this.roles.indexOf('edit') != -1)
mainActs.push({
		text : '编辑',
		iconCls : 'edit-icon',
		itemId : this.oldId+'edit',
		scope : this,
		handler : this.onEdit,
		disabled : this.lock
	});
		if (this.roles.indexOf('share') != -1)
mainActs.push({
			text : '分享设置',
			iconCls : 'share-icon',
			itemId : this.oldId + 'share',
			scope : this,
			handler : this.onShare,
			disabled : this.lock
		});
	this.items =[{
		region : 'north',
		xtype : 'panel',
		border : false,
		items : [{
				xtype : 'toolbar',
				itemId : this.oldId+'search',
				items : [{
						xtype : 'label',
						text : '名称：'
					},{
						xtype : 'textfield',
						name : 'name'
					},'',{
						xtype : 'label',
						text : '活动状态：'
					},{
						xtype : 'combo',
						name : 'status',
						mode : 'local',
						valueField : 'value',
						triggerAction : 'all',
						forceSelection : true,
						typeAhead : true,
						editable : false,
						emptyText : form_empty_text,
						store : Ext.create('mvc.combo.wa.WaOActVoteStatus')
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
				xtype : 'toolbar',
				itemId : this.oldId+'act',
				items : mainActs
			},{
				xtype : 'form',
				itemId :  this.oldId+'main',
				bodyPadding : '5 5 0 5',
				fieldDefaults : {
					anchor : '100%',
					labelWidth : 100,
					width : 275,
					labelAlign : 'right',
					readOnly : true
				},
				items : [{
						xtype : 'fieldset',
						title : '投票活动信息',
						collapsible : true,
						layout : {
							type : 'table',
							columns : 3
						},
						items : [{xtype : 'textfield',name : 'bean.name',fieldLabel : '名称'}
							,{xtype : 'datefield',name : 'bean.entryStartTime',fieldLabel : '报名开始时间',format : 'Y-m-d H:i:s'}
							,{xtype : 'datefield',name : 'bean.entryEndTime',fieldLabel : '报名结束时间',format : 'Y-m-d H:i:s'}
							,{xtype : 'datefield',name : 'bean.actStartTime',fieldLabel : '活动开始时间',format : 'Y-m-d H:i:s'}
							,{xtype : 'datefield',name : 'bean.actEndTime',fieldLabel : '活动结束时间',format : 'Y-m-d H:i:s'}
							]
					}]
			}]
	},{
		region : 'center',
		xtype : 'tabpanel',
		tabBar : {
			style : 'background:#fff'
		},
		items : [{
				xtype : Ext.create('mvc.view.wa.WaActVote.ListMain',{
							title : '投票活动',
							itemId : this.oldId+'maintable',
							iconCls : 'tab-user-icon',
							roles : this.roles,
							listeners : 								 {
									scope : this,
					                selectionchange: function(model, records) {
					                    if (records.length === 1){
								            this.mdMain.getForm().loadRecord(records[0]);
											this.mdLineTable.store.filter([{'id':'filter', 'property':'vote','value':records[0].get('bean.pkey')}]);
											this.mdRecordLineTable.store.filter([{'id':'filter', 'property':'vote','value':records[0].get('bean.pkey')}]);
											var status = records[0].get('bean.status');
											this.onChangeStatus(status);
								        }else{
								        	this.mdMain.getForm().reset();
								        	this.mdLineTable.store.removeAll();
								        	this.mdRecordLineTable.store.removeAll();
								        	this.onChangeStatus(-1);
								        }
					                }
				                }

						})
			},{
				xtype : Ext.create('mvc.view.wa.WaActVote.ListLineWaActVotePrize',{
							title : '奖品配置',
							itemId : this.oldId+'linetable',
							iconCls : 'tab-user-icon'
						})
			},{
				xtype : Ext.create('mvc.view.wa.WaActVote.ListLineWaActVotePrizeRecord',{
					title : '获奖记录',
					itemId : this.oldId+'recordlinetable',
					iconCls : 'tab-user-icon'
				})
			}]
	}];
		this.callParent(arguments);
		this.mdSearch = this.down('#'+this.oldId+'search');
		this.mdAct = this.down('#'+this.oldId+'act');
		this.mdMain = this.down('#'+this.oldId+'main');
		this.mdMainTable = this.down('#'+this.oldId+'maintable');
		this.mdLineTable = this.down('#'+this.oldId+'linetable');
		this.mdRecordLineTable = this.down('#'+this.oldId+'recordlinetable');
		mvc.Tools.onENTER2SearchBar(this.mdSearch,this);
		if (mainActs.length == 0)
			this.down('[region=north]').remove(this.mdAct);
},
onChangeStatus : function(status){
	var VOTE_STATUS_INIT = 0, VOTE_STATUS_PUBLISH = 1, VOTE_STATUS_ENTRYFIN = 2, VOTE_STATUS_ACTFIN=3,VOTE_STATUS_CLOSE=4;
	if (this.roles.indexOf('upd') != -1)
		this.down('#'+this.oldId+'upd').setDisabled(status == VOTE_STATUS_CLOSE || status == -1);
	if (this.roles.indexOf('del') != -1)
		this.down('#'+this.oldId+'del').setDisabled(status != VOTE_STATUS_INIT);
	if (this.roles.indexOf('doPublish') != -1)
		this.down('#'+this.oldId+'doPublish').setDisabled(status != VOTE_STATUS_INIT);
	if (this.roles.indexOf('doClose') != -1)
		this.down('#'+this.oldId+'doClose').setDisabled(status == VOTE_STATUS_CLOSE || status == -1);
	if (this.roles.indexOf('edit') != -1)
		this.down('#'+this.oldId+'edit').setDisabled(status == VOTE_STATUS_CLOSE || status == -1);
	if (this.roles.indexOf('share') != -1)
		 this.down('#'+this.oldId+'share').setDisabled(status == VOTE_STATUS_CLOSE || status == -1);
},
getStore : function(){
		return this.mdMainTable.store;
},
onSaveRecord : function(form, data){
		this.mdMainTable.store.insert(0,data);
		this.mdMainTable.getView().select(0);
		Ext.example.msg(msg_title, msg_text);
},
onIns : function(){
		var win = Ext.create('mvc.view.wa.WaActVote.Win',{
			title : this.title+'>新增'
		});
		win.on('create',this.onSaveRecord,this);
		win.show();
},
onUpd : function(){
		var selection = this.mdMainTable.getView().getSelectionModel().getSelection()[0];
		this.mdMainTable.onUpdWin(selection);
},
onDel : function(){
		var selection = this.mdMainTable.getView().getSelectionModel().getSelection();
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
						url : base_path+'/wa_WaActVote_delMulti?pkeys='+arr.toString()+'&rowVersions='+arrv.toString(),
						success : function (response, options) {
							var result = Ext.decode(response.responseText);
							if (result.success){
								me.mdMainTable.getStore().remove(selection);
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
	onRecord : function(form, data){
	var selection = this.getView().getSelectionModel().getSelection()[0];
	var bean = Ext.create('mvc.model.wa.WaActVote', data);
	Ext.apply(selection.data,bean.data);
	selection.commit();
	this.getView().select(selection);
	Ext.example.msg(msg_title, msg_text);			
},
onEdit : function() {
	var selection = this.mdMainTable.getView().getSelectionModel().getSelection()[0];
	this.onEditWin(selection);
},
onEditWin : function(selection){
	if (selection){
		var win = Ext.create('mvc.view.wa.WaActVote.WinEdit',{
			title : this.title+'>编辑',
		});
		win.on('create',this.mdMainTable.onUpdateRecord,this.mdMainTable);
		win.show();
		win.setActiveRecord(selection);
	}			
},
onShare : function() {
	var selection = this.mdMainTable.getView()
			.getSelectionModel().getSelection()[0];
	this.onShareWin(selection);
},
onShareWin : function(selection) {
	var model;
	if (selection) {
		var win = Ext.create(
				'mvc.view.js.JsMenuShare.Win', {
					title : this.title + '>分享设置',
				});
		Ext.Ajax.request({ 
			url : base_path+'/wa_WaActVote_share?pkey='+selection.get('bean.pkey')+'&rowVersion='+selection.get(BEAN_VERSION),
			async : false,
			success : function (response, options) {
				var result = Ext.decode(response.responseText);
				if (result.success){
					model  = Ext.create('mvc.model.js.JsMenuShare',result);
				}else{
				}
			}
		})
		win.show();
		win.setActiveRecord(model);
	}
},
onDoPublish : function(){
        var selection = this.mdMainTable.getView().getSelectionModel().getSelection()[0];
		var me = this;
		if (selection){
			Ext.MessageBox.confirm(msg_confirm_title, '确认发布吗？',
				function(btn) {
					if (btn != 'yes')
						return;
					Ext.Ajax.request({ 
						url : base_path+'/wa_WaActVote_publish?pkey='+selection.get('bean.pkey')+'&rowVersion='+selection.get(BEAN_VERSION),
						success : function (response, options) {
							var result = Ext.decode(response.responseText);
							if (result.success){
								var bean  = Ext.create('mvc.model.wa.WaActVote',result);
								Ext.apply(selection.data, bean.data);
								selection.commit();
								me.mdMainTable.getSelectionModel().deselectAll();
								me.mdMainTable.getView().select(selection);
								Ext.example.msg(msg_title, '发布--成功');
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
onDoClose : function(){
        var selection = this.mdMainTable.getView().getSelectionModel().getSelection()[0];
		var me = this;
		if (selection){
			Ext.MessageBox.confirm(msg_confirm_title, '确认关闭吗？',
				function(btn) {
					if (btn != 'yes')
						return;
					Ext.Ajax.request({
						url : base_path+'/wa_WaActVote_close?pkey='+selection.get('bean.pkey')+'&rowVersion='+selection.get(BEAN_VERSION),
						success : function (response, options) {
							var result = Ext.decode(response.responseText);
							if (result.success){
								var bean  = Ext.create('mvc.model.wa.WaActVote',result);
								Ext.apply(selection.data, bean.data);
								selection.commit();
								me.mdMainTable.getSelectionModel().deselectAll();
								me.mdMainTable.getView().select(selection);
								Ext.example.msg(msg_title, '关闭--成功');
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
onSearchCancel : function(){
		this.mdMainTable.getSelectionModel().deselectAll();
		mvc.Tools.searchClear(this.mdSearch);
		this.mdMainTable.store.clearFilter();
},
onSearch : function(){
		var array = mvc.Tools.searchValues(this.mdSearch);
		this.onSearchDo(array);
},
onSearchAdv : function(){
		var win = Ext.create('mvc.view.wa.WaActVote.WinSearch',{
			title : this.title+'>高级搜索',
			listCmp : this
		});
		win.show();
},
onSearchDo : function(array){
		this.mdMainTable.getSelectionModel().deselectAll();
		if (array.length == 0){
			this.mdMainTable.store.clearFilter();
			return;
		}
		this.mdMainTable.store.clearFilter(true);
		this.mdMainTable.store.filter(array);
}
});