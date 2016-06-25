Ext.define('mvc.view.wx.WxAccount.WinSearch',{
extend : 'Ext.window.Window',
oldId : 'WxAccount_searchWin_',
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
			url : base_path+'/wx_WxAccount_list',
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
								text : '名称'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_accountName',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'accountName'}
							
						,{
								xtype : 'label',
								text : 'TOKEN'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_accountToken',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'accountToken'}
							
						,{
								xtype : 'label',
								text : '微信号'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_accountNumber',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'accountNumber'}
							
						,{
								xtype : 'label',
								text : '原始ID'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_accountId',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'accountId'}
							
						,{
								xtype : 'label',
								text : '公众号类型'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_accountType',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'accountType',
											store : Ext.create('mvc.combo.wx.WxOAccountType')
										})
							
						,{
								xtype : 'label',
								text : '开放平台'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_openPlat',
											width : 80,
											value : 3,
											store : outstore
										})
							,
								mvc.Tools.crtComboTrigger(true,'wx_WxOpenPlat','',{
											name : 'openPlat'
										})
							
						,{
								xtype : 'label',
								text : '代理服务号'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_agentAccount',
											width : 80,
											value : 3,
											store : outstore
										})
							,{
								xtype : 'beantrigger',
								name : 'agentAccount',
								bean : 'WxAccount',
								beanType : 'wx',
								emptyText : form_empty_text
							}
						,{
								xtype : 'label',
								text : '电子邮箱'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_accountEmail',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'accountEmail'}
							
						,{
								xtype : 'label',
								text : '描述'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_accountDesc',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'accountDesc'}
							
						,{
								xtype : 'label',
								text : 'APPID'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_accountAppid',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'accountAppid'}
							
						,{
								xtype : 'label',
								text : 'APPSECRET'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_accountAppsecret',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'accountAppsecret'}
							
						,{
								xtype : 'label',
								text : '负责人'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_userSys',
											width : 80,
											value : 3,
											store : outstore
										})
							,{
								xtype : 'beantrigger',
								name : 'userSys',
								bean : 'SysUser',
								beanType : 'sys',
								emptyText : form_empty_text
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