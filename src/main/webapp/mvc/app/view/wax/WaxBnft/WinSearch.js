Ext.define('mvc.view.wax.WaxBnft.WinSearch',{
extend : 'Ext.window.Window',
oldId : 'WaxBnft_searchWin_',
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
			url : base_path+'/wax_WaxBnft_list',
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
								text : '首图'
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
								text : '介绍'
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
								text : '商家'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_bsn',
											width : 80,
											value : 3,
											store : outstore
										})
							,{
								xtype : 'beantrigger',
								name : 'bsn',
								bean : 'WpBsn',
								beanType : 'wp',
								emptyText : form_empty_text
							}
						,{
								xtype : 'label',
								text : '奖品'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_gift',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'gift'}
							
						,{
								xtype : 'label',
								text : '领取时间'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_getDate',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'getDate'}
							
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
											store : Ext.create('mvc.combo.wa.WaOActVoteStatus')
										})
							
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
							,{
								xtype : 'beantrigger',
								name : 'account',
								bean : 'WxAccount',
								beanType : 'wx',
								emptyText : form_empty_text
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