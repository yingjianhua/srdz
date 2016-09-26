Ext.define('mvc.view.wpt.WptHeadline.WinSearch',{
extend : 'Ext.window.Window',
oldId : 'WptHeadline_searchWin_',
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
			url : base_path+'/wpt_WptHeadline_list',
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
								text : '城市'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_city',
											width : 80,
											value : 3,
											store : outstore
										})
							,{
								xtype : 'beantrigger',
								name : 'city',
								bean : 'WptCity',
								beanType : 'wpt',
								emptyText : form_empty_text
							}
						,{
								xtype : 'label',
								text : '区域'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_cityline',
											width : 80,
											value : 3,
											store : outstore
										})
							,
								mvc.Tools.crtComboTrigger(true,'wpt_WptCityLine','',{
											name : 'cityline'
										})
							
						,{
								xtype : 'label',
								text : '主题'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_banquet',
											width : 80,
											value : 3,
											store : outstore
										})
							,{
								xtype : 'beantrigger',
								name : 'banquet',
								bean : 'WptBanquet',
								beanType : 'wpt',
								emptyText : form_empty_text
							}
						,{
								xtype : 'label',
								text : '标题'
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
								text : '正文'
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
								text : '图片'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_imgUrl',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'imgUrl'}
							
						,{
								xtype : 'label',
								text : '时间'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_date',
											width : 80,
											value : 7,
											store : datestore,
											listeners : {
												scope : this,
												change : function(field,newv,oldv,opts){
this.onBetween(newv,this.oldId + 'date',this.oldId + 'dateEd',this.oldId + 'date_empty');this.doLayout();											}
											}
										})
							,{
									xtype : 'datefield',
									name : 'date',
									format : 'Y-m-d',
									itemId : this.oldId + 'date'
								},{
									xtype : 'datefieldexp',
									name : 'dateEd',
									format : 'Y-m-d',
									hidden : true,
									itemId : this.oldId + 'dateEd'
								}
							
						,{
								xtype : 'label',
								text : '链接'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_url',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'url'}
							
						,{
								xtype : 'label',
								text : '置顶'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_top',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'top',
											store : Ext.create('mvc.combo.sys.SysOYn')
										})
							
						,{
								xtype : 'label',
								text : '排序'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_sort',
											width : 80,
											value : 3,
											store : numstore
										})
							,{xtype : 'numberfield',name : 'sort',allowDecimals : false}
							
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