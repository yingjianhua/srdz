Ext.define('mvc.view.wm.WmNews.WinSearch',{
extend : 'Ext.window.Window',
oldId : 'WmNews_searchWin_',
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
			url : base_path+'/wm_WmNews_list',
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
								text : '封面'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_imageUrl',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'imageUrl'}
							
						,{
								xtype : 'label',
								text : '是否显示封面'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_showCoverPic',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'showCoverPic',
											store : Ext.create('mvc.combo.sys.SysOYn')
										})
							
						,{
								xtype : 'label',
								text : '作者'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_author',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'author'}
							
						,{
								xtype : 'label',
								text : '摘要'
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
								text : '原文链接'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_relUrl',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'relUrl'}
							
						,{
								xtype : 'label',
								text : '图文类型'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_type',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'type',
											store : Ext.create('mvc.combo.wm.WmONewsType')
										})
							
						,{
								xtype : 'label',
								text : '扩展链接'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_exp',
											width : 80
										})
							,{xtype : 'beantrigger',name : 'exp'}
							
						,{
								xtype : 'label',
								text : '自定义链接'
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
								text : '排序号'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_sort',
											width : 80,
											value : 3,
											store : numstore
										})
							,{xtype : 'numberfield',name : 'sort',allowDecimals : false}
							
						,{
								xtype : 'label',
								text : '图文ID'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_mediaId',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'mediaId'}
							
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