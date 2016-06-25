Ext.define('mvc.view.js.JsMenuShare.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
bodyPadding : '5 5 5 5',
url : base_path+'/js_JsMenuShare_upd',
fieldDefaults : {
	labelWidth : 100,
	labelStyle : 'font-weight : bold'
},
initComponent : function(){
			var formFlds = [];
			formFlds.push
(
		mvc.Tools.crtComboForm(false,{
					name : 'bean.enabled',
					fieldLabel : '启用标志',
					store : Ext.create('mvc.combo.sys.SysOEnabled'),
					value : 1
				})
	,{xtype : 'textfield',name : 'bean.title',fieldLabel : '分享标题'}
	,{xtype : 'textfield',name : 'bean.des',fieldLabel : '分享描述'}
	/*,{xtype : 'textfield',name : 'bean.link',fieldLabel : '分享链接'}*/
	,{xtype : 'imagefield',name : 'bean.imgUrl',fieldLabel : '分享图标', labelWidth : this.fieldDefaults.labelWidth, widthLimit : 100}
	/*,
		mvc.Tools.crtComboForm(false,{
					name : 'bean.type',
					fieldLabel : '分享类型',
					store : Ext.create('mvc.combo.js.JsOJsMenuType'),
					value : 2
				})
	,{xtype : 'textfield',name : 'bean.dataUrl',fieldLabel : '数据链接'}*/
	,
	/************************************************************************/
	{
        xtype: 'fieldset',
        flex: 1,
        
//        title: '分享设置',
        defaultType: 'checkbox', // each item will be a checkbox
        layout: 'anchor',
        defaults: {
            anchor: '100%',
            hideEmptyLabel: false
        },
        items: [ 
            	{
					name : 'bean.appMessage',
					fieldLabel : '微信朋友',
					store : Ext.create('mvc.combo.sys.SysOYn'),
					inputValue: '1',
            	}
	,
		{
					name : 'bean.timeLine',
					fieldLabel : '微信朋友圈',
					store : Ext.create('mvc.combo.sys.SysOYn'),
					inputValue: '1',
				}
	,
		{
					name : 'bean.qq',
					fieldLabel : 'QQ好友',
					store : Ext.create('mvc.combo.sys.SysOYn'),
					inputValue: '1',
				}
	,
		{
					name : 'bean.weibo',
					fieldLabel : '微博',
					store : Ext.create('mvc.combo.sys.SysOYn'),
					inputValue: '1',
				}
	,
		{
					name : 'bean.qzone',
					fieldLabel : 'QQ空间',
					store : Ext.create('mvc.combo.sys.SysOYn'),
					inputValue: '1',
				}
	]
    }, 
	/************************************************************************/
//		mvc.Tools.crtComboForm(false,{
//					name : 'bean.appMessage',
//					fieldLabel : '微信朋友',
//					store : Ext.create('mvc.combo.sys.SysOYn'),
//					value : 1
//				})
//	,
//		mvc.Tools.crtComboForm(false,{
//					name : 'bean.timeLine',
//					fieldLabel : '微信朋友圈',
//					store : Ext.create('mvc.combo.sys.SysOYn'),
//					value : 1
//				})
//	,
//		mvc.Tools.crtComboForm(false,{
//					name : 'bean.qq',
//					fieldLabel : 'QQ好友',
//					store : Ext.create('mvc.combo.sys.SysOYn'),
//					value : 0
//				})
//	,
//		mvc.Tools.crtComboForm(false,{
//					name : 'bean.weibo',
//					fieldLabel : '微博',
//					store : Ext.create('mvc.combo.sys.SysOYn'),
//					value : 1
//				})
//	,
//		mvc.Tools.crtComboForm(false,{
//					name : 'bean.qzone',
//					fieldLabel : 'QQ空间',
//					store : Ext.create('mvc.combo.sys.SysOYn'),
//					value : 1
//				})
	/*,*/{xtype : 'numberfield',name : 'bean.rowVersion',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '版本',hidden : true,allowDecimals : false}
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