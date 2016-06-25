Ext.define('mvc.view.wpt.WptSpecial.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/wpt_WptSpecial_',
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
({xtype : 'textfield',name : 'bean.title',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '标题'}
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
			change : function(field, newv, oldv) {
				if(oldv && oldv != newv){
					this.up('window').lineTable.store.removeAll()
				}
			}
		}
	},{xtype : 'imagefield',name : 'bean.topImgUrl',blankText : "推荐尺寸 640*359",afterLabelTextTpl : required,allowBlank : false, labelWidth : this.fieldDefaults.labelWidth,fieldLabel : '顶图'}
,{xtype : 'imagefield',name : 'bean.baseImgUrl',blankText : "推荐尺寸 640*427",afterLabelTextTpl : required,allowBlank : false, labelWidth : this.fieldDefaults.labelWidth,fieldLabel : '底图'}
	,{xtype : 'textfield',name : 'bean.intro',fieldLabel : '介绍'}
	,{xtype : 'numberfield',name : 'bean.sort',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '排序',allowDecimals : false}
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