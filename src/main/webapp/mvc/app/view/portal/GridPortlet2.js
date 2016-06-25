Ext.define('mvc.view.portal.GridPortlet2', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.gridportlet2',
    uses: [
        'Ext.data.ArrayStore'
    ],
    height: 300,
    myData: [
        ['工业能源综合利用效率指标体系研究',                               				75.02,  '2014-09-02',  '9/1 12:00am'],
        ['市科委“工业锅炉能效提升技术的研究与示范”项目',                           		42.42,  '2014-08-02',  '9/1 12:00am'],
        ['上海市节能减排中心有限公司—中航商用航空发动机有限责任公司临港基地建设项目',                 63.28,  '2014-07-12',  '9/1 12:00am'],
        ['上海市节能减排中心有限公司—上海赛科石油化工有限责任公司新增9万吨/年顶二烯装置项目',           87.01, '2014-07-01',  '9/1 12:00am'],
        ['广东省韶关钢铁集团有限公司锅炉全燃煤气改造项目',  									16.31,  '2014-06-06',  '9/1 12:00am'],
        ['光明乳业有限公司华东中央工厂建设项目',                 						6.48, '2014-05-02', '9/1 12:00am'],
        ['宝山钢铁股份有限公司无取向硅钢工程项目',                 						12.53, '2014-04-15',  '9/1 12:00am'],
        ['上海大众汽车有限公司-水平衡测试项目',                    						85.92,  '2013-12-02',  '9/1 12:00am']
//        ['IBM',                     		'2200157873', 24.02,  0.04,  '9/1 12:00am'],
//        ['淘宝', 							'2200157194', 76.51,  1.28,  '9/1 12:00am'],
//        ['腾讯',                    			'2200157753',  33.43, -0.64, '9/1 12:00am'],
//        ['世纪互联',           			 		'2200157951', 41.08, -0.23, '9/1 12:00am'],
//        ['苹果',          		'2200157852', 19.09,  3.74,  '9/1 12:00am'],
//        ['微软',                 		'22001578357', 50.03, -0.08, '9/1 12:00am']
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
               {name: 'change',  type: 'float'},
               {name: 'date'}
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
                id       :'company2',
                text   : '项目',
                //width: 120,
                flex: 1,
                sortable : true,
                dataIndex: 'company'
            },
            {
                text   : '回款(万元)',
                width    : 100,
                sortable : true,
                renderer : this.change,
                dataIndex: 'change',align:'right'
            },
	          {
	          text   : '回款日期',
	          width    : 75,
	          sortable : true,
	          width : 100,
	          dataIndex: 'date'
	      }
            ]
        });

        this.callParent(arguments);
    }
});
