Ext.define('mvc.view.wpt.WptRestaurant.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/wpt_WptRestaurant_',
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
({xtype : 'textfield',name : 'bean.name',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '餐厅名称'}
	,{xtype : 'textfield',name : 'bean.mobile',fieldLabel : '电话'}
	,{xtype : 'textfield',name : 'bean.manager',fieldLabel : '店长电话'}
	,{
		xtype : 'beantrigger',
		name : 'bean.city',
		fieldLabel : '城市',
		bean : 'WptCity',
		beanType : 'wpt',
		emptyText : form_empty_text,
		afterLabelTextTpl : required,
		allowBlank : false,
		listeners : {
			scope : this,
			change : function(field,newv,oldv){
				var cl = this.down('[name=bean.cityline]');
				var store = cl.getStore()
				store.actWhere = 'city='+newv.split(bean_split)[0];
				store.getProxy().extraParams={"sarg1":store.actWhere};
				cl.setValue(null);
				cl.getStore().load();
		} }
	},
		mvc.Tools.crtComboTrigger(false,'wpt_WptCityLine','1=2',{
					name : 'bean.cityline',
					fieldLabel : '区'
				})
	,{xtype : 'textfield',name : 'bean.addr',fieldLabel : '地址'}
	,{xtype : 'textfield',name : 'bean.coordinate',fieldLabel : '经纬度'}
	,
		mvc.Tools.crtComboForm(false,{
					name : 'bean.display',
					fieldLabel : '显示图片',
					store : Ext.create('mvc.combo.sys.SysOYn'),
					value : 0
				})
	,{
			xtype : 'imagefield',
			name : 'bean.imgUrl',
			blankText : "推荐尺寸 640*358",
			afterLabelTextTpl : required,
			allowBlank : false,
			labelWidth : this.fieldDefaults.labelWidth,
			fieldLabel : '图片'}
	,{xtype : 'textarea',name : 'bean.rem',fieldLabel : '备注'}
	,{xtype : 'textfield',name : 'bean.des',fieldLabel : '描述'}
	,{xtype : 'textfield',name : 'bean.startdate',fieldLabel : '开始营业时间'}
	,{xtype : 'textfield',name : 'bean.stopdate',fieldLabel : '结束营业时间'}
	,{xtype : 'numberfield',name : 'bean.consumption',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '人均消费',decimalPrecision : 2}
	,{xtype : 'textfield',name : 'bean.wifiaccount',fieldLabel : 'wifi账号'}
	,{xtype : 'textfield',name : 'bean.wifipassword',fieldLabel : 'wifi密码'}
	,{
		xtype : 'beantrigger',
		name : 'bean.template',
		fieldLabel : '模板',
		bean : 'WptRestaurantTemplate',
		beanType : 'wpt',
		emptyText : form_empty_text,
	},
	mvc.Tools.crtComboForm(false,{
				name : 'bean.enabled',
				fieldLabel : '启用',
				store : Ext.create('mvc.combo.sys.SysOEnabled'),
				value : 1,
			})
	,{xtype : 'numberfield',name : 'bean.rowVersion',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '版本',hidden : true,allowDecimals : false}
	,{xtype : 'hiddenfield',name : 'bean.pkey'}
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