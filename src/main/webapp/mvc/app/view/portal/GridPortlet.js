Ext.define('mvc.view.portal.GridPortlet', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.gridportlet',
    uses: [
        'Ext.data.ArrayStore'
    ],
    height: 300,
    myData: [
        ['工业能源综合利用效率指标体系研究',                               	71.72, 5.02,  0.03,  '9/1 12:00am'],
        ['市科委“工业锅炉能效提升技术的研究与示范”项目',                           		29.01, 22.42,  1.47,  '9/1 12:00am'],
        ['上海市节能减排中心有限公司—中航商用航空发动机有限责任公司临港基地建设项目',                    			83.81, 53.28,  0.34,  '9/1 12:00am'],
        ['上海市节能减排中心有限公司—上海赛科石油化工有限责任公司新增9万吨/年顶二烯装置项目',            					52.55, 7.01,  0.02,  '9/1 12:00am'],
        ['广东省韶关钢铁集团有限公司锅炉全燃煤气改造项目',  							64.13, 96.31,  0.49,  '9/1 12:00am'],
        ['光明乳业有限公司华东中央工厂建设项目',                 31.61, 66.48, -1.54, '9/1 12:00am'],
        ['宝山钢铁股份有限公司无取向硅钢工程项目',                 75.43, 12.53,  0.71,  '9/1 12:00am'],
        ['上海大众汽车有限公司-水平衡测试项目',                    				67.27, 55.92,  1.39,  '9/1 12:00am']
//        ['废品能源再利用',                     		49.37, 34.02,  0.04,  '9/1 12:00am'],
//        ['新能源标准化', 							40.48, 78.51,  1.28,  '9/1 12:00am'],
//        ['ASOS',                    			68.1,  93.43, -0.64, '9/1 12:00am'],
//        ['新能源应用推广',           			 		34.14, 21.08, -0.23, '9/1 12:00am'],
//        ['新能源应用推广2',          		30.27, 18.09,  3.74,  '9/1 12:00am'],
//        ['新能源应用推广3',                 		36.53, 56.03, -0.08, '9/1 12:00am']
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
               {name: 'change',     type: 'float'},
               {name: 'pctChange',  type: 'float'}
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
                id       :'company',
                text   : '项目',
                //width: 120,
                flex: 1,
                sortable : true,
                dataIndex: 'company'
            },{
                text   : '成本(万元)',
                width    : 75,
                sortable : true,
                renderer : this.change,
                dataIndex: 'change',
                align:'right'
            },{
                text   : '进度',
                width    : 100,
                sortable : true,
                renderer : this.pctChange,
                dataIndex: 'pctChange',align:'right'
            }]
        });

        this.callParent(arguments);
    }
});
