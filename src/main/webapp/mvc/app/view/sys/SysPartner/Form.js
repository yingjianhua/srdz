Ext.define('mvc.view.sys.SysPartner.Form',{
	extend : 'Ext.form.Panel',
	requires : ['Ext.ux.DataTip'],
	layout : 'form',
	border : false,
	frame : false,
	insFlag : true,
	bodyPadding : '5 5 5 5',
	url : base_path+'/sys_SysPartner_iu',
	fieldDefaults : {
		labelWidth : 100,
		labelStyle : 'font-weight : bold'
	},
	plugins : {
		ptype : 'datatip'
	},
	initComponent : function(){
		var formFlds = [];
		formFlds.push({
				xtype : 'textfield',
				name : 'bean.code',
				afterLabelTextTpl : required,
				allowBlank : false,
				fieldLabel : '代码'
			},{
				xtype : 'textfield',
				name : 'bean.name',
				afterLabelTextTpl : required,
				allowBlank : false,
				fieldLabel : '名称'
			},mvc.Tools.crtComboForm(false,{
				name : 'bean.type',
				fieldLabel : '类型',
				store : Ext.create('mvc.combo.sys.SysPartnerType')
			}),mvc.Tools.crtComboTrigger(false,'sys_SysOrg','',{
				name : 'bean.org',
				fieldLabel : '机构'
			}),mvc.Tools.crtComboTrigger(false,'sys_SysDept','',{
				name : 'bean.dept',
				fieldLabel : '部门'
			}),{
				xtype : 'textfield',
				name : 'bean.remark',
				fieldLabel : '备注'
			},{
				xtype : 'hiddenfield',
				name : 'bean.pkey'
			});
		this.items = [{
			layout : {
				type : 'vbox',
				align : 'stretch'
			},
			border : false,
			items : formFlds
		}];
		this.callParent(arguments);
	}
});
