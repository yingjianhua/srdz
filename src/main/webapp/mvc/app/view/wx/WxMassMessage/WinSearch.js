Ext.define('mvc.view.wx.WxMassMessage.WinSearch',{
extend : 'Ext.window.Window',
oldId : 'WxMassMessage_searchWin_',
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
			url : base_path+'/wx_WxMassMessage_list',
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
								text : '群发消息类型'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_massmsgType',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'massmsgType',
											store : Ext.create('mvc.combo.wx.WxOMassMsgType')
										})
							
						,{
								xtype : 'label',
								text : '消息模板'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_template',
											width : 80
										})
							,{xtype : 'beantrigger',name : 'template'}
							
						,{
								xtype : 'label',
								text : '向全部用户'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_isToAll',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'isToAll',
											store : Ext.create('mvc.combo.sys.SysOYn')
										})
							
						,{
								xtype : 'label',
								text : '群发分组'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_userGroup',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'userGroup'}
							
						,{
								xtype : 'label',
								text : '发送时间'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_createdTime',
											width : 80,
											value : 7,
											store : datestore,
											listeners : {
												scope : this,
												change : function(field,newv,oldv,opts){
this.onBetween(newv,this.oldId + 'createdTime',this.oldId + 'createdTimeEd',this.oldId + 'createdTime_empty');this.doLayout();											}
											}
										})
							,{
									xtype : 'datefield',
									name : 'createdTime',
									format : 'Y-m-d H:i:s',
									itemId : this.oldId + 'createdTime'
								},{
									xtype : 'datefieldexp',
									name : 'createdTimeEd',
									format : 'Y-m-d',
									hidden : true,
									itemId : this.oldId + 'createdTimeEd'
								}
							
						,{
								xtype : 'label',
								text : '完成时间'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_completeTime',
											width : 80,
											value : 7,
											store : datestore,
											listeners : {
												scope : this,
												change : function(field,newv,oldv,opts){
this.onBetween(newv,this.oldId + 'completeTime',this.oldId + 'completeTimeEd',this.oldId + 'completeTime_empty');this.doLayout();											}
											}
										})
							,{
									xtype : 'datefield',
									name : 'completeTime',
									format : 'Y-m-d H:i:s',
									itemId : this.oldId + 'completeTime'
								},{
									xtype : 'datefieldexp',
									name : 'completeTimeEd',
									format : 'Y-m-d',
									hidden : true,
									itemId : this.oldId + 'completeTimeEd'
								}
							
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
								text : '消息ID'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_msgId',
											width : 80,
											value : 3,
											store : numstore
										})
							,{xtype : 'numberfield',name : 'msgId',allowDecimals : false}
							
						,{
								xtype : 'label',
								text : '数据ID'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_msgDataId',
											width : 80,
											value : 3,
											store : numstore
										})
							,{xtype : 'numberfield',name : 'msgDataId',allowDecimals : false}
							
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