Ext.define('mvc.view.wpt.WptOrder.WinSearch',{
extend : 'Ext.window.Window',
oldId : 'WptOrder_searchWin_',
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
			url : base_path+'/wpt_WptOrder_list',
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
								text : '订单号'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_orderid',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'orderid'}
							
						,{
								xtype : 'label',
								text : '定金订单号'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_depPayId',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'depPayId'}
							
						,{
								xtype : 'label',
								text : '退款单号'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_outRefundNo',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'outRefundNo'}
							
						,{
								xtype : 'label',
								text : '关注用户'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_wxuser',
											width : 80,
											value : 3,
											store : outstore
										})
							,{
								xtype : 'beantrigger',
								name : 'wxuser',
								bean : 'WxUser',
								beanType : 'wx',
								emptyText : form_empty_text
							}
						,{
								xtype : 'label',
								text : '餐厅'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_restaurant',
											width : 80,
											value : 3,
											store : outstore
										})
							,{
								xtype : 'beantrigger',
								name : 'restaurant',
								bean : 'WptRestaurant',
								beanType : 'wpt',
								emptyText : form_empty_text
							}
						,{
								xtype : 'label',
								text : '宴会类型'
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
								text : '用餐时间'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_time',
											width : 80,
											value : 7,
											store : datestore,
											listeners : {
												scope : this,
												change : function(field,newv,oldv,opts){
this.onBetween(newv,this.oldId + 'time',this.oldId + 'timeEd',this.oldId + 'time_empty');this.doLayout();											}
											}
										})
							,{
									xtype : 'datefield',
									name : 'time',
									format : 'Y-m-d H:i:s',
									itemId : this.oldId + 'time'
								},{
									xtype : 'datefieldexp',
									name : 'timeEd',
									format : 'Y-m-d',
									hidden : true,
									itemId : this.oldId + 'timeEd'
								}
							
						,{
								xtype : 'label',
								text : '宴会人数'
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
								text : '人均预算'
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
								text : '创建时间'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_createTime',
											width : 80,
											value : 7,
											store : datestore,
											listeners : {
												scope : this,
												change : function(field,newv,oldv,opts){
this.onBetween(newv,this.oldId + 'createTime',this.oldId + 'createTimeEd',this.oldId + 'createTime_empty');this.doLayout();											}
											}
										})
							,{
									xtype : 'datefield',
									name : 'createTime',
									format : 'Y-m-d H:i:s',
									itemId : this.oldId + 'createTime'
								},{
									xtype : 'datefieldexp',
									name : 'createTimeEd',
									format : 'Y-m-d',
									hidden : true,
									itemId : this.oldId + 'createTimeEd'
								}
							
						,{
								xtype : 'label',
								text : '订单状态'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_status',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'status',
											store : Ext.create('mvc.combo.wpt.WptOStatus')
										})
							
						,{
								xtype : 'label',
								text : '定金'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_deposit',
											width : 80,
											value : 3,
											store : numstore
										})
							,{xtype : 'numberfield',name : 'deposit',decimalPrecision : 2}
							
						,{
								xtype : 'label',
								text : '余款'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_residue',
											width : 80,
											value : 3,
											store : numstore
										})
							,{xtype : 'numberfield',name : 'residue',decimalPrecision : 2}
							
						,{
								xtype : 'label',
								text : '联系人'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_contactMan',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'contactMan'}
							
						,{
								xtype : 'label',
								text : '性别'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_contactSex',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'contactSex',
											store : Ext.create('mvc.combo.sys.SysOSex')
										})
							
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
								text : '套餐名称'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_comboName',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'comboName'}
							
						,{
								xtype : 'label',
								text : '金额'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_price',
											width : 80,
											value : 3,
											store : numstore
										})
							,{xtype : 'numberfield',name : 'price',decimalPrecision : 2}
							
						,{
								xtype : 'label',
								text : '是否私人定制 '
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_isPt',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'isPt',
											store : Ext.create('mvc.combo.sys.SysOYn')
										})
							
						,{
								xtype : 'label',
								text : '核验码'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_checkcode',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'checkcode'}
							
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