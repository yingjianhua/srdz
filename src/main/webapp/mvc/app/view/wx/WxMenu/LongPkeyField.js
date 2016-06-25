Ext.define('mvc.view.wx.WxMenu.LongPkeyField', {
    extend: 'Ext.form.field.Trigger',
    trigger1Cls: Ext.baseCSSPrefix + 'form-clear-trigger',
    trigger2Cls: Ext.baseCSSPrefix + 'form-search-trigger',
    fieldLabel : '素材',
    bean : null,
	beanType : null,
    data :[
            { text: "投票", beanType : "wa", bean : "WaActVote", leaf: true },
            { text: "图文秀", beanType : "wp", bean : "WpArtShow", leaf: true },
           { text: "福利", beanType : "wax", bean : "WaxBnft", leaf: true }
       ],
    onTrigger2Click: function () {
    	var me = this;
    	var store = Ext.create('Ext.data.TreeStore', {
    		proxy : new Ext.data.MemoryProxy(),
    		fields : ["text", "index", "beanType", "bean", "leaf"],
    	    root: {
    	        expanded: true,
    	        children: me.data
    	    }
    	});

    	var win = Ext.create("Ext.window.Window",{
    		title : me.title,
    		width: 850,
    	    height: 400,
    	    layout: 'border',
    	    items: [{
    	        // xtype: 'panel' implied 	by default
    	        region:'west',
    	        xtype: 'treepanel',
    	        border : true ,
    	        width: 150,
    	        rootVisible : false,
    	        store : store,
    	        layout: 'fit',
    	        listeners : {
    	        	// Ext.view.View this, Ext.data.Model record, HTMLElement item, Number index, Ext.EventObject e, Object eOpts
    	        	itemclick : function(treepanel, record, item, index, e, eOpts) {
    	        		var TriggerList = Ext.create("mvc.view." + record.get("beanType") + "."+ record.get("bean") + ".TriggerList",{
    	        			width : 700,
    	        			height : 400
    	        		});
    	        		me.bean = record.get('bean')
    	        		me.beanType = record.get('beanType')
    	        		TriggerList.on('trigger', win.onTrigger, win);
    	        		var center = win.down("[region=center]")
    	        		center.removeAll();
    	        		center.add(TriggerList);
    	        		TriggerList.getStore().load();
    	        	}
    	        }
    	    },{
    	        region: 'center',
    	        xtype: 'panel',
    	        layout: 'fit',
    	        border : true ,
    	    }],
    	    onTrigger : function(data, params) {
    	    	var pkey = data.split(bean_split)[0]
    	    	var text = data.split(bean_split)[1]
    	    	var url;
    	    	Ext.Ajax.request({
    	    		async : false,
					url : base_path+'/wx_WxMenu_getWebPath',
					data : {
						sarg1 : "expand_"+me.beanType+"_"+me.bean+"_show?pkey="+pkey
					},
					success : function (response, options) {
						var result = Ext.decode(response.responseText);
						if (result.webpath){
							url = result.webpath+"/expand_"+me.beanType+"_"+me.bean+"_show?pkey="+pkey;
							me.setValue(url)
							console.log(url)
						}else{
							Ext.MessageBox.show({
								title : msg_title, 
								msg : result.msg,
								buttons : Ext.MessageBox.OK,
								icon : Ext.MessageBox.ERROR
							});
						}
					}
				});
    	    	this.close();
    	    }
    	})
    	win.show();
    },
});