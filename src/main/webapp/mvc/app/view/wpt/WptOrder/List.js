Ext.define('mvc.view.wpt.WptOrder.List',{
extend : 'Ext.panel.Panel',
oldId : 'WptOrder_list_',
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
var mainActs = [];
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
if (this.roles.indexOf('service') != -1)
	mainActs.push({
			text : '服务',
			iconCls : 'edit-icon',
			itemId : this.oldId+'service',
			scope : this,
			handler : this.onService,
			disabled : this.lock
		});
if (this.roles.indexOf('accept') != -1)
	mainActs.push({
			text : '确认',
			iconCls : 'edit-icon',
			itemId : this.oldId+'accept',
			scope : this,
			handler : this.onAccept,
			disabled : this.lock
		});
if (this.roles.indexOf('deposit') != -1)
	mainActs.push({
			text : '收取定金',
			iconCls : 'edit-icon',
			itemId : this.oldId+'deposit',
			scope : this,
			handler : this.onDeposit,
			disabled : this.lock
		});
if (this.roles.indexOf('residue') != -1)
	mainActs.push({
			text : '收取余款',
			iconCls : 'edit-icon',
			itemId : this.oldId+'residue',
			scope : this,
			handler : this.onResidue,
			disabled : this.lock
		});
if (this.roles.indexOf('agreeRefund') != -1)
	mainActs.push({
			text : '同意退款',
			iconCls : 'edit-icon',
			itemId : this.oldId+'agreeRefund',
			scope : this,
			handler : this.onAgreeRefund,
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
						text : '订单号：'
					},{
						xtype : 'textfield',
						name : 'orderid'
					},'',{
						xtype : 'label',
						text : '订单状态：'
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
						store : Ext.create('mvc.combo.wpt.WptOStatus')
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
						title : '订单信息',
						collapsible : true,
						layout : {
							type : 'table',
							columns : 3
						},
						items : [{xtype : 'textfield',name : 'bean.orderid',fieldLabel : '订单号'}
							,{
								xtype : 'beantrigger',
								name : 'bean.wxuser',
								fieldLabel : '关注用户',
								bean : 'WxUser',
								beanType : 'wx',
								emptyText : form_empty_text
							},{
								xtype : 'beantrigger',
								name : 'bean.city',
								fieldLabel : '城市',
								bean : 'WptCity',
								beanType : 'wpt',
								emptyText : form_empty_text
							},{
								xtype : 'beantrigger',
								name : 'bean.banquet',
								fieldLabel : '宴会类型',
								bean : 'WptBanquet',
								beanType : 'wpt',
								emptyText : form_empty_text
							},{xtype : 'datefield',name : 'bean.time',fieldLabel : '用餐时间',format : 'Y-m-d H:i:s'}
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
				xtype : Ext.create('mvc.view.wpt.WptOrder.ListMain',{
							title : '订单',
							itemId : this.oldId+'maintable',
							iconCls : 'tab-user-icon',
							roles : this.roles,
							listeners : 							 {
								scope : this,
				                selectionchange: function(model, records) {
				                    if (records.length === 1){
				                        this.mdMain.getForm().loadRecord(records[0]);
        								this.mdLineTable.store.filter([{'id':'filter', 'property':'wptorder','value':records[0].get('bean.pkey')}]);
        								this.mdServiceTable.store.filter([{'id':'filter', 'property':'wptorder','value':records[0].get('bean.pkey')}]);
        								var status = records[0].get("bean.status");
        								if (this.roles.indexOf('upd') != -1)
											this.down('#'+this.oldId+'upd').setDisabled(status != 0 && status != 1);
										if (this.roles.indexOf('del') != -1)
											this.down('#'+this.oldId+'del').setDisabled(status != 0 && status != 1);
										if (this.roles.indexOf('service') != -1)
											this.down('#'+this.oldId+'service').setDisabled(status != 0 && status != 1);
										if (this.roles.indexOf('accept') != -1)
											this.down('#'+this.oldId+'accept').setDisabled(status != 1);
										if (this.roles.indexOf('deposit') != -1)
											this.down('#'+this.oldId+'deposit').setDisabled(status != 2);
										if (this.roles.indexOf('residue') != -1)
											this.down('#'+this.oldId+'residue').setDisabled(status != 3);
										if (this.roles.indexOf('agreeRefund') != -1)
											this.down('#'+this.oldId+'agreeRefund').setDisabled(status != 8);
				                    }else{
				                    	this.mdMain.getForm().reset();
				                    	this.mdLineTable.store.removeAll();
				                    	this.mdServiceTable.store.removeAll();
				                    	if (this.roles.indexOf('upd') != -1)
											this.down('#'+this.oldId+'upd').setDisabled(true);
										if (this.roles.indexOf('del') != -1)
											this.down('#'+this.oldId+'del').setDisabled(true);
										if (this.roles.indexOf('service') != -1)
											this.down('#'+this.oldId+'service').setDisabled(true);
										if (this.roles.indexOf('accept') != -1)
											this.down('#'+this.oldId+'accept').setDisabled(true);
										if (this.roles.indexOf('deposit') != -1)
											this.down('#'+this.oldId+'deposit').setDisabled(true);
										if (this.roles.indexOf('residue') != -1)
											this.down('#'+this.oldId+'residue').setDisabled(true);
										if (this.roles.indexOf('agreeRefund') != -1)
											this.down('#'+this.oldId+'agreeRefund').setDisabled(true);
				                    }
				                }
			                }

						})
			},{
				xtype : Ext.create('mvc.view.wpt.WptOrder.ListLineWptOrderLine',{
							title : '菜品',
							itemId : this.oldId+'linetable',
							iconCls : 'tab-user-icon'
						})
			},{
				xtype : Ext.create('mvc.view.wpt.WptOrder.ListLineWptOrderService',{
					title : '服务',
					itemId : this.oldId+'servicetable',
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
		this.mdServiceTable = this.down('#'+this.oldId+'servicetable');
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
						url : base_path+'/wpt_WptOrder_delMulti?pkeys='+arr.toString()+'&rowVersions='+arrv.toString(),
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
onService : function() {
	var selection = this.mdMainTable.getView().getSelectionModel().getSelection()[0];
	this.onServiceWin(selection);
},
onAccept : function() {
	var me = this;
	var selection = this.mdMainTable.getView().getSelectionModel().getSelection()[0];
	Ext.MessageBox.confirm(msg_confirm_title, "确认后进入支付定金状态，订单信息将不可修改", 
		function(btn) {
			if (btn != 'yes')
				return;
			Ext.Ajax.request({
				url : base_path+'/wpt_WptOrder_accept?pkey='+selection.get("bean.pkey")+'&rowVersions='+selection.get("bean.rowVersion"),
				success : function (response, options) {
					var result = Ext.decode(response.responseText);
					if (result.success){
						var bean = Ext.create('mvc.model.wpt.WptOrder', result);
						Ext.apply(selection.data,bean.data);
						selection.commit();
						me.mdMainTable.getSelectionModel().deselectAll();
						me.mdMainTable.getView().select(selection);
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
	);
},
onDeposit : function() {
	var me = this;
	var selection = this.mdMainTable.getView().getSelectionModel().getSelection()[0];
	Ext.MessageBox.confirm(msg_confirm_title, "请确认是否已收取定金"+selection.get("bean.deposit")+" 元", 
		function(btn) {
			if (btn != 'yes')
				return;
			Ext.Ajax.request({
				url : base_path+'/wpt_WptOrder_deposit?pkey='+selection.get("bean.pkey")+'&rowVersions='+selection.get("bean.rowVersion"),
				success : function (response, options) {
					var result = Ext.decode(response.responseText);
					if (result.success){
						var bean = Ext.create('mvc.model.wpt.WptOrder', result.items);
						Ext.apply(selection.data,bean.data);
						selection.commit();
						me.mdMainTable.getSelectionModel().deselectAll();
						me.mdMainTable.getView().select(selection);
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
	);
},
onResidue : function() {
	var me = this;
	var selection = this.mdMainTable.getView().getSelectionModel().getSelection()[0];
	Ext.MessageBox.confirm(msg_confirm_title, "请确认是否已收取余款 "+selection.get("bean.residue")+" 元", 
		function(btn) {
			if (btn != 'yes')
				return;
			Ext.Ajax.request({
				url : base_path+'/wpt_WptOrder_residue?pkey='+selection.get("bean.pkey")+'&rowVersions='+selection.get("bean.rowVersion"),
				success : function (response, options) {
					var result = Ext.decode(response.responseText);
					if (result.success){
						var bean = Ext.create('mvc.model.wpt.WptOrder', result.items);
						Ext.apply(selection.data,bean.data);
						selection.commit();
						me.mdMainTable.getSelectionModel().deselectAll();
						me.mdMainTable.getView().select(selection);
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
	);
},
onAgreeRefund : function() {
	var me = this;
	var selection = this.mdMainTable.getView().getSelectionModel().getSelection()[0];
	Ext.MessageBox.confirm(msg_confirm_title, "确认退款吗？", 
			function(btn) {
				if (btn != 'yes')
					return;
				Ext.Ajax.request({
					url : base_path+'/wpt_WptOrder_agreeRefund?pkey='+selection.get("bean.pkey")+'&rowVersions='+selection.get("bean.rowVersion"),
					success : function (response, options) {
						var result = Ext.decode(response.responseText);
						if (result.success){
							var bean = Ext.create('mvc.model.wpt.WptOrder', result.items);
							Ext.apply(selection.data,bean.data);
							selection.commit();
							me.mdMainTable.getSelectionModel().deselectAll();
							me.mdMainTable.getView().select(selection);
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
		);
},
onServiceWin : function(selection) {
	if (selection) {
		var win = Ext.create(
				'mvc.view.wpt.WptOrderService.Win', {
					title : this.title + '>服务',
				});
		win.show();
		win.setActiveRecord(selection);
		win.on('create',this.mdMainTable.onServiceRecord,this.mdMainTable);
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
		var win = Ext.create('mvc.view.wpt.WptOrder.WinSearch',{
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