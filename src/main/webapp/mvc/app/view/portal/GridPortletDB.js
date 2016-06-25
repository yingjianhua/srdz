Ext.define('mvc.view.portal.GridPortletDB', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.gridportletdb',
    uses: [
        'Ext.data.ArrayStore'
    ],
    height: 260,
    myData: [
        ['研究预算审批',  '2014-09-21'],
        ['部署华东中央工厂建设项目',  '2014-08-31'],
        ['水泥采购',  '2014-09-06'],
        ['华东中央工厂建设项目二期策略检验',  '2017-09-17']
    ],

    /**
     * Custom function used for column renderer
     * @param {Object} val
     */
    change: function(val) {
        if (val > 0) {
            return '<span style="color:green;">' + val + '</span>';
        } else if (val < 0) {
            return '<span style="color:red;">' + val + '</span>';
        }
        return val;
    },

    /**
     * Custom function used for column renderer
     * @param {Object} val
     */
    pctChange: function(val) {
        if (val > 0) {
            return '<span style="color:green;">' + val + '%</span>';
        } else if (val < 0) {
            return '<span style="color:red;">' + val + '%</span>';
        }
        return val;
    },

    initComponent: function(){

        var store = Ext.create('Ext.data.ArrayStore', {
            fields: [
               {name: 'company'},
               {name: 'pctChange'}
            ],
            data: this.myData
        });

        Ext.apply(this, {
            //height: 300,
            height: this.height,
            store: store,
            stripeRows: true,
            columnLines: true,
            columns: [{
                id       :'companydb',
                text   : '事项',
                //width: 120,
                flex: 1,
                sortable : true,
                dataIndex: 'company'
            },{
                text   : '时间',
                width    : 100,
                sortable : true,
                dataIndex: 'pctChange'
            }]
        });

        this.callParent(arguments);
    }
});
