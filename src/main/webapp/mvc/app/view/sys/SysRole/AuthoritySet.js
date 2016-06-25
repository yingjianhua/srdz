Ext.define('mvc.view.sys.SysRole.AuthoritySet', {
	extend : 'Ext.form.Panel',
	layout : {
		type : 'table',
		columns : 2
	},
	bodyPadding : 10,
	autoScroll : true,
	jyType : "sys",

	initComponent : function() {
		this.ajaxSyncCall();
		this.callParent(arguments);
	},
	getChkBoxs : function(idx, flds) {
		var chkBoxs = [];
		for (j = 0; j < flds[idx].acts.length; j++) {
			chkBoxs.push({
						boxLabel : flds[idx].acts[j].mc,
						name: 'keys',
						inputValue: flds[idx].acts[j].pkey,
						checked: flds[idx].acts[j].checked
					});
		}
		return chkBoxs;
	},

	ajaxSyncCall : function() {
		var me = this, fldSets = [];
		Ext.Ajax.request({
					async : false,
					url : base_path+ '/sys_SysMenu_loadAuthorityByRole?pkey=' + me.pkey + '&jyType='
							+ me.jyType,
					method : 'GET',
					success : function(response) {
						var flds = Ext.JSON.decode(response.responseText, true);
						for (i = 0; i < flds.length; i++) {
							fldSets.push({
										xtype : 'fieldsetcheck',
										title : flds[i].title,
										checkboxToggle : true,
										collapsed : false,
										defaultType : 'textfield',
										layout : 'anchor',
										itemCls : 'x-layout-fieldset-checkboxgroup',
										margin : 10,
										items : [{
													xtype : 'checkboxgroup',
													columns : 3,
													items : me.getChkBoxs(i,
															flds)
												}],
										onCheckChange : function() {
											if(this.checkboxCmp.getValue())
												this.checkAll(true);
											else
												this.checkAll(false);
										},
										checkAll : function(checked){
											this.items.each(function(cbg){
												cbg.items.each(function(item){
													item.setValue(checked);
												});
											});
										}
									});
						}
						me.items = fldSets;
					},
					failure : function(response) {
						Ext.example.msg(msg_title, msg_ajax);
					}
				});
	}
});