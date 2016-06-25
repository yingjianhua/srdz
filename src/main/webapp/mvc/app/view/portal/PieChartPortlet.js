Ext.define('mvc.view.portal.PieChartPortlet', {

    extend: 'Ext.panel.Panel',
    alias: 'widget.piechartportlet',

    requires: [
               'Ext.chart.*',
               'Ext.layout.container.Fit',
               'Ext.window.MessageBox',
               'Ext.chart.axis.*',
               'Ext.chart.series.*',
               'Ext.Window'
    ],

    initComponent: function(){
    	
    	var store1 = Ext.create('Ext.data.JsonStore', {
            fields: ['name','data1'],
            data: [
                   {name:'工业能源研究',data1:111},
                   {name:'工业锅研究',data1:222},
                   {name:'临港基地建设项目',data1:333},
                   {name:'年顶二烯装置项目',data1:444},
                   {name:'锅炉全燃煤气改造项目',data1:111},
                   {name:'华东中央工厂建设项目',data1:222},
                   {name:'无取向硅钢工程项目',data1:333},
                   {name:'水平衡测试项目',data1:444}
                   ]
        });

        Ext.apply(this, {
            width: 800,
            height: 250,
            minWidth: 350,
            minHeight: 225,
            layout: 'fit',
        	items: [{
        		xtype: 'chart',
                animate: true,
                store: store1,
                shadow: true,
                legend: {
                    position: 'right'
                },
                insetPadding: 10,
                theme: 'Base:gradients',
                series: [{
                    type: 'pie',
                    field: 'data1',
                    showInLegend: true,
                    donut: false,
                    tips: {
                      trackMouse: true,
                      width: 140,
                      height: 28,
                      renderer: function(storeItem, item) {
                        //calculate percentage.
                        var total = 0;
                        store1.each(function(rec) {
                            total += rec.get('data1');
                        });
                        this.setTitle(storeItem.get('name') + ': ' + Math.round(storeItem.get('data1') / total * 100) + '%');
                      }
                    },
                    highlight: {
                      segment: {
                        margin: 20
                      }
                    },
                    label: {
                        field: 'name',
                        display: 'rotate',
                        contrast: true,
                        font: '12px Arial'
                    }
                }]
        	}]
        });

        this.callParent(arguments);
    }
});
