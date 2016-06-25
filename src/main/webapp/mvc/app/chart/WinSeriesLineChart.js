Ext.define('mvc.chart.WinSeriesLineChart', {
	extend : 'Ext.window.Window',
	width : 800,
	height : 600,
	minHeight : 400,
	minWidth : 550,
	hidden : false,
	maximizable : true,
	title : '折线图',
	layout : 'fit',
	store : null, // 默认由List传参过来
	xtitle : null,
	ytitle : null,
	xfield : null,
	yfield : null,
	itemtitle : null,
	initComponent : function() {
		var x = this.xfield;
		var y = this.yfield;
		var it = this.itemtitle;
		this.items = {
			xtype : 'chart',
			style : 'background:#fff',
			animate : true,
			shadow : true,
			store : this.store,
//			legend : true,
			legend: {
				position: 'right'
            },
			axes : [ {
				type : 'Numeric',
				position : 'left',
				fields : y,
				label : {
					renderer : Ext.util.Format.numberRenderer('0.00')
				},
				title : this.ytitle,
				grid : true,
				minimum : 0
			}, {
				type : 'Category',
				position : 'bottom',
				fields : [x],
				title : this.xtitle
			}],
			// 每子块对应store的一组数据
			 series : this.getSeries(x, y, it)
		}
		this.callParent(arguments);
	},
	getSeries : function(x, y, it) {
		var ser = [];
		for (i = 0; i < y.length; i++) {
			var j = i;
			ser.push({
				type : 'line',
				highlight : {
					size : 7,
					radius : 7
				},
				axis : 'left',
//				smooth: true,//线条圆弧
				xField : x,
				yField : y[i],
				title : it[i],
				fill : true, // 线条以下空间填充
//				fillOpacity: 0.5,
				// 数值点样式
				markerConfig : {
					type : 'circle',// type : 'cross'是*符号
					size : 4,
					radius : 4,
					'stroke-width' : 0
				},
				// 折线样式
				// style: { 
				// stroke: '#00ff00',
				// 'stroke-width': 10,
				// fill: '#80A080',
				// opacity: 0.2
				// },
				// 数值点鼠标经过描述内容渲染
				tips : {
					trackMouse : true,
					width : 140,
					height : 28,
					renderer : function(storeItem, item) {
						this.setTitle(item.value[1] % 1 == 0 ? item.value[1] : Ext.util.Format.number(item.value[1], '0.00'));
						this.setWidth(80);
					}
				}
			});
		}
		return ser;
	}
});