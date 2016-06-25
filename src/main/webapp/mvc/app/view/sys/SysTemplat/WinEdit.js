Ext.define('mvc.view.sys.SysTemplat.WinEdit',{
extend : 'Ext.window.Window',
width : 800,
height : 500,
layout : 'border',
resizable : true,
maximizable : true,
modal : true,
iconCls : 'app-icon',
record : null,
oldId : 'SysTemplat_edit_',
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
		items : [
				mvc.Tools.crtComboForm(true,{
							value : this.record.get('bean.type'),
							fieldLabel : '模板类型',
							store : Ext.create('mvc.combo.sys.SysOTemplateType')
						})
			,{xtype : 'textfield',fieldLabel : '代码',value : this.record.get('bean.code')}
			,{xtype : 'numberfield',fieldLabel : '启用年份',value : this.record.get('bean.year')}
			,{xtype : 'textfield',fieldLabel : '模板名称',value : this.record.get('bean.name')}
			,
				mvc.Tools.crtComboTrigger(true,'sys_SysCell','',{
							value : this.record.get('bean.mngCell'),
							fieldLabel : '管理单元'
						})
			,
				mvc.Tools.crtComboForm(true,{
							value : this.record.get('bean.enabled'),
							fieldLabel : '启用标志',
							store : Ext.create('mvc.combo.sys.SysOEnabled')
						})
			]
	},{
		region : 'center',
		xtype : 'tabpanel',
		border : false,
		items : [{
				xtype: Ext.create('mvc.view.sys.SysTemplatCell.ListEdit',{
							border : false,
							title : '模板可使用单元',
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