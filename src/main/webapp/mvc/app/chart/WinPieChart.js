Ext.define('mvc.chart.WinPieChart', {
	extend : 'Ext.window.Window',
	width : 800,
	height : 600,
	minHeight : 400,
	minWidth : 550,
	hidden : false,
	maximizable : true,
	title : '饼图',
	layout : 'fit',
	store : null, // 默认由List传参过来
	pData : null,
	pName : null,
	initComponent : function() {
		var me = this;
		var d = me.pData;
		var n = me.pName;
		this.items = {
			xtype : 'chart',
			style : 'background:#fff',
			animate : true,
			shadow : true,
			store : me.store,
			legend: {
				position: 'right'
            },
			// 每子块对应store的一组数据
			 series : [{
                type: 'pie',
                field: d,
                showInLegend: true,
                donut: false,
                tips: {
                  trackMouse: true,
                  width: 140,
                  height: 28,
                  renderer: function(storeItem, item) {
                    var total = 0;
                    store.each(function(rec) {
                        total += rec.get(d);
                    });
                    this.setTitle(storeItem.get(n) + ': ' + Math.round(storeItem.get(d) / total * 100) + '%');
                  }
                },
                highlight: {
                  segment: {
                    margin: 20
                  }
                },
                label: {
                    field: n,
                    display: 'rotate',
                    contrast: true,
                    font: '18px Arial'
                }
            }]
		}
		this.callParent(arguments);
	}
});