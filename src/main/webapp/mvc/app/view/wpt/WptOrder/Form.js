Ext.define('mvc.view.wpt.WptOrder.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
isPt : false,
bodyPadding : '5 5 5 5',
url : base_path+'/wpt_WptOrder_upd',
fieldDefaults : {
	labelWidth : 100,
	labelStyle : 'font-weight : bold'
},
initComponent : function(){
	var me = this;
	var formFlds = [];
	formFlds.push(
			{xtype : 'textfield',name : 'bean.orderid',id : 'orderid', fieldLabel : '订单号', readOnly : true},
			{xtype : 'beantrigger',name : 'bean.member',id : 'member',fieldLabel : '关注用户',bean : 'WxUser',beanType : 'wx',readOnly : true},
			{xtype : 'beantrigger',name : 'bean.city.pkey',id : 'city.pkey',fieldLabel : '城市',bean : 'WptCity',beanType : 'wpt',emptyText : form_empty_text,afterLabelTextTpl : required,allowBlank : false,
				listeners : {
					change : function(field, newv, oldv) {
						var restaurant = me.down("[name=bean.restaurant]");
						restaurant.diySql = newv?"city="+newv.split(bean_split)[0]:"1=2";
						if(oldv) {
							restaurant.setValue(null);
						}
					}
				}
			}
	);
	if(me.isPt) {
		formFlds.push(
				{xtype : 'beantrigger',name : 'bean.restaurant.pkey',id : 'restaurant.pkey',fieldLabel : '餐厅',bean : 'WptRestaurant',beanType : 'wpt',emptyText : form_empty_text,listeners : {change : function(field, newv, oldv) {if(oldv)me.up('window').lineTable.store.removeAll()}}},
				{xtype : 'textfield',name : 'bean.combo.pkey',id : 'combo.pkey', fieldLabel : '套餐'},
				{xtype : 'numberfield',name : 'bean.deposit',id : 'deposit',afterLabelTextTpl : required,allowBlank : false,value : 0,fieldLabel : '定金',decimalPrecision : 2,listeners : {change : function (field, newv, oldv) {var residue = me.down("[name=bean.residue]").getValue();if(residue) {me.down("[name=bean.price]").setValue(newv+residue);} else {me.down("[name=bean.price]").setValue(newv);}}}},
				{xtype : 'numberfield',name : 'bean.price',id : 'price',value : 0,fieldLabel : '价格',decimalPrecision : 2, readOnly : true}
		);
	} else {
		formFlds.push(
				{xtype : 'beantrigger',name : 'bean.restaurant.pkey',id : 'restaurant.pkey',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '餐厅',bean : 'WptRestaurant',beanType : 'wpt',emptyText : form_empty_text,listeners : {change : function(field, newv, oldv) {if(oldv)me.up('window').lineTable.store.removeAll()}}},
				{xtype : 'textfield',name : 'bean.combo.pkey',id : 'combo.pkey',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '套餐名称'},
				{xtype : 'numberfield',name : 'bean.price',id : 'price',afterLabelTextTpl : required,allowBlank : false,value : 0,fieldLabel : '价格',decimalPrecision : 2}
		);
	}
	formFlds.push(
			{xtype : 'datefield',name : 'bean.time',id : 'time',value : 'Env.getTranBeginTime()',fieldLabel : '用餐时间',afterLabelTextTpl : required,allowBlank : false,format : 'Y-m-d H:i:s'},
			{xtype : 'textfield',name : 'bean.contactMan',id : 'contactMan',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '联系人'},
			mvc.Tools.crtComboForm(false,{name : 'bean.contactSex',id : 'contactSex',fieldLabel : '性别',store : Ext.create('mvc.combo.sys.SysOSex'),value : 0}),
			mvc.Tools.crtComboForm(false,{name : 'bean.contactType',id : 'contactType',fieldLabel : '联系方式类型',store : Ext.create('mvc.combo.wpt.WptOContactStatus'),value : 0}),
			{xtype : 'textfield',name : 'bean.contactWay',id : 'contactWay',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '联系方式'},
			{xtype : 'textarea',name : 'bean.rem',id : 'rem',fieldLabel : '备注'},
			{xtype : 'numberfield',name : 'bean.rowVersion',id : 'rowVersion',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '版本',hidden : true,allowDecimals : false},
			{xtype : 'hiddenfield',name : 'bean.pkey',id : 'pkey'}
	);
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