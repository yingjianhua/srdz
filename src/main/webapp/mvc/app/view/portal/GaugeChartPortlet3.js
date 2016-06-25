Ext.define('mvc.view.portal.GaugeChartPortlet3', {

    extend: 'Ext.panel.Panel',
    alias: 'widget.gaugechartportlet3',

    requires: [
               'Ext.chart.*',
               'Ext.chart.axis.Gauge',
               'Ext.chart.series.*',
               'Ext.Window'
    ],

    generateData: function(n){
        var data = [{
        		data1 : n
            }];
        return data;
    },

    initComponent: function(){

        Ext.apply(this, {
            width: 800,
            height: 250,
            minWidth: 350,
            minHeight: 225,
//            title: 'Gauge Charts',
            layout: {
                type: 'hbox',
                align: 'stretch'
            },
            items: [{
                xtype: 'chart',
                style: 'background:#fff',
                animate: true,
                store: Ext.create('Ext.data.JsonStore', {
                    fields: ['data1'],
                    data: this.generateData(53)
                }),
                insetPadding: 25,
                flex: 1,
                axes: [{
                    type: 'gauge',
                    position: 'gauge',
                    minimum: 0,
                    maximum: 100,
                    steps: 10,
                    margin: 7
                }],
                series: [{
                    type: 'gauge',
                    field: 'data1',
                    donut: 30,
                    colorSet: ['#82B525', '#ddd']
                }]
            }]
        });

        this.callParent(arguments);
    }
});
