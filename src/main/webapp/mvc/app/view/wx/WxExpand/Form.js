Ext.define('mvc.view.wx.WxExpand.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/wx_WxExpand_',
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
({xtype : 'textfield',name : 'bean.action',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '功能类',readOnly : true}
	,{xtype : 'textfield',name : 'bean.name',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '名称'}
	,{xtype : 'textfield',name : 'bean.content',fieldLabel : '描述'}
	,{xtype : 'imagefield',name : 'bean.imageUrl',fieldLabel : '封面',afterLabelTextTpl : required,allowBlank : false}
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
    var me = this;
    me.down("[name=bean.imageUrl]").down("textfield").on({
        change : {
            fn : function(field,newv,oldv) {
                if(me.article) {
                    me.article.onImageChange(newv)
                }
            }
        }
    });
}
});