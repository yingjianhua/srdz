Ext.define('mvc.view.prv.PrvRole.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/prv_PrvRole_',
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
	formFlds.push(
		{xtype : 'textfield',name : 'bean.code',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '代码'}
		,{xtype : 'textfield',name : 'bean.name',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '名称'}
		,mvc.Tools.crtComboForm(false,{
			name : 'bean.rangeType',
			fieldLabel : '可视范围',
			store : Ext.create('mvc.combo.sys.SysORangeType'),
			value : 1,
			listeners : {
				scope : this,
				change : function(field,newv,oldv,opts) {
					var range = this.down('[name=bean.rangePkey]');
					if (newv <= 10) {
						range.setDisabled(true);
						range.hide();
					} else {
						if (newv > 10 && newv <= 20) {
							range.setFieldLabel('可视机构');
							range.store.proxy.url = base_path + '/sys_SysOrg_getComboTrigger';
						} else if (newv >20 && newv <= 30) {
							range.setFieldLabel('可视单元');
							range.store.proxy.url = base_path + '/sys_SysCell_getComboTrigger';
						}
						range.store.load();
						range.setDisabled(false);
						range.show();
					}
				}
			}
		}),
		mvc.Tools.crtComboTrigger(false,'sys_SysOrg','',{
			name : 'bean.rangePkey',
			fieldLabel : '可视机构',
			hidden : this.insFlag ? true : false,
			disabled : this.insFlag ? true : false
		})
		,mvc.Tools.crtComboTrigger(false,'sys_SysCell','',{
			name : 'bean.cell',
			fieldLabel : '管理核算单元'
		})
		,{xtype : 'numberfield',name : 'bean.rowVersion',value : '0',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '版本',hidden : true,allowDecimals : false}
		,{
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
	var rt = this.down('[name=bean.rangeType]');
	var rp = this.down('[name=bean.rangePkey]');
	if (rt.getValue() == 1) {
		rp.setDisabled(true);
		rp.hide();
	} else if (rt.getValue() > 10 && rt.getValue() <= 20) {
		rp.setFieldLabel('可视机构');
		rp.store.proxy.url = base_path + '/sys_SysOrg_getComboTrigger';
		rp.store.load();
	} else if (rt.getValue() >20 && rt.getValue() <= 30) {
		rp.setFieldLabel('可视单元');
		rp.store.proxy.url = base_path + '/sys_SysCell_getComboTrigger';
		rp.store.load();
	}
}
});