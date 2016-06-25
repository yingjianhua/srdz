Ext.define('mvc.view.portal.GridPortlet4', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.gridportlet4',
    uses: [
        'Ext.data.ArrayStore'
    ],
    height: 260,
    myData: [
        ['工业能源综合利用效率指标体系研究',                               	'第一阶段研究报告',  '2014-09-21'],
        ['市科委“工业锅炉能效提升技术的研究与示范”项目',                           		'部署完成',  '2014-08-31'],
        ['上海市节能减排中心有限公司—中航商用航空发动机有限责任公司临港基地建设项目',                    			'实施完成',  '2014-05-06'],
        ['上海市节能减排中心有限公司—上海赛科石油化工有限责任公司新增9万吨/年顶二烯装置项目',            					'部署完成',  '2017-09-17'],
        ['广东省韶关钢铁集团有限公司锅炉全燃煤气改造项目',  							'改造验收报告',  '2014-08-28'],
        ['光明乳业有限公司华东中央工厂建设项目',                 '部署完成', '2014-04-03'],
        ['宝山钢铁股份有限公司无取向硅钢工程项目',                 '验收报告',  '2014-06-11'],
        ['上海大众汽车有限公司-水平衡测试项目',                    				'平衡指标报告',  '2014-07-24']
//        ['废品能源再利用',                     		'可回收废片标准确定', -34.02,  0.04,  '9/1 12:00am'],
//        ['新能源标准化', 							'相关部门批准下达', 78.51,  1.28,  '9/1 12:00am'],
//        ['ASOS',                    			'项目完成验收',  93.43, -0.64, '9/1 12:00am'],
//        ['新能源应用推广',           			 		'第一阶段成效达成', -21.08, -0.23, '9/1 12:00am'],
//        ['新能源应用推广2',          		'第二阶段成效达成', -18.09,  3.74,  '9/1 12:00am'],
//        ['新能源应用推广3',                 		'第三阶段成效达成', 56.03, -0.08, '9/1 12:00am']
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
               {name: 'change'},
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
                id       :'company4',
                text   : '项目',
                //width: 120,
                flex: 1,
                sortable : true,
                dataIndex: 'company'
            },{
                text   : '交付物',
                width    : 150,
                sortable : true,
                dataIndex: 'change'
            },{
                text   : '交付时间',
                width    : 100,
                sortable : true,
//                renderer : this.change,
                dataIndex: 'pctChange'
            }]
        });

        this.callParent(arguments);
    }
});
