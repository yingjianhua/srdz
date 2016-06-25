Ext.define('mvc.view.wm.WmNews.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip','mvc.tools.LongPkeyField'],
layout : 'form',
border : false,
frame : false,
article : null,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/wm_WmNews_',
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
	,{xtype : 'imagefield',name : 'bean.imageUrl',labelWidth : this.fieldDefaults.labelWidth,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '封面'}
	,{xtype : 'textfield',name : 'bean.summary',fieldLabel : '摘要'}
	,
		mvc.Tools.crtComboForm(false,{
			name : 'bean.type',
			fieldLabel : '图文链接类型',
			store : Ext.create('mvc.combo.wm.WmONewsType'),
			value : 1,
			listeners : {
				scope : this,
				change : function(field, newValue, oldValue, eOpts) {
					var url = this.down("[name=bean.url]")
					var exp = this.down("[name=bean.exp]")
					if(newValue === 1) {
						url.show();
						url.enable();
						exp.hide();
						exp.disable();
					} else if(newValue === 2){
						exp.show();
						exp.enable();
						url.hide();
						url.disable();
					} else {
						exp.hide();
						exp.disable();
						url.hide();
						url.disable();
					}
				}
			}
		})
	,{xtype : 'longpkeyfield', name : 'bean.exp', fieldLabel : '扩展链接', title : "扩展选择器"}
	,{xtype : 'textfield',name : 'bean.url',fieldLabel : '自定义链接'}
	,
		mvc.Tools.crtComboForm(false,{
					name : 'bean.showCoverPic',
					fieldLabel : '是否显示封面',
					store : Ext.create('mvc.combo.sys.SysOYn'),
					value : 1
				})
	,{xtype : 'textfield',name : 'bean.author',fieldLabel : '作者'}
	,{xtype : 'panel',
		name : 'rtxt',
		html:'<div style="overflow:auto;height:200px;max-height:175px;width:580px" id="rtxt"></div><br>',
		items : [ {
			xtype : 'text',
			text : '正文:',
			style : {
				fontWeight: 'bolder'
			}
		} ]
	}
	,
		mvc.Tools.crtComboTrigger(true,'wm_WmNews','',{
					name : 'bean.picUp',
					fieldLabel : '上级图文',
					hidden : true
				})
	,{
		xtype : 'textfield',
		name : 'bean.content',
		fieldLabel : '原文',
		hidden : true,
		listeners: {
			scope : this,
			change : function (field,newv,oldv){
				UM.getEditor('rtxt').setContent(newv);
			}
		}
	}
	,{xtype : 'textfield',name : 'bean.relUrl',fieldLabel : '原文链接'}
	,{xtype : 'numberfield',name : 'bean.sort',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '排序号',allowDecimals : false}
	,
		mvc.Tools.crtComboTrigger(true,'wx_WxAccount','',{
					name : 'bean.account',
					fieldLabel : '公众帐号',
					hidden : true
				})
	,{xtype : 'datefield',name : 'bean.createdTime',value : 'Env.getTranBeginTime()',fieldLabel : '创建时间',format : 'Y-m-d H:i:s', hidden : true}
	,{xtype : 'datefield',name : 'bean.updatedTime',value : 'Env.getTranBeginTime()',fieldLabel : '更新时间',format : 'Y-m-d H:i:s', hidden : true}
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
					//console.log("form中图片改变了【"+newv+"】，接下来要改变文章中的图片");
					me.article.onImageChange(newv)
				}
			}
		}
	});
	me.down("[name=bean.title]").on({
		change : {
			fn : function(field,newv,oldv) {
				if(me.article) {
					//console.log("form中title改变了【"+newv+"】，接下来要改变文章中的title");
					me.article.onTitleChange(newv)
				}
			}
		}
	});
	
},
getValues: function(asString, dirtyOnly, includeEmptyText, useDataValues) {
		var data = this.callParent();
		var content = UM.getEditor('rtxt').getContent();
		this.down("[name=bean.content]").setValue(content);
		Ext.apply(data,{"bean.content": content})
		return data;
	},
listeners: {
	'render': function () {
		var ue = UM.getEditor('rtxt');
	},
	'destroy': function () {
		var ue = UM.clearCache('rtxt');
	}
	
}
});