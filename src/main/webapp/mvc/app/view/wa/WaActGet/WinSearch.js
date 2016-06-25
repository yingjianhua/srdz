Ext.define('mvc.view.wa.WaActGet.WinSearch',{
extend : 'Ext.window.Window',
oldId : 'WaActGet_searchWin_',
width : 680,
layout : 'fit',
resizable : true,
iconCls : 'app-icon',
listCmp : null,
initComponent : function(){
	var strstore = Ext.create('mvc.combo.sys.SysOOptCht');
	var datestore = Ext.create('mvc.combo.sys.SysOOptCht');
	var numstore = Ext.create('mvc.combo.sys.SysOOptCht');
	var outstore = Ext.create('mvc.combo.sys.SysOOptCht');
	strstore.filter('value', new RegExp('^([1-4|9]|[1][0])$'));
	datestore.filter('value', new RegExp('^([3-9]|[1][0-1])$'));
	numstore.filter('value', new RegExp('^([3-9]|[1][0])$'));
	outstore.filter('value', new RegExp('^([3|4|9]|[1][0])$'));
	this.items ={
	anchor : '100%',
	plain : true,
	items : [{
			xtype : 'panel',
			layout : 'form',
			border : false,
			frame : false,
			bodyPadding : '5 5 5 5',
			url : base_path+'/wa_WaActGet_list',
			fieldDefaults : {
				labelWidth : 100,
				labelStyle : 'font-weight : bold'
			},
			items : [{
					xtype : 'form',
					border : false,
					layout : {
						type : 'table',
						columns : 6,
						itemCls : 'x-layout-table-items-form'
					},
					items : [{
								xtype : 'label',
								text : '抽奖活动'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_act',
											width : 80,
											value : 3,
											store : outstore
										})
							,
								mvc.Tools.crtComboTrigger(true,'wa_WaAct','',{
											name : 'act'
										})
							
						,{
								xtype : 'label',
								text : '收货人名称'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_recName',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'recName'}
							
						,{
								xtype : 'label',
								text : '收货人地址'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_recAddr',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'recAddr'}
							
						,{
								xtype : 'label',
								text : '手机号码'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_recMobile',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'recMobile'}
							
						,{
								xtype : 'label',
								text : '奖项设置'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_actItem',
											width : 80,
											value : 3,
											store : outstore
										})
							,
								mvc.Tools.crtComboTrigger(true,'wa_WaActItem','',{
											name : 'actItem'
										})
							
						,{
								xtype : 'label',
								text : '奖品设置'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_actPrize',
											width : 80,
											value : 3,
											store : outstore
										})
							,
								mvc.Tools.crtComboTrigger(true,'wa_WaActPrize','',{
											name : 'actPrize'
										})
							
						,{
								xtype : 'label',
								text : '抽奖时间'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_actTime',
											width : 80,
											value : 7,
											store : datestore,
											listeners : {
												scope : this,
												change : function(field,newv,oldv,opts){
this.onBetween(newv,this.oldId + 'actTime',this.oldId + 'actTimeEd',this.oldId + 'actTime_empty');this.doLayout();											}
											}
										})
							,{
									xtype : 'datefield',
									name : 'actTime',
									format : 'Y-m-d H:i:s',
									itemId : this.oldId + 'actTime'
								},{
									xtype : 'datefieldexp',
									name : 'actTimeEd',
									format : 'Y-m-d',
									hidden : true,
									itemId : this.oldId + 'actTimeEd'
								}
							
						,{
								xtype : 'label',
								text : '关注用户'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_wxUser',
											width : 80,
											value : 3,
											store : outstore
										})
							,
								mvc.Tools.crtComboTrigger(true,'wx_WxUser','',{
											name : 'wxUser'
										})
							
						,{
								xtype : 'label',
								text : '身份证图片'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_wxCard',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'wxCard'}
							
						,{
								xtype : 'label',
								text : '兑奖KEY'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_actKey',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'actKey'}
							
						,{
								xtype : 'label',
								text : '发货状态'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_sendStatus',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'sendStatus',
											store : Ext.create('mvc.combo.wa.WaOActSendStatus')
										})
							
						,{
								xtype : 'label',
								text : '快递名称'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_sendName',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'sendName'}
							
						,{
								xtype : 'label',
								text : '快递编号'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_sendNum',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'sendNum'}
							
						,{
								xtype : 'label',
								text : '快递时间'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_sendTime',
											width : 80,
											value : 7,
											store : datestore,
											listeners : {
												scope : this,
												change : function(field,newv,oldv,opts){
this.onBetween(newv,this.oldId + 'sendTime',this.oldId + 'sendTimeEd',this.oldId + 'sendTime_empty');this.doLayout();											}
											}
										})
							,{
									xtype : 'datefield',
									name : 'sendTime',
									format : 'Y-m-d H:i:s',
									itemId : this.oldId + 'sendTime'
								},{
									xtype : 'datefieldexp',
									name : 'sendTimeEd',
									format : 'Y-m-d',
									hidden : true,
									itemId : this.oldId + 'sendTimeEd'
								}
							
						]
				}]
		}]
};
	this.buttonAlign = 'right',
	this.buttons =[{
		text : '重置',
		scope : this,
		iconCls : 'win-refresh-icon',
		handler : this.onReset
	},{
		text : '关闭',
		scope : this,
		iconCls : 'win-close-icon',
		handler : this.onClose
	},{
		text : '搜索',
		scope : this,
		iconCls : 'win-ok-icon',
		handler : this.onSearchAdv
	}];
		this.callParent(arguments);
		this.addEvents('create');
		this.form = this.items.items[0];
},
setActiveRecord : function(record){
		this.form.activeRecord = record;
		if (record || this.form.activeRecord) {
			this.form.getForm().loadRecord(record);
		} else {
			this.form.getForm().reset();
		}
},
onBetween : function(newv,label,dateEd){
	if(newv == 11) {
		this.down('#'+label).hide();
		this.down('#'+label).setValue(null);
		this.down('#'+dateEd).show();
	} else {
		this.down('#'+label).show();
		this.down('#'+dateEd).hide();
		this.down('#'+dateEd).setValue(null);
	}
},
onReset : function(){
		this.setActiveRecord(this.form.activeRecord);
},
onClose : function(){
		this.close();
},
onSearchAdv : function(){
	var array = mvc.Tools.advancedSearchValues(this.down('form'));
	this.listCmp.onSearchDo(array);
	this.close();
}
});