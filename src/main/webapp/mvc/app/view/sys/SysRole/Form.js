Ext.define('mvc.view.sys.SysRole.Form',{
	extend : 'Ext.form.Panel',
	requires : ['Ext.ux.DataTip'],
	layout : 'form',
	border : false,
	frame : false,
	insFlag : true,
	bodyPadding : '5 5 5 5',
	url : base_path+'/sys_SysRole_',
	fieldDefaults : {
		labelWidth : 100,
		labelStyle : 'font-weight : bold'
	},
	plugins : {
		ptype : 'datatip'
	},
	initComponent : function(){
		if (this.insFlag)
			this.url = this.url + 'ins';
		else
			this.url = this.url + 'upd';
		var formFlds = [];
		formFlds.push({
				xtype : 'textfield',
				name : 'bean.code',
				afterLabelTextTpl : required,
				allowBlank : false,
				emptyText : '可以重复, 便于快速输入',
				fieldLabel : '代码'
			},{
				xtype : 'textfield',
				name : 'bean.name',
				afterLabelTextTpl : required,
				allowBlank : false,
				fieldLabel : '名称'
			},mvc.Tools.crtComboForm(false,{
				name : 'bean.enabled',
				fieldLabel : '启用标志',
				store : Ext.create('mvc.combo.sys.SysOEnabled'),
				value : 1
			}),mvc.Tools.crtComboForm(false,{
				fieldLabel : '可视范围',
				store : Ext.create('mvc.combo.sys.SysODispScope2'),
				value : 1,
				itemId : 'form_cmb',
				listeners : {
					scope : this,
					change : function(field,newv,oldv,opts) {
						var me = this;
						var org = me.down('#form_SysRole_org');
						if (newv === 1){
							org.hide();
							org.disable();
							org.setValue();
						}else{
							org.show();
							org.enable();
						}
					}
				}
			}),mvc.Tools.crtComboTrigger(true,'sys_SysOrg','',{
				name : 'bean.tbObj',
				fieldLabel : '可视机构',
				itemId : 'form_SysRole_org',
				hidden : true,
				disabled : true,
				listeners : {
					scope : this,
					change : function(field,newv,oldv,opts) {
						var me = this;
						var cmb = me.down('#form_cmb');
						if (newv)
							cmb.setValue(2);
					}
				}
			}),{
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
