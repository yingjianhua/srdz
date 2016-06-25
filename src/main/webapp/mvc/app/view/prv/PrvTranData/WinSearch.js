Ext.define('mvc.view.prv.PrvTranData.WinSearch',{
extend : 'Ext.window.Window',
oldId : 'PrvTranData_searchWin_',
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
			url : base_path+'/prv_PrvTranData_list',
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
								text : '交易名称'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_tranName',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'tranName'}
							
						,{
								xtype : 'label',
								text : '是否为表单'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_isForm',
											width : 80,
											value : 3,
											store : numstore
										})
							,mvc.Tools.crtComboForm(true,{
											name : 'isForm',
											store : Ext.create('mvc.combo.sys.SysOYn')
										})
							
						,{
								xtype : 'label',
								text : '所属资源控制组'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_grp',
											width : 80,
											value : 3,
											store : outstore
										})
							,
								mvc.Tools.crtComboTrigger(true,'prv_PrvTranGrp','',{
											name : 'grp'
										})
							
						,{
								xtype : 'label',
								text : '用户字段名称'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_userName',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'userName'}
							
						,{
								xtype : 'label',
								text : '部门字段名称'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_deptName',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'deptName'}
							
						,{
								xtype : 'label',
								text : '核算单元字段名称'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_cellName',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'cellName'}
							
						,{
								xtype : 'label',
								text : '机构字段名称'
							},
								mvc.Tools.crtComboBase(false,{
											name : 'optcht_orgName',
											width : 80,
											value : 1,
											store : strstore
										})
							,{xtype : 'textfield',name : 'orgName'}
							
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