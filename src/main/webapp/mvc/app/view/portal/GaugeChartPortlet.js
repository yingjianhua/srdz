Ext.define('mvc.view.portal.GaugeChartPortlet', {

    extend: 'Ext.panel.Panel',
    alias: 'widget.gaugechartportlet',

    requires: [
               'Ext.data.JsonStore',
               'Ext.chart.*',
               'Ext.chart.axis.Gauge',
               'Ext.chart.series.*',
               'Ext.Window'
    ],

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
                animate: {
                    easing: 'elasticIn',
                    duration: 1000
                },
                store: Ext.create('Ext.data.JsonStore', {
                    fields: ['data1'],
                    data: [{data1 : 21}]
                }),
                insetPadding: 25,
                flex: 1,
                axes: [{
                    type: 'gauge',
                    position: 'gauge',
                    minimum: 0,
                    maximum: 100,
                    steps: 10,
                    margin: -10
                }],
                series: [{
                    type: 'gauge',
                    field: 'data1',
                    donut: false,
                    colorSet: ['#F49D10', '#ddd']
                }]
            }]
        });

        this.callParent(arguments);
    }
});
