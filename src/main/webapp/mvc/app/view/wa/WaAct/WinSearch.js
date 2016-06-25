Ext.define('mvc.view.wa.WaAct.WinSearch',{
extend : 'Ext.window.Window',
oldId : 'WaAct_searchWin_',
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
			url : base_path+'/wa_WaAct_list',
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
								text : '活动名称'
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
								text : '活动描述'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_title',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'title'}
							
						,{
								xtype : 'label',
								text : '抽奖次数'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_doCount',
											width : 80,
											value : 3,
											store : numstore
										})
							,{xtype : 'numberfield',name : 'doCount',allowDecimals : false}
							
						,{
								xtype : 'label',
								text : '开始时间'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_startTime',
											width : 80,
											value : 7,
											store : datestore,
											listeners : {
												scope : this,
												change : function(field,newv,oldv,opts){
this.onBetween(newv,this.oldId + 'startTime',this.oldId + 'startTimeEd',this.oldId + 'startTime_empty');this.doLayout();											}
											}
										})
							,{
									xtype : 'datefield',
									name : 'startTime',
									format : 'Y-m-d H:i:s',
									itemId : this.oldId + 'startTime'
								},{
									xtype : 'datefieldexp',
									name : 'startTimeEd',
									format : 'Y-m-d',
									hidden : true,
									itemId : this.oldId + 'startTimeEd'
								}
							
						,{
								xtype : 'label',
								text : '结束时间'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_endTime',
											width : 80,
											value : 7,
											store : datestore,
											listeners : {
												scope : this,
												change : function(field,newv,oldv,opts){
this.onBetween(newv,this.oldId + 'endTime',this.oldId + 'endTimeEd',this.oldId + 'endTime_empty');this.doLayout();											}
											}
										})
							,{
									xtype : 'datefield',
									name : 'endTime',
									format : 'Y-m-d H:i:s',
									itemId : this.oldId + 'endTime'
								},{
									xtype : 'datefieldexp',
									name : 'endTimeEd',
									format : 'Y-m-d',
									hidden : true,
									itemId : this.oldId + 'endTimeEd'
								}
							
						,{
								xtype : 'label',
								text : '中奖概率(%)'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_rate',
											width : 80,
											value : 3,
											store : numstore
										})
							,{xtype : 'numberfield',name : 'rate',decimalPrecision : 4}
							
						,{
								xtype : 'label',
								text : '抽奖类型'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_type',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'type',
											store : Ext.create('mvc.combo.wa.WaOActDrawType')
										})
							
						,{
								xtype : 'label',
								text : '活动状态'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_status',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'status',
											store : Ext.create('mvc.combo.wa.WaOActStatus')
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
            this.lineTable.store.filter([{'id':'filter','property':'act','value':record.get('bean.pkey')}]);
        } else {
            this.form.getForm().reset();
            this.lineTable.store.removeAll();
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