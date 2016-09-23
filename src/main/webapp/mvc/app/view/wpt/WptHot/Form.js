Ext.define('mvc.view.wpt.WptHot.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/wpt/resource/hot_',
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
		name : 'bean.city.pkey',
		id : 'city.pkey',
		fieldLabel : '城市',
		bean : 'WptCity',
		beanType : 'wpt',
		emptyText : form_empty_text,
		afterLabelTextTpl : required,
		allowBlank : false,
		listeners : {
			scope : this,
			change : function(field,newv,oldv){
				var cl = this.down('[name=bean.restaurant.pkey]');
				var store = cl.getStore()
				store.actWhere = 'city='+newv.split(bean_split)[0];
				store.getProxy().extraParams={"sarg1":store.actWhere};
				cl.setValue(null);
				cl.getStore().load();
		} }
	},
	mvc.Tools.crtComboTrigger(false,'wpt/resource/restaurant','1=2',{
		name : 'bean.restaurant.pkey',
		id : 'restaurant.pkey',
		fieldLabel : '餐厅'
	})
	,{xtype : 'numberfield',name : 'bean.sort',id : 'sort',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '排序',allowDecimals : false}
	,{xtype : 'numberfield',name : 'bean.rowVersion',id : 'rowVersion',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '版本',hidden : true,allowDecimals : false}
	,{xtype : 'hiddenfield',name : 'bean.account',id : 'account'}
	,{xtype : 'hiddenfield',name : 'bean.pkey',id : 'pkey'});
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