Ext.define('mvc.view.sys.SysCom.WinSearch',{
extend : 'Ext.window.Window',
oldId : 'SysCom_searchWin_',
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
			url : base_path+'/sys_SysCom_list',
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
								text : '机构名称'
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
								text : '机构简称'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_shortName',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'shortName'}
							
						,{
								xtype : 'label',
								text : '电话1'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_tel1',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'tel1'}
							
						,{
								xtype : 'label',
								text : '电话2'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_tel2',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'tel2'}
							
						,{
								xtype : 'label',
								text : '传真'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_fax',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'fax'}
							
						,{
								xtype : 'label',
								text : '网址'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_website',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'website'}
							
						,{
								xtype : 'label',
								text : '地址'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_addr',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'addr'}
							
						,{
								xtype : 'label',
								text : '邮编'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_zipCode',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'zipCode'}
							
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
							text : '',
							colspan : 3
						}]
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