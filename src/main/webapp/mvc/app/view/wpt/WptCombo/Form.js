Ext.define('mvc.view.wpt.WptCombo.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/wpt_WptCombo_',
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
	formFlds.push({
		xtype : 'beantrigger',
		name : 'bean.restaurant',
		fieldLabel : '餐厅',
		bean : 'WptRestaurant',
		beanType : 'wpt',
		emptyText : form_empty_text,
		afterLabelTextTpl : required,
		allowBlank : false,
		listeners : {
			change : function(field, newv, oldv) {
				var win = this.up("window")
				if(oldv != undefined) {
					var comboLine = win.comboLine;
					comboLine.getStore().removeAll();
				} 
				var resLine = win.resLine;
				if(newv) {
					var array = [{
						id:'flds',
						property: 'param',
						value: '1=1'
					}, {
						id:'diy',
						property: 'diy',
						value: "restaurant="+newv.split(bean_split)[0]
					}];
					resLine.getStore().filter(array);
				} else {
					resLine.getStore().removeAll();
				}
			}
		}}
	,{xtype : 'textfield',name : 'bean.name',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '名称'}
	,{xtype : 'imagefield',name : 'bean.imgUrl',blankText : "推荐尺寸 640*425",afterLabelTextTpl : required,allowBlank : false, labelWidth : this.fieldDefaults.labelWidth,fieldLabel : '图片'}
	,{xtype : 'textareafield',name : 'bean.des',fieldLabel : '描述'}
	,{xtype : 'numberfield',name : 'bean.origPrice',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '原价',decimalPrecision : 2}
	,{xtype : 'numberfield',name : 'bean.price',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '价格',decimalPrecision : 2}
	,{xtype : 'numberfield',name : 'bean.numberMin',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '最少人数',allowDecimals : false}
	,{xtype : 'numberfield',name : 'bean.numberMax',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '最大人数',allowDecimals : false}
	,{xtype : 'textfield',name : 'bean.serviceDate',fieldLabel : '使用日期'}
	,{xtype : 'textfield',name : 'bean.serviceTime',fieldLabel : '使用时段'}
	,{xtype : 'textfield',name : 'bean.rem',fieldLabel : '备注'}
	,{xtype : 'numberfield',name : 'bean.sort',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '排序',allowDecimals : false}
	,mvc.Tools.crtComboForm(false,{
				name : 'bean.enabled',
				fieldLabel : '启用',
				store : Ext.create('mvc.combo.sys.SysOEnabled'),
				value : 1,
			})
	,{xtype : 'numberfield',name : 'bean.rowVersion',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '版本',hidden : true,allowDecimals : false}
	,{
		xtype : 'hiddenfield',
		name : 'bean.pkey'
	});
	this.items = [{
		layout : {
			type : 'table',
			columns : 2,
			itemCls : 'x-layout-table-items-form'
		},
		border : false,
		items : formFlds
	}];
	this.callParent(arguments);
}
});