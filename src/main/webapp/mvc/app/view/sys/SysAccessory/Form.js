Ext.define('mvc.view.sys.SysAccessory.Form',{
	extend : 'Ext.form.Panel',
	requires : ['Ext.ux.DataTip'],
	layout : 'form',
	border : false,
	frame : false,
	insFlag : true,
	tbandkeyTable : null,
	tbandkeyPkey : null,
	bodyPadding : '5 5 5 5',
	url : base_path+'/sys_SysAccessory_',
	fieldDefaults : {
		labelWidth : 100,
		labelStyle : 'font-weight : bold'
	},
	plugins : {
		ptype : 'datatip'
	},
	initComponent : function(){
		if (this.insFlag)
			this.url = this.url + 'ins';
		else
			this.url = this.url + 'upd';
//		var formFlds = [];
//		formFlds.push(mvc.Tools.crtComboForm(false,{
//				name : 'bean.type',
//				fieldLabel : '附件类型',
//				store : Ext.create('mvc.combo.tp.TpOAccType'),
//				value : 1
//			}),{
//				xtype : 'textfield',
//				name : 'bean.name',
//				fieldLabel : '名称'
//			},{
//				xtype : 'numberfield',
//				name : 'bean.size',
//				fieldLabel : '附件大小',decimalPrecision : 2
//			},{
//				xtype : 'hiddenfield',
//				name : 'bean.pkey'
//			});
		var formFlds = [];
		formFlds.push({
			xtype : 'hiddenfield',
			name : 'bean.pkey'
		});
	if (this.insFlag)
		formFlds.push({
            xtype: 'filefield',
	        name: 'file',
	        fieldLabel: '附件',
	        msgTarget: 'side',
	        buttonText: '浏览',
	        afterLabelTextTpl : required,
			allowBlank : false,
			listeners : {
				scope : this,
				change : function(field,newv,oldv,opts) {
					var nfield = this.down('#form_fobasefile_name');
					if (typeof newv != "undefined" && newv) {
						//IE浏览器会取全路径
						if (newv.indexOf('\\') != -1){
							var tmps = newv.split('\\'); 
							nfield.setValue(tmps[tmps.length-1]);
						}else
						nfield.setValue(newv);
					} else{
						nfield.setValue();
					}
				}
			}
        });
	
	formFlds.push({xtype: 'radiogroup',
		        fieldLabel: '类型',
		        vertical: true,
		        afterLabelTextTpl : required,
				allowBlank : false,
		        items: [
					{boxLabel:'文件',name:'bean.type',inputValue:'1',checked:true,width:50},
					{boxLabel:'图片',name:'bean.type',inputValue:'2',width:50},
					{boxLabel:'成果',name:'bean.type',inputValue:'3',width:50}
		        ]
		    },{
			xtype : 'textfield',
			name : 'bean.name',
			fieldLabel : '附件名称',
			itemId : 'form_fobasefile_name',
			afterLabelTextTpl : required,
			allowBlank : false
		},{
			xtype : 'hiddenfield',
			name : 'bean.tbandkeyTable',
			value : this.tbandkeyTable
		},{xtype : 'numberfield',name : 'bean.rowVersion',value : '0',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '版本',hidden : true,allowDecimals : false}
		,{
			xtype : 'hiddenfield',
			name : 'bean.tbandkeyPkey',
			value : this.tbandkeyPkey
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
