Ext.define('mvc.view.wa.WaActVoteEntry.WinSearch',{
extend : 'Ext.window.Window',
oldId : 'WaActVoteEntry_searchWin_',
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
			url : base_path+'/wa_WaActVoteEntry_list',
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
								text : '活动'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_vote',
											width : 80,
											value : 3,
											store : outstore
										})
							,{
								xtype : 'beantrigger',
								name : 'vote',
								bean : 'WaActVote',
								beanType : 'wa',
								emptyText : form_empty_text
							}
						,{
								xtype : 'label',
								text : '参赛者'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_wxUser',
											width : 80,
											value : 3,
											store : outstore
										})
							,{
								xtype : 'beantrigger',
								name : 'wxUser',
								bean : 'WxUser',
								beanType : 'wx',
								emptyText : form_empty_text
							}
						,{
								xtype : 'label',
								text : '编号'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_number',
											width : 80,
											value : 3,
											store : numstore
										})
							,{xtype : 'numberfield',name : 'number',allowDecimals : false}
							
						,{
								xtype : 'label',
								text : '姓名'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_namePerson',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'namePerson'}
							
						,{
								xtype : 'label',
								text : '电话'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_phonePerson',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'phonePerson'}
							
						,{
								xtype : 'label',
								text : '描述'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_des',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'des'}
							
						,{
								xtype : 'label',
								text : '报名时间'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_entryTime',
											width : 80,
											value : 7,
											store : datestore,
											listeners : {
												scope : this,
												change : function(field,newv,oldv,opts){
this.onBetween(newv,this.oldId + 'entryTime',this.oldId + 'entryTimeEd',this.oldId + 'entryTime_empty');this.doLayout();											}
											}
										})
							,{
									xtype : 'datefield',
									name : 'entryTime',
									format : 'Y-m-d H:i:s',
									itemId : this.oldId + 'entryTime'
								},{
									xtype : 'datefieldexp',
									name : 'entryTimeEd',
									format : 'Y-m-d',
									hidden : true,
									itemId : this.oldId + 'entryTimeEd'
								}
							
						,{
								xtype : 'label',
								text : '投票数'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_voteCount',
											width : 80,
											value : 3,
											store : numstore
										})
							,{xtype : 'numberfield',name : 'voteCount',allowDecimals : false}
							
						,{
								xtype : 'label',
								text : '报名状态'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_status',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'status',
											store : Ext.create('mvc.combo.wa.WaOActEntryStatus')
										})
							
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