Ext.define('mvc.view.sys.SysEm.WinSearch',{
extend : 'Ext.window.Window',
oldId : 'SysEm_searchWin_',
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
			url : base_path+'/sys_SysEm_list',
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
								text : '工号'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_code',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'code'}
							
						,{
								xtype : 'label',
								text : '名称'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_name',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'name'}
							
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
								text : '职员状态'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_state',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'state',
											store : Ext.create('mvc.combo.sys.SysOState')
										})
							
						,{
								xtype : 'label',
								text : '机构'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_org',
											width : 80,
											value : 3,
											store : outstore
										})
							,
								mvc.Tools.crtComboTrigger(true,'sys_SysOrg','',{
											name : 'org'
										})
							
						,{
								xtype : 'label',
								text : '部门'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_dept',
											width : 80,
											value : 3,
											store : outstore
										})
							,{
								xtype : 'beantrigger',
								name : 'dept',
								bean : 'SysDept',
								beanType : 'sys',
								emptyText : form_empty_text
							}
						,{
								xtype : 'label',
								text : '个人证件类型'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_peCardType',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'peCardType',
											store : Ext.create('mvc.combo.sys.SysOCardPersonType')
										})
							
						,{
								xtype : 'label',
								text : '个人证件号码'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_peCardNumb',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'peCardNumb'}
							
						,{
								xtype : 'label',
								text : '个人常用手机'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_peMobile',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'peMobile'}
							
						,{
								xtype : 'label',
								text : '个人邮箱'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_peEmail',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'peEmail'}
							
						,{
								xtype : 'label',
								text : '个人微信'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_peWx',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'peWx'}
							
						,{
								xtype : 'label',
								text : '个人QQ'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_peQq',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'peQq'}
							
						,{
								xtype : 'label',
								text : '个人性别'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_peSex',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'peSex',
											store : Ext.create('mvc.combo.sys.SysOSex')
										})
							
						,{
								xtype : 'label',
								text : '个人出生日期'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_peBirthday',
											width : 80,
											value : 7,
											store : datestore,
											listeners : {
												scope : this,
												change : function(field,newv,oldv,opts){
this.onBetween(newv,this.oldId + 'peBirthday',this.oldId + 'peBirthdayEd',this.oldId + 'peBirthday_empty');this.doLayout();											}
											}
										})
							,{
									xtype : 'datefield',
									name : 'peBirthday',
									format : 'Y-m-d',
									itemId : this.oldId + 'peBirthday'
								},{
									xtype : 'datefieldexp',
									name : 'peBirthdayEd',
									format : 'Y-m-d',
									hidden : true,
									itemId : this.oldId + 'peBirthdayEd'
								}
							
						,{
								xtype : 'label',
								text : '个人婚姻状况'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_peMerry',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'peMerry',
											store : Ext.create('mvc.combo.sys.SysOMerry')
										})
							
						,{
								xtype : 'label',
								text : '家庭电话'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_hoTel',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'hoTel'}
							
						,{
								xtype : 'label',
								text : '家庭地址'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_hoAddr',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'hoAddr'}
							
						,{
								xtype : 'label',
								text : '家庭邮编'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_hoZipCode',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'hoZipCode'}
							
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
								text : '更新员'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_updatedBy',
											width : 80,
											value : 3,
											store : outstore
										})
							,{
								xtype : 'beantrigger',
								name : 'updatedBy',
								bean : 'SysUser',
								beanType : 'sys',
								emptyText : form_empty_text
							}
						,{
								xtype : 'label',
								text : '更新时间'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_updatedDateTime',
											width : 80,
											value : 7,
											store : datestore,
											listeners : {
												scope : this,
												change : function(field,newv,oldv,opts){
this.onBetween(newv,this.oldId + 'updatedDateTime',this.oldId + 'updatedDateTimeEd',this.oldId + 'updatedDateTime_empty');this.doLayout();											}
											}
										})
							,{
									xtype : 'datefield',
									name : 'updatedDateTime',
									format : 'Y-m-d H:i:s',
									itemId : this.oldId + 'updatedDateTime'
								},{
									xtype : 'datefieldexp',
									name : 'updatedDateTimeEd',
									format : 'Y-m-d',
									hidden : true,
									itemId : this.oldId + 'updatedDateTimeEd'
								}
							
						,{
								xtype : 'label',
								text : '建档员'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_createdBy',
											width : 80,
											value : 3,
											store : outstore
										})
							,{
								xtype : 'beantrigger',
								name : 'createdBy',
								bean : 'SysUser',
								beanType : 'sys',
								emptyText : form_empty_text
							}
						,{
								xtype : 'label',
								text : '建档时间'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_createdDateTime',
											width : 80,
											value : 7,
											store : datestore,
											listeners : {
												scope : this,
												change : function(field,newv,oldv,opts){
this.onBetween(newv,this.oldId + 'createdDateTime',this.oldId + 'createdDateTimeEd',this.oldId + 'createdDateTime_empty');this.doLayout();											}
											}
										})
							,{
									xtype : 'datefield',
									name : 'createdDateTime',
									format : 'Y-m-d H:i:s',
									itemId : this.oldId + 'createdDateTime'
								},{
									xtype : 'datefieldexp',
									name : 'createdDateTimeEd',
									format : 'Y-m-d',
									hidden : true,
									itemId : this.oldId + 'createdDateTimeEd'
								}
							
						,{
								xtype : 'label',
								text : '登录账号'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_loginName',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'loginName'}
							
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