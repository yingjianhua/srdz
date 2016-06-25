Ext.define('mvc.view.sys.SysSupplierOrg.Form',{
	extend : 'Ext.form.Panel',
	requires : ['Ext.ux.DataTip'],
	layout : 'form',
	border : false,
	frame : false,
	insFlag : true,
	bodyPadding : '5 5 5 5',
	url : base_path+'/sys_SysSupplierOrg_',
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
		formFlds.push(mvc.Tools.crtComboTrigger(false,'sys_SysSupplier','',{
				name : 'bean.supplier',
				fieldLabel : '供应商'
			}),mvc.Tools.crtComboTrigger(false,'sys_SysOrg','',{
				name : 'bean.org',
				fieldLabel : '机构'
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
