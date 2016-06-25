Ext.define('mvc.view.portal.ImagePortlet', {
	extend : 'Ext.panel.Panel',
	id: 'images-view',
	bodyPadding : 10,
	border : false,
	items: Ext.create('Ext.view.View', {
        store: Ext.create('mvc.store.Image'),
        tpl: [
            '<tpl for=".">',
                '<div class="thumb-wrap" id="{name:stripTags}">',
                    '<div class="thumb"><img src="'+base_path+'/extfile/images/mvc/mod_{url}.png" title="{name}"></div>',
                    '<h3 style="text-align:center;">{name}</h3>',
                '</div>',
            '</tpl>',
            '<div class="x-clear"></div>'
        ],
        height: 200,
        trackOver: true,
        overItemCls: 'x-item-over',
        itemSelector: 'div.thumb-wrap',
        listeners: {
        	itemclick : function(dv,nodes) {
        		Ext.getCmp("myWest").show();
        		Ext.getCmp("myWest").reload([nodes.data.url]);
        	}
        }
    })
});
