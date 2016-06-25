Ext.define('mvc.view.sys.SysSeq.Form',{
	extend : 'Ext.form.Panel',
	requires : ['Ext.ux.DataTip'],
	layout : 'form',
	border : false,
	frame : false,
	insFlag : true,
	bodyPadding : '5 5 5 5',
	url : base_path+'/sys_SysSeq_',
	fieldDefaults : {
		labelWidth : 120,
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
				name : 'one.name',
				fieldLabel : '名称',
				readOnly : true
			},mvc.Tools.crtComboForm(false,{
				name : 'bean.orgFlag',
				fieldLabel : '是否按机构编号',
				store : Ext.create('mvc.combo.sys.SysOYn'),
				value : 1
			}),{
				xtype : 'textfield',
				name : 'bean.firstStr',
				fieldLabel : '前缀字符'
			},mvc.Tools.crtComboForm(false,{
				name : 'bean.type',
				fieldLabel : '编号类型',
				store : Ext.create('mvc.combo.sys.SysOType'),
				value : 1
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
