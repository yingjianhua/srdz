Ext.define('mvc.view.wx.WxUser.WinSearch',{
extend : 'Ext.window.Window',
oldId : 'WxUser_searchWin_',
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
			url : base_path+'/wx_WxUser_list',
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
								text : '关注状态'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_status',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'status',
											store : Ext.create('mvc.combo.wx.WxOStatus')
										})
							
						,{
								xtype : 'label',
								text : '粉丝ID'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_openId',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'openId'}
							
						,{
								xtype : 'label',
								text : '唯一ID'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_unionId',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'unionId'}
							
						,{
								xtype : 'label',
								text : '昵称'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_nickname',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'nickname'}
							
						,{
								xtype : 'label',
								text : '性别'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_sex',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'sex',
											store : Ext.create('mvc.combo.sys.SysOSex')
										})
							
						,{
								xtype : 'label',
								text : '城市'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_city',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'city'}
							
						,{
								xtype : 'label',
								text : '省份'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_province',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'province'}
							
						,{
								xtype : 'label',
								text : '国家'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_country',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'country'}
							
						,{
								xtype : 'label',
								text : '头像'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_imageUrl',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'imageUrl'}
							
						,{
								xtype : 'label',
								text : '备注'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_rem',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'rem'}
							
						,{
								xtype : 'label',
								text : '用户分组'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_userGroup',
											width : 80,
											value : 3,
											store : outstore
										})
							,
								mvc.Tools.crtComboTrigger(true,'wx_WxUserGroup','',{
											name : 'userGroup'
										})
							
						,{
								xtype : 'label',
								text : '关注时间'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_subscribeTime',
											width : 80,
											value : 7,
											store : datestore,
											listeners : {
												scope : this,
												change : function(field,newv,oldv,opts){
this.onBetween(newv,this.oldId + 'subscribeTime',this.oldId + 'subscribeTimeEd',this.oldId + 'subscribeTime_empty');this.doLayout();											}
											}
										})
							,{
									xtype : 'datefield',
									name : 'subscribeTime',
									format : 'Y-m-d H:i:s',
									itemId : this.oldId + 'subscribeTime'
								},{
									xtype : 'datefieldexp',
									name : 'subscribeTimeEd',
									format : 'Y-m-d',
									hidden : true,
									itemId : this.oldId + 'subscribeTimeEd'
								}
							
						,{
								xtype : 'label',
								text : '同步时间'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_syncTime',
											width : 80,
											value : 7,
											store : datestore,
											listeners : {
												scope : this,
												change : function(field,newv,oldv,opts){
this.onBetween(newv,this.oldId + 'syncTime',this.oldId + 'syncTimeEd',this.oldId + 'syncTime_empty');this.doLayout();											}
											}
										})
							,{
									xtype : 'datefield',
									name : 'syncTime',
									format : 'Y-m-d H:i:s',
									itemId : this.oldId + 'syncTime'
								},{
									xtype : 'datefieldexp',
									name : 'syncTimeEd',
									format : 'Y-m-d',
									hidden : true,
									itemId : this.oldId + 'syncTimeEd'
								}
							
						,{
								xtype : 'label',
								text : '是否同步'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_syncStatus',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'syncStatus',
											store : Ext.create('mvc.combo.sys.SysOYn')
										})
							
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