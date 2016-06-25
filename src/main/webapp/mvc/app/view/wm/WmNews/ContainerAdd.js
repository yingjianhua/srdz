Ext.define('mvc.view.wm.WmNews.ContainerAdd', {
	extend : 'Ext.panel.Panel',
	width : 318,
	height : 102,
	itemImage: null,
	initComponent : function() {
		this.style = {
				borderBottom : '1px solid #E7E7EB',
				borderLeft : '1px solid #E7E7EB',
				borderRight : '1px solid #E7E7EB'
			},
		this.items = {
			xtype : "box",
//			width : 286,
//			height : 60,
			width : 286,
			height : 70,
			style : {
//				position : "absolute",
//				left : "14px",
//				top : "20px",
				display : "block",
				marginLeft : "15px",
				marginTop : "16px",
				paddingLeft : "129px",
				paddingTop : "21px",
				border : "3px #000 dotted",
				opacity : '0.1'
			},
//			style : {
//				position : "absolute",
//				opacity : '0.2',
//				left : "165px",
//				top : "44px"
//			},
			autoEl : {
				tag : "span",
				html : '<img src="../images/b_add_01.jpg" />'
				
			}
//			autoEl : {
//				tag : "a",
//				href : "javascript:void(0)",
//				onclick : "return false;",
//				html : '<img src="../images/b_add_01.png" width="20px" height="20px" style="position: absolute;left: 126px;top: 16px">'
//					
//			}
		};
		this.callParent(arguments);
		this.addEvents('onAdd');
		this.itemImage = this.items.items[0];
		var me = this;
		this.listeners = {
			afterrender : {
				fn : function() {
					me.itemImage.getEl().on("mouseover",me.onshowOperation,me);
					me.itemImage.getEl().on("mouseout",me.onhideOperation,me);
					me.itemImage.getEl().on("click",me.onAdd,me);
				}
			}
		}
	},
	onshowOperation: function(){
		this.itemImage.getEl().dom.style.opacity = "0.3";
//		this.itemImage.getEl().dom.style.border = "#b0b0b0 dotted";
		//console.log("show")
	},
	onhideOperation : function() {
		this.itemImage.getEl().dom.style.opacity = "0.1";
//		this.itemImage.getEl().dom.style.border = "#cdcdcd dotted";
		//console.log("hide")
	},
	onAdd: function() {
		this.fireEvent('onAdd');
	}	
});