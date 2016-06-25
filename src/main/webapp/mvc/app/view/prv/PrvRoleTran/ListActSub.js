Ext.define('mvc.view.prv.PrvRoleTran.ListActSub', {
	extend : 'Ext.panel.Panel',
	loadMask : true,
	infos : '',
	defaults : {
		style : 'background:none; border-right: #157fcc 1px solid;border-top: 0px solid;border-left: 0px solid;border-bottom: #157fcc 1px solid;'
	},
	layout : {
		type : 'hbox'
	},
	initComponent : function() {
		var cmpActs = [];
		var cmpActsSub = [];
		cmpActs.push({
					xtype : 'combo',
					name : 'type',
					mode : 'local',
					valueField : 'value',
					triggerAction : 'all',
					forceSelection : true,
					typeAhead : true,
					editable : false,
					emptyText : form_empty_text,
					value : this.infos.type,
					store : Ext.create('mvc.combo.prv.PrvOPrvType'),
					margin : '0 10 0 0'
				});
		cmpActsSub.push({
					xtype : 'numberfield',
					value : this.infos.day,
					margin : '0 10 0 0'
				});
		this.items = [{
					xtype : 'panel',
					action : 'pkey',
					items : {
						xtype : 'numberfield',
						value : this.infos.pkey
					},
					hidden : true,
					flex : 0
				}, {
					xtype : 'panel',
					height : 50,
					layout : {
						type : 'hbox',
						padding : '5',
						pack : 'center',
						align : 'middle'
					},
					items : {
						xtype : 'label',
						html : '<font size="3">'
								+ this.infos.grp.split(bean_split)[1]
								+ '</font>'
					},
					flex : 1.7
				}, {
					xtype : 'panel',
					height : 50,
					action : 'type',
					layout : {
						type : 'hbox',
						padding : '5',
						pack : 'center',
						align : 'middle'
					},
					items : cmpActs,
					flex : 1.7
				}, {
					xtype : 'panel',
					height : 50,
					action : 'day',
					layout : {
						type : 'hbox',
						padding : '5',
						pack : 'center',
						align : 'middle'
					},
					items : cmpActsSub,
					flex : 1.7
				}];
		this.callParent(arguments);
	}
});