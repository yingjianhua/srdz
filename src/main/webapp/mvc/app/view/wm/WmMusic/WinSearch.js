Ext.define('mvc.view.wm.WmMusic.WinSearch',{
extend : 'Ext.window.Window',
oldId : 'WmMusic_searchWin_',
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
			url : base_path+'/wm_WmMusic_list',
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
								text : '音乐标题'
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
								text : '音乐描述'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_description',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'description'}
							
						,{
								xtype : 'label',
								text : '音乐'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_musicUrl',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'musicUrl'}
							
						,{
								xtype : 'label',
								text : '高质量音乐'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_hqmusicUrl',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'hqmusicUrl'}
							
						,{
								xtype : 'label',
								text : '缩略图ID'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_thumbMediaId',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'thumbMediaId'}
							
						,{
								xtype : 'label',
								text : '缩略图'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_thumbUrl',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'thumbUrl'}
							
						,{
								xtype : 'label',
								text : '同步状态'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_status',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'status',
											store : Ext.create('mvc.combo.wx.WxOSyncStatus')
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