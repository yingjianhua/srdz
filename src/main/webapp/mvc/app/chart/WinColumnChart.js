Ext.define('mvc.chart.WinColumnChart', {
	extend : 'Ext.window.Window',
	width: 800,
    height: 600,
    minHeight: 400,
    minWidth: 550,
    hidden: false,
    maximizable: true,
    title: '图表',
    layout: 'fit',
    store:null, //默认由List传参过来
    xtitle:null,
    ytitle:null,
    xfield:null,
    yfield:null,
    //tbar功能栏，根据实际情况确定是否使用
    initComponent : function() {
    	var x = this.xfield;
    	var y = this.yfield;
    	this.items= {
	        xtype: 'chart',
	        style: 'background:#fff',
	        animate: true,
	        shadow: true,
	        store: this.store,
	        axes: [{ //用来配置坐标，可以配置多个坐标
	            type: 'Numeric', //配置坐标的类型
	            position: 'left', //配置坐标的位置
	            fields: [this.yfield], //可以配置多个字段，用来设置坐标显示的值
	            label: { //可以配置文字的显示方式
	                renderer: Ext.util.Format.numberRenderer('0,0')
	            },
	            title: this.ytitle,
	            grid: true
	            //minimum: 1000 //可以配置坐标的最小最大值
	        }, {
	            type: 'Category',
	            position: 'bottom',
	            fields: [this.xfield],
	            title: this.xtitle
	        }],
	        series: [{ //用来配置图表
	            type: 'column', //配置图表的类型
	            axis: 'left', //相对于哪个坐标
	            highlight: true, //设置鼠标移动到图表上面，是否高亮，反映慢
	            tips: { //提示信息
	              trackMouse: true,
	              width: 140,
	              height: 28,
	              renderer: function(storeItem, item) {
	                this.setTitle(storeItem.get(x) + ': ' + storeItem.get(y));
	              }
	            },
	            label: { //设置图表上显示的文字
	              display: 'insideEnd',
	              'text-anchor': 'middle',
	                field: this.yfield,
	                renderer: Ext.util.Format.numberRenderer('0'),
	                orientation: 'vertical',
	                color: '#333'
	            },
	            xField: this.xfield,
	            yField: this.yfield
	        }]
	    }
		this.callParent(arguments);
	}
});