Ext.define('mvc.view.wpt.WptTop.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/wpt_WptTop_',
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
				var cl = this.down('[name=bean.cityline]');
				var store = cl.getStore()
				store.actWhere = 'city='+newv.split(bean_split)[0];
				store.getProxy().extraParams={"sarg1":store.actWhere};
				cl.setValue(null);
				cl.getStore().load();
		} }
	},
		mvc.Tools.crtComboTrigger(false,'wpt_WptCityLine','1=2',{
					name : 'bean.cityline.pkey',
					id : 'cityline.pkey',
					fieldLabel : '区域'
				})
	,{
		xtype : 'beantrigger',
		name : 'bean.banquet.pkey',
		id : 'banquet.pkey',
		fieldLabel : '主题',
		bean : 'WptBanquet',
		beanType : 'wpt',
		emptyText : form_empty_text,
		afterLabelTextTpl : required,
		allowBlank : false
	},{xtype : 'textfield',name : 'bean.title',id : 'title',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '标题'}
	,{xtype : 'imagefield',name : 'bean.imgUrl',id : 'imgUrl',afterLabelTextTpl : required,allowBlank : false, labelWidth : this.fieldDefaults.labelWidth,fieldLabel : '图片',blankText:"推荐尺寸为383*246"}
	,{xtype : 'datefield',name : 'bean.date',id : 'date',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '时间',format : 'Y-m-d'}
	,{xtype : 'textfield',name : 'bean.url',id : 'url',fieldLabel : '链接'}
	,
		mvc.Tools.crtComboForm(false,{
					name : 'bean.top',
					id : 'top',
					fieldLabel : '置顶',
					store : Ext.create('mvc.combo.sys.SysOYn'),
					value : 0
				})
	,{xtype : 'numberfield',name : 'bean.sort',id : 'sort',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '排序',allowDecimals : false}
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