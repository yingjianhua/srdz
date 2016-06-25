Ext.define('mvc.view.wa.WaActConfig.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/wa_WaActConfig_',
fieldDefaults : {
	labelWidth : 100,
	labelStyle : 'font-weight : bold'
},
initComponent : function(){
			if (this.insFlag)
				this.url = this.url + 'ins';
			else
				this.url = this.url + 'upd';
			var formFlds = [];
			formFlds.push
({xtype : 'textfield',name : 'bean.name',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '名称'}
	,{xtype : 'numberfield',name : 'bean.cycle',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '活动周期',allowDecimals : false}
	,
		mvc.Tools.crtComboForm(false,{
					name : 'bean.unit',
					fieldLabel : '单位',
					store : Ext.create('mvc.combo.wa.WaOActUnit'),
					value : 1
				})
	,{xtype : 'numberfield',name : 'bean.cycleLimit',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '周期参与数',allowDecimals : false}
	,{xtype : 'textfield',name : 'bean.cycleLimitWords',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '周期参与数提示语'}
	,{xtype : 'numberfield',name : 'bean.ipLimit',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '同IP周期参与数',allowDecimals : false}
	,{xtype : 'textfield',name : 'bean.ipLimitWords',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '同IP周期参与数提示语'}
	,{xtype : 'numberfield',name : 'bean.viewRate',value : 1,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '浏览量系数',allowDecimals : false}
	,{xtype : 'numberfield',name : 'bean.entryRate',value : 1,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '报名数系数',allowDecimals : false}
	,{xtype : 'numberfield',name : 'bean.actRate',value : 1,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '参与数系数',allowDecimals : false}
	,
		mvc.Tools.crtComboForm(false,{
					name : 'bean.imageShape',
					fieldLabel : '图片限制',
					store : Ext.create('mvc.combo.wa.WaOActImageShape'),
					value : 3
				})
	,
		mvc.Tools.crtComboForm(false,{
					name : 'bean.customMenu',
					fieldLabel : '自定义菜单',
					store : Ext.create('mvc.combo.sys.SysOYn'),
					value : 0
				})
	,
		mvc.Tools.crtComboForm(false,{
					name : 'bean.resArea',
					fieldLabel : '限制区域',
					store : Ext.create('mvc.combo.sys.SysOYn'),
					value : 0,
					listeners : {
                        scope : this,
                        change : function(field, newv, oldv, opts) {
                            if(newv==0){
                                this.down('[name=bean.resAreaWords]').hide();
                                this.down('[name=bean.resAreaWords]').setDisabled(true);
                                this.down('[name=bean.area]').hide();
                                this.down('[name=bean.area]').setDisabled(true);
                                this.down('[name=bean.invalidArea]').hide();
                                this.down('[name=bean.invalidArea]').setDisabled(true);
                            }
                            if(newv==1){
                                this.down('[name=bean.resAreaWords]').show();
                                this.down('[name=bean.resAreaWords]').setDisabled(false);
                                this.down('[name=bean.area]').show();
                                this.down('[name=bean.area]').setDisabled(false);
                                this.down('[name=bean.invalidArea]').show();
                                this.down('[name=bean.invalidArea]').setDisabled(false);
                            }
                        }
                    }
				})
	,{xtype : 'textfield',name : 'bean.resAreaWords',afterLabelTextTpl : required,allowBlank : false,disabled:true,fieldLabel : '限制区域提示',hidden : true}
	,{xtype : 'textfield',name : 'bean.area',disabled:true,fieldLabel : '有效区域',hidden:true}
	,{xtype : 'textfield',name : 'bean.invalidArea',disabled:true,fieldLabel : '无效区域',hidden:true}
	,{xtype : 'numberfield',name : 'bean.rowVersion',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '版本',hidden : true,allowDecimals : false}
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
}
});