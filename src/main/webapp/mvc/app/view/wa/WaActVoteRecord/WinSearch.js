Ext.define('mvc.view.wa.WaActVoteRecord.WinSearch',{
extend : 'Ext.window.Window',
oldId : 'WaActVoteRecord_searchWin_',
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
			url : base_path+'/wa_WaActVoteRecord_list',
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
											name : 'optcht_act',
											width : 80,
											value : 3,
											store : outstore
										})
							,{
								xtype : 'beantrigger',
								name : 'act',
								bean : 'WaActVote',
								beanType : 'wa',
								emptyText : form_empty_text
							}
						,{
								xtype : 'label',
								text : '参赛者'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_entryRecord',
											width : 80,
											value : 3,
											store : outstore
										})
							,{
								xtype : 'beantrigger',
								name : 'entryRecord',
								bean : 'WaActVoteEntry',
								beanType : 'wa',
								emptyText : form_empty_text
							}
						,{
								xtype : 'label',
								text : '投票者'
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
								text : '投票时间'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_voteTime',
											width : 80,
											value : 7,
											store : datestore,
											listeners : {
												scope : this,
												change : function(field,newv,oldv,opts){
this.onBetween(newv,this.oldId + 'voteTime',this.oldId + 'voteTimeEd',this.oldId + 'voteTime_empty');this.doLayout();											}
											}
										})
							,{
									xtype : 'datefield',
									name : 'voteTime',
									format : 'Y-m-d H:i:s',
									itemId : this.oldId + 'voteTime'
								},{
									xtype : 'datefieldexp',
									name : 'voteTimeEd',
									format : 'Y-m-d',
									hidden : true,
									itemId : this.oldId + 'voteTimeEd'
								}
							
						,{
								xtype : 'label',
								text : 'IP地址'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_ip',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'ip'}
							
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