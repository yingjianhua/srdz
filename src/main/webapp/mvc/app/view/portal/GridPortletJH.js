Ext.define('mvc.view.portal.GridPortletJH', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.gridportletjh',
    uses: [
        'Ext.data.ArrayStore'
    ],
    height: 260,
    myData: [
             ['水平衡测试项目合同签署',  '2014-09-25'],
             ['9万吨/年顶二烯装置项目需求初稿拟定',  '2014-09-26'],
             ['9万吨/年顶二烯装置项目车间人员安排',  '2014-09-27']
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
                id       :'companyjh',
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
