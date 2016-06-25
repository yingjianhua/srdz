Ext.define('mvc.view.wa.WaAct.List',{
extend : 'Ext.panel.Panel',
oldId : 'WaAct_list_',
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
		if (this.roles.indexOf('doOpen') != -1)
mainActs.push({
		text : '发布',
		iconCls : 'upd-icon',
		itemId : this.oldId+'doOpen',
		scope : this,
		handler : this.onDoOpen,
		disabled : this.lock
	});
		if (this.roles.indexOf('doClose') != -1)
mainActs.push({
		text : '关闭',
		iconCls : 'upd-icon',
		itemId : this.oldId+'doClose',
		scope : this,
		handler : this.onDoClose,
		disabled : this.lock
	});
		if (this.roles.indexOf('doWinning') != -1)
mainActs.push({
		text : '查看中奖纪录',
		iconCls : 'upd-icon',
		itemId : this.oldId+'doWinning',
		scope : this,
		handler : this.onDoWinning,
		disabled : this.lock
	});
		if (this.roles.indexOf('doDraw') != -1)
mainActs.push({
		text : '查看抽奖记录',
		iconCls : 'upd-icon',
		itemId : this.oldId+'doDraw',
		scope : this,
		handler : this.onDoDraw,
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
						text : '活动名称：'
					},{
						xtype : 'textfield',
						name : 'name'
					},'',{
						xtype : 'label',
						text : '抽奖类型：'
					},{
						xtype : 'combo',
						name : 'type',
						mode : 'local',
						valueField : 'value',
						triggerAction : 'all',
						forceSelection : true,
						typeAhead : true,
						editable : false,
						emptyText : form_empty_text,
						store : Ext.create('mvc.combo.wa.WaOActDrawType')
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
						title : '抽奖活动信息',
						collapsible : true,
						layout : {
							type : 'table',
							columns : 3
						},
						items : [{xtype : 'textfield',name : 'bean.name',fieldLabel : '活动名称'}
							,{xtype : 'textfield',name : 'bean.title',fieldLabel : '活动描述'}
							,
								mvc.Tools.crtComboForm(true,{
											name : 'bean.type',
											fieldLabel : '抽奖类型',
											store : Ext.create('mvc.combo.wa.WaOActDrawType')
										})
							,{xtype : 'numberfield',name : 'bean.doCount',fieldLabel : '抽奖次数',allowDecimals : false}
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
				xtype : Ext.create('mvc.view.wa.WaAct.ListMain',{
							title : '抽奖活动',
							itemId : this.oldId+'maintable',
							iconCls : 'tab-user-icon',
							roles : this.roles,
							listeners :     {
                            scope : this,
                            selectionchange: function(model, records) {
                                if (records.length === 1){
                                    this.mdMain.getForm().loadRecord(records[0]);
                                    this.mdLineTable.store.filter([{'id':'filter', 'property':'act','value':records[0].get('bean.pkey')}]);
                                    if (this.roles.indexOf('upd') != -1)
                                        this.down('#'+this.oldId+'upd').setDisabled(false);
                                    if (this.roles.indexOf('del') != -1)
                                        this.down('#'+this.oldId+'del').setDisabled(false);
                                    if (this.roles.indexOf('doOpen') != -1)
                                        this.down('#'+this.oldId+'doOpen').setDisabled(false);
                                    if (this.roles.indexOf('doClose') != -1)
                                        this.down('#'+this.oldId+'doClose').setDisabled(false);
                                    if (this.roles.indexOf('doWinning') != -1)
                                        this.down('#'+this.oldId+'doWinning').setDisabled(false);
                                    if (this.roles.indexOf('doDraw') != -1)
                                        this.down('#'+this.oldId+'doDraw').setDisabled(false);
                                }else{
                                    this.mdMain.getForm().reset();
                                    this.mdLineTable.store.removeAll();
                                    if (this.roles.indexOf('upd') != -1)
                                        this.down('#'+this.oldId+'upd').setDisabled(true);
                                    if (this.roles.indexOf('del') != -1)
                                        this.down('#'+this.oldId+'del').setDisabled(true);
                                    if (this.roles.indexOf('doOpen') != -1)
                                        this.down('#'+this.oldId+'doOpen').setDisabled(true);
                                    if (this.roles.indexOf('doClose') != -1)
                                        this.down('#'+this.oldId+'doClose').setDisabled(true);
                                    if (this.roles.indexOf('doWinning') != -1)
                                        this.down('#'+this.oldId+'doWinning').setDisabled(true);
                                    if (this.roles.indexOf('doDraw') != -1)
                                        this.down('#'+this.oldId+'doDraw').setDisabled(true);
                                }
                            }
                        }

						})
			},{
				xtype : Ext.create('mvc.view.wa.WaAct.ListLineWaActSet',{
							title : '活动配置信息',
							itemId : this.oldId+'linetable',
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
		mvc.Tools.onENTER2SearchBar(this.mdSearch,this);
		if (mainActs.length == 0)
			this.down('[region=north]').remove(this.mdAct);
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
		var win = Ext.create('mvc.view.wa.WaAct.Win',{
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
						url : base_path+'/wa_WaAct_delMulti?pkeys='+arr.toString()+'&rowVersions='+arrv.toString(),
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
onDoOpen : function(){
        var selection = this.mdMainTable.getView().getSelectionModel()
                .getSelection();
        if (selection) {
            var me = this;
            if (selection[0].get('bean.status') == 1) {
                Ext.Ajax.request({
                    url : base_path + '/wa_WaAct_Publish?pkey='
                            + selection[0].get('bean.pkey'),
                    success : function(response, options) {
                        var result = Ext.decode(response.responseText);
                        if (result.success) {
                            me.getStore().reload();
                            me.mdMainTable.getView().deselect(
                                    me.mdMainTable.getView().getSelectionModel().getSelection());
                            Ext.example.msg(msg_title, msg_submit);
                        } else {
                            Ext.MessageBox.show({
                                title : msg_title,
                                msg : result.msg,
                                buttons : Ext.MessageBox.OK,
                                icon : Ext.MessageBox.ERROR
                            });
                        }
                    }
                })
            } else if (selection[0].get('bean.status') == 2) {
                Ext.MessageBox.show({
                    title : "提示",
                    msg : "活动已发布",
                    buttons : Ext.MessageBox.OK,
                    icon : Ext.MessageBox.ERROR
                });
            } else if (selection[0].get('bean.status') == 3) {
                Ext.MessageBox.show({
                    title : "提示",
                    msg : "活动已关闭不能再发布",
                    buttons : Ext.MessageBox.OK,
                    icon : Ext.MessageBox.ERROR
                });
            }
        }  
},
onDoClose : function(){
        var selection = this.mdMainTable.getView().getSelectionModel()
                .getSelection();
        if (selection) {
            var me = this;
            Ext.MessageBox.confirm("提示", "是否关闭此活动", function(btn) {
                if (btn != 'yes')
                    return;
                if (selection[0].get('bean.status') != 3) {
                    Ext.Ajax.request({
                        url : base_path + '/wa_WaAct_Close?pkey='
                                + selection[0].get('bean.pkey'),
                        success : function(response, options) {
                            var result = Ext.decode(response.responseText);
                            if (result.success) {
                                me.getStore().reload();
                                me.mdMainTable.getView()
                                        .deselect(
                                                me.mdMainTable.getView().getSelectionModel()
                                                        .getSelection());
                                Ext.example.msg(msg_title, msg_submit);
                            } else {
                                Ext.MessageBox.show({
                                    title : msg_title,
                                    msg : result.msg,
                                    buttons : Ext.MessageBox.OK,
                                    icon : Ext.MessageBox.ERROR
                                });
                            }
                        }
                    })
                }
            })
        }  
},
onDoWinning : function(){
    var selection = this.mdMainTable.getView().getSelectionModel()
  .getSelection();
    var win = Ext.create('mvc.view.wa.WaAct.WinCheck',{
        title : this.title+'>查看中奖纪录',
        listUrl : 'mvc.view.wa.WaActGet.List'
    });
    win.setActiveRecord(selection);
    win.on('create',this.onSaveRecord,this);
    win.show();
},
onDoDraw : function(){
  var selection = this.mdMainTable.getView().getSelectionModel()
  .getSelection();
    var win = Ext.create('mvc.view.wa.WaAct.WinCheck',{
        title : this.title+'>查看抽奖纪录',
        listUrl : 'mvc.view.wa.WaActDo.List'
    });
    win.setActiveRecord(selection);
    win.on('create',this.onSaveRecord,this);
    win.show();
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
		var win = Ext.create('mvc.view.wa.WaAct.WinSearch',{
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