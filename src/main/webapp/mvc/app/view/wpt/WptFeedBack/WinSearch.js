Ext.define('mvc.view.wpt.WptFeedBack.WinSearch',{
extend : 'Ext.window.Window',
oldId : 'WptFeedBack_searchWin_',
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
			url : base_path+'/wpt_WptFeedBack_list',
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
								text : '内容'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_content',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'content'}
							
						,{
								xtype : 'label',
								text : '联系方式'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_contactWay',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'contactWay'}
							
						,{
								xtype : 'label',
								text : '联系方式类型'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_contactType',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'contactType',
											store : Ext.create('mvc.combo.wpt.WptOContactStatus')
										})
							
						,{
								xtype : 'label',
								text : '处理人'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_handleMan',
											width : 80,
											value : 3,
											store : outstore
										})
							,{
								xtype : 'beantrigger',
								name : 'handleMan',
								bean : 'SysUser',
								beanType : 'sys',
								emptyText : form_empty_text
							}
						,{
								xtype : 'label',
								text : '处理时间'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_handleTime',
											width : 80,
											value : 7,
											store : datestore,
											listeners : {
												scope : this,
												change : function(field,newv,oldv,opts){
this.onBetween(newv,this.oldId + 'handleTime',this.oldId + 'handleTimeEd',this.oldId + 'handleTime_empty');this.doLayout();											}
											}
										})
							,{
									xtype : 'datefield',
									name : 'handleTime',
									format : 'Y-m-d H:i:s',
									itemId : this.oldId + 'handleTime'
								},{
									xtype : 'datefieldexp',
									name : 'handleTimeEd',
									format : 'Y-m-d',
									hidden : true,
									itemId : this.oldId + 'handleTimeEd'
								}
							
						,{
								xtype : 'label',
								text : '已处理'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_isHandle',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'isHandle',
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