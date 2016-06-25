Ext.define('mvc.view.wpt.WptRestaurant.WinSearch',{
extend : 'Ext.window.Window',
oldId : 'WptRestaurant_searchWin_',
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
			url : base_path+'/wpt_WptRestaurant_list',
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
								text : '餐厅名称'
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
								text : '电话'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_mobile',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'mobile'}
							
						,{
								xtype : 'label',
								text : '店长电话'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_manager',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'manager'}
							
						,{
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
								text : '区'
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
								text : '经纬度'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_coordinate',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'coordinate'}
							
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
								text : '显示图片'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_display',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'display',
											store : Ext.create('mvc.combo.sys.SysOYn')
										})
							
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
								text : '开始营业时间'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_startdate',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'startdate'}
							
						,{
								xtype : 'label',
								text : '结束营业时间'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_stopdate',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'stopdate'}
							
						,{
								xtype : 'label',
								text : '人均消费'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_consumption',
											width : 80,
											value : 3,
											store : numstore
										})
							,{xtype : 'numberfield',name : 'consumption',decimalPrecision : 2}
							
						,{
								xtype : 'label',
								text : 'wifi账号'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_wifiaccount',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'wifiaccount'}
							
						,{
								xtype : 'label',
								text : 'wifi密码'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_wifipassword',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'wifipassword'}
							
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