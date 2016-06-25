Ext.define('mvc.view.sys.SysDept.WinEdit',{
extend : 'Ext.window.Window',
width : 800,
height : 500,
layout : 'border',
resizable : true,
maximizable : true,
modal : true,
iconCls : 'app-icon',
record : null,
oldId : 'SysDept_edit_',
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
			,{xtype : 'textfield',fieldLabel : '部门名称',value : this.record.get('bean.name')}
			,
				mvc.Tools.crtComboForm(true,{
							value : this.record.get('bean.enabled'),
							fieldLabel : '启用标志',
							store : Ext.create('mvc.combo.sys.SysOEnabled')
						})
			,
				mvc.Tools.crtComboTrigger(true,'sys_SysOrg','',{
							value : this.record.get('bean.org'),
							fieldLabel : '所属机构'
						})
			,{
				xtype : 'beantrigger',
				value : this.record.get('bean.manager'),
				fieldLabel : '部门负责人',
				bean : 'SysUser',
				beanType : 'sys',
				emptyText : form_empty_text
			},{
				xtype : 'beantrigger',
				value : this.record.get('bean.deptUp'),
				fieldLabel : '上级部门',
				bean : 'SysDept',
				beanType : 'sys',
				emptyText : form_empty_text
			},
				mvc.Tools.crtComboTrigger(true,'sys_SysCell','',{
							value : this.record.get('bean.cell'),
							fieldLabel : '核算单元'
						})
			,{xtype : 'textfield',fieldLabel : '备注',value : this.record.get('bean.rem')}
			]
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