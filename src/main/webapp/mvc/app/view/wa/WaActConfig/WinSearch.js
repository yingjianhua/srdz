Ext.define('mvc.view.wa.WaActConfig.WinSearch',{
extend : 'Ext.window.Window',
oldId : 'WaActConfig_searchWin_',
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
			url : base_path+'/wa_WaActConfig_list',
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
											name : 'optcht_name',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'name'}
							
						,{
								xtype : 'label',
								text : '活动周期'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_cycle',
											width : 80,
											value : 3,
											store : numstore
										})
							,{xtype : 'numberfield',name : 'cycle',allowDecimals : false}
							
						,{
								xtype : 'label',
								text : '单位'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_unit',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'unit',
											store : Ext.create('mvc.combo.wa.WaOActUnit')
										})
							
						,{
								xtype : 'label',
								text : '周期参与数'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_cycleLimit',
											width : 80,
											value : 3,
											store : numstore
										})
							,{xtype : 'numberfield',name : 'cycleLimit',allowDecimals : false}
							
						,{
								xtype : 'label',
								text : '周期参与数提示语'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_cycleLimitWords',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'cycleLimitWords'}
							
						,{
								xtype : 'label',
								text : '同IP周期参与数'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_ipLimit',
											width : 80,
											value : 3,
											store : numstore
										})
							,{xtype : 'numberfield',name : 'ipLimit',allowDecimals : false}
							
						,{
								xtype : 'label',
								text : '同IP周期参与数提示语'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_ipLimitWords',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'ipLimitWords'}
							
						,{
								xtype : 'label',
								text : '浏览量系数'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_viewRate',
											width : 80,
											value : 3,
											store : numstore
										})
							,{xtype : 'numberfield',name : 'viewRate',allowDecimals : false}
							
						,{
								xtype : 'label',
								text : '报名数系数'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_entryRate',
											width : 80,
											value : 3,
											store : numstore
										})
							,{xtype : 'numberfield',name : 'entryRate',allowDecimals : false}
							
						,{
								xtype : 'label',
								text : '参与数系数'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_actRate',
											width : 80,
											value : 3,
											store : numstore
										})
							,{xtype : 'numberfield',name : 'actRate',allowDecimals : false}
							
						,{
								xtype : 'label',
								text : '图片限制'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_imageShape',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'imageShape',
											store : Ext.create('mvc.combo.wa.WaOActImageShape')
										})
							
						,{
								xtype : 'label',
								text : '自定义菜单'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_customMenu',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'customMenu',
											store : Ext.create('mvc.combo.sys.SysOYn')
										})
							
						,{
								xtype : 'label',
								text : '限制区域'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_resArea',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'resArea',
											store : Ext.create('mvc.combo.sys.SysOYn')
										})
							
						,{
								xtype : 'label',
								text : '限制区域异常提示'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_resAreaWords',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'resAreaWords'}
							
						,{
								xtype : 'label',
								text : '有效区域'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_area',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'area'}
							
						,{
								xtype : 'label',
								text : '无效区域'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_invalidArea',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'invalidArea'}
							
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