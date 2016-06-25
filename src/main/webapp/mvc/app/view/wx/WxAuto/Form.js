Ext.define('mvc.view.wx.WxAuto.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/wx_WxAuto_',
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
({xtype : 'textfield',name : 'bean.keyword',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '关键字'}
	,
		        mvc.Tools.crtComboForm(false,{
            name : 'bean.msgType',
            fieldLabel : '消息类型',
            store : Ext.create('mvc.combo.wx.WxOMsgType'),
            value : 1,
            listeners : {
                scope : this,
                change : function(field, newv, oldv, opts) {
                	var template = this.down('[name=bean.template]');
    				switch (newv) {
                	case 1:{
                		template.bean='WmText';
                		break;
                	}
                	case 2: {
                		 template.bean='WmImage';template.diySql='status=2';
                		 break;
                	}
                	case 3: {
                		template.bean='WmMusic';template.diySql='status=2';
                		break;
                	}
                	case 4: {
                		template.bean='WmVideo';template.diySql='status=2';
                		break;
                	}
                	case 5: {
                		template.bean='WmVoice';template.diySql='status=2';
                		break;
                	}
                	case 6: {
                		template.bean='WmNews';
                		break;
                	}
                	default :{
                		
                	}
                	}
                }
            }
        })

	,{xtype : 'beantrigger',name : 'bean.template',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '消息模板',bean : 'WmText',beanType : 'wm'}
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