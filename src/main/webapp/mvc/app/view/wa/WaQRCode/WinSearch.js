Ext.define('mvc.view.wa.WaQRCode.WinSearch',{
extend : 'Ext.window.Window',
oldId : 'WaQRCode_searchWin_',
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
			url : base_path+'/wa_WaQRCode_list',
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
								text : '二维码类型'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_type',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'type',
											store : Ext.create('mvc.combo.wx.WxOQRCodeType')
										})
							
						,{
								xtype : 'label',
								text : '场景值'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_sceneKey',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'sceneKey'}
							
						,{
								xtype : 'label',
								text : '有效天数'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_validTerm',
											width : 80,
											value : 3,
											store : numstore
										})
							,{xtype : 'numberfield',name : 'validTerm',decimalPrecision : 2}
							
						,{
								xtype : 'label',
								text : '二维码图片'
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
								text : '二维码详情'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_summary',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'summary'}
							
						,{
								xtype : 'label',
								text : '解析地址'
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
								text : '到期时间'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_expireTime',
											width : 80,
											value : 7,
											store : datestore,
											listeners : {
												scope : this,
												change : function(field,newv,oldv,opts){
this.onBetween(newv,this.oldId + 'expireTime',this.oldId + 'expireTimeEd',this.oldId + 'expireTime_empty');this.doLayout();											}
											}
										})
							,{
									xtype : 'datefield',
									name : 'expireTime',
									format : 'Y-m-d H:i:s',
									itemId : this.oldId + 'expireTime'
								},{
									xtype : 'datefieldexp',
									name : 'expireTimeEd',
									format : 'Y-m-d',
									hidden : true,
									itemId : this.oldId + 'expireTimeEd'
								}
							
						,{
								xtype : 'label',
								text : '公众帐号'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_account',
											width : 80,
											value : 3,
											store : outstore
										})
							,
								mvc.Tools.crtComboTrigger(true,'wx_WxAccount','',{
											name : 'account'
										})
							
						,{
								xtype : 'label',
								text : '创建时间'
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