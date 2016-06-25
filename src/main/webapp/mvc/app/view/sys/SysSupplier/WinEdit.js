Ext.define('mvc.view.sys.SysSupplier.WinEdit',{
extend : 'Ext.window.Window',
width : 800,
height : 500,
layout : 'border',
resizable : true,
maximizable : true,
modal : true,
iconCls : 'app-icon',
record : null,
oldId : 'SysSupplier_edit_',
isEdit : true,
initComponent : function(){
		var mainPkey = this.record.get('bean.pkey');
this.items =[{
		region : 'north',
		xtype : 'form',
		border : false,
		bodyPadding : '5 5 0 5',
		layout : {
			type : 'table',
			columns : 2,
			itemCls : 'x-layout-table-items'
		},
		fieldDefaults : {
			labelWidth : 120,
			width : 300,
			labelStyle : 'font-weight : bold',
			readOnly : true
		},
		items : [{xtype : 'textfield',fieldLabel : '代码',value : this.record.get('bean.code')}
			,{xtype : 'textfield',fieldLabel : '名称',value : this.record.get('bean.name')}
			,{xtype : 'textfield',fieldLabel : '简称',value : this.record.get('bean.shortName')}
			,
				mvc.Tools.crtComboForm(true,{
							value : this.record.get('bean.comPersonFlag'),
							fieldLabel : '性质',
							store : Ext.create('mvc.combo.sys.SysOComPersonFlag')
						})
			,
				mvc.Tools.crtComboForm(true,{
							value : this.record.get('bean.enabled'),
							fieldLabel : '启用标志',
							store : Ext.create('mvc.combo.sys.SysOEnabled')
						})
			,
				mvc.Tools.crtComboTrigger(true,'sys_SysOrg','',{
							value : this.record.get('bean.mngOrg'),
							fieldLabel : '管理机构'
						})
			,{
				xtype : 'beantrigger',
				value : this.record.get('bean.mngDept'),
				fieldLabel : '管理部门',
				bean : 'SysDept',
				beanType : 'sys',
				emptyText : form_empty_text
			},{
				xtype : 'beantrigger',
				value : this.record.get('bean.businessMember'),
				fieldLabel : '业务代表',
				bean : 'SysUser',
				beanType : 'sys',
				emptyText : form_empty_text
			}]
	},{
		region : 'center',
		xtype : 'tabpanel',
		border : false,
		items : [{
				xtype: Ext.create('mvc.view.sys.SysPersonLink.ListEdit',{
							border : false,
							title : '联系人',
							itemId : this.oldId+'1',
							mainPkey : mainPkey,
							iconCls : 'tab-user-icon',
							isEdit : this.isEdit
						})
			},{
				xtype: Ext.create('mvc.view.sys.SysSupplierOrg.ListEdit',{
							border : false,
							title : '供应商机构对照表',
							itemId : this.oldId+'2',
							mainPkey : mainPkey,
							iconCls : 'tab-user-icon',
							isEdit : this.isEdit,
							listeners : {
								activate : function(tab){tab.onLoadFirst();}
							}
						})
			}]
	}];
		this.callParent(arguments);},
loadData : function(){
		this.down('#'+this.oldId+'1').onLoadFirst();
},
close : function(){
		//关闭WIN窗口时需要手动调用单元格编辑取消事件]
		var tab = this.down('tabpanel').getActiveTab();
		if (tab.isEdit)
			this.down('tabpanel').getActiveTab().cellEditing.cancelEdit();
		this.callParent(arguments);
}
});