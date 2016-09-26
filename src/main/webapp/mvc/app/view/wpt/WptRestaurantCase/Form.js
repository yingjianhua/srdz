Ext.define('mvc.view.wpt.WptRestaurantCase.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/wpt/resource/restaurantCase_',
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
({
		xtype : 'beantrigger',
		name : 'bean.restaurant.pkey',
		id : 'restaurant.pkey',
		fieldLabel : '餐厅',
		bean : 'WptRestaurant',
		beanType : 'wpt',
		emptyText : form_empty_text,
		afterLabelTextTpl : required,
		allowBlank : false
	},{xtype : 'imagefield',name : 'bean.imgUrl',id : 'imgUrl',fieldLabel : '图片',labelWidth : this.fieldDefaults.labelWidth}
	,{xtype : 'textfield',name : 'bean.des',id : 'des',fieldLabel : '描述'}
	,{xtype : 'numberfield',name : 'bean.rowVersion',id : 'rowVersion',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '版本',hidden : true,allowDecimals : false}
	,{
		xtype : 'hiddenfield',
		name : 'bean.pkey',
		id : 'pkey'
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