Ext.define('mvc.view.prv.PrvRole.ListActSub',{
	extend : 'Ext.panel.Panel',
	loadMask : true,
	infos : '',
	defaults : {
		style:'background:none; border-right: #157fcc 1px solid;border-top: 0px solid;border-left: 0px solid;border-bottom: #157fcc 1px solid;'
	},
	layout: {
        type: 'hbox'
    },
	initComponent : function(){
		var cmpActs = [];
		var cmpActsSub = [];
		var defaultH = 50;
		for (j = 0; j < this.infos.acts.length; j++) {
			cmpActs.push({
				xtype : 'checkbox',
				boxLabel : this.infos.acts[j].mc,
				inputValue: this.infos.acts[j].pkey,
				checked: this.infos.acts[j].checked,
				margin:'0 10 0 0'
			});
			cmpActsSub.push({
				xtype : 'checkbox',
				boxLabel : this.infos.actssub[j].mc,
				inputValue: this.infos.actssub[j].pkey,
				checked: this.infos.actssub[j].checked,
				margin:'0 10 0 0'
			});
		}
		if (cmpActs.length>11)
			defaultH = 75;
		this.items = [{
			xtype : 'panel',
			height : defaultH,
			layout: {type: 'hbox',padding:'5',pack:'center',align:'middle'},
			items : {xtype:'label',html:'<font size="3">'+this.infos.title+'</font>'},
			flex : 1
		},{
			xtype : 'panel',
			height : defaultH,
			action : 'checkPanel',
			layout: {type: 'column',padding:'5',align:'middle'},
            items : cmpActs,
			flex : 2
		},
		{
			xtype : 'panel',
			height : defaultH,
			layout: {type: 'column',padding:'5',align:'middle'},
            items : cmpActsSub,
			flex : 2
		},
		];
		this.callParent(arguments);
	},
});