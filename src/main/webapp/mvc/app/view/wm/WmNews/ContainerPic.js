Ext.define('mvc.view.wm.WmNews.ContainerPic', {
	extend : 'Ext.panel.Panel',
	width : 320,
	height : 160,
	main : true,
	data : null,
//	border : "1 1 0 0",
	itemImage: null,
	itemText : null,
	itemEdit : null,
	itemDel : null,
	initComponent : function() {
		var content = [];
		if (this.main) {
			this.height = 160,
			this.style = {
				borderTop : '1px solid #E7E7EB',
				borderLeft : '1px solid #E7E7EB',
				borderRight : '1px solid #E7E7EB'
				
			},
			content.push({
						xtype : "box",
						width : 320,
						height : 160,
						style : {
							position : "absolute",
							left : "0px",
							top : "0px"
						},
						autoEl : {
							tag : "img",
							src : "../" + this.data["bean.imageUrl"]
						}
					});
			content.push({
						xtype : "box",
						style : {
							color : "white",
							margin : '0px',
							padding : '8px',
							width : "320px",
							height : "28px",
							background : 'rgba(0,0,0,0.6)',
							position : "absolute",
							left : "0px",
							bottom : "0px"
						},
						autoEl : {
							tag : "h4",
							html : this.data["bean.title"]
						}
					});
			content.push({
				xtype : "box",
				width : 320,
				height : 160,
				hidden : true,
				style : {
					display : 'block',
					backgroundColor : '#000',
					opacity : '0.2',
					position : "absolute",
					left : "0px",
					top : "0px"
				},
				autoEl : {
					tag : "div"
				}
			});
			content.push({
				xtype : "box",
				width : 20,
				height : 20,
				hidden : true,
				style : {
					position : "absolute",
					opacity : '0.2',
					left : "150px",
					top : "70px"
				},
				autoEl : {
					tag : "img",
					src : '../images/pencil.png'
				}
			});
			content.push({
				xtype : "box",
				width : 30,
				height : 30,
				hidden : true,
				style : {
					position : "absolute",
					opacity : '0.2',
					left : "44px",
					top : "20px"
				},
				autoEl : {
					tag : "img",
					src : '../images/trash.png'
				}
			});
//			content.push({
//						xtype : "button",
//						text : "编辑",
//						scope : this,
//						handler : this.onEdit
//			}); 
//			content.push({
//						xtype : "button",
//						text : "删除",
//						hidden : true
//			});
		} else {
			this.height = 118;
			this.style = {
					borderTop : '1px solid #E7E7EB',
					borderLeft : '1px solid #E7E7EB',
					borderRight : '1px solid #E7E7EB'
				},
			content.push({
						xtype : "box",
						width : 78,
						height : 78,
						style : {
							position : "absolute",
							right : "14px",
							top : "20px"
						},
						autoEl : {
							tag : "img",
							src : "../" + this.data["bean.imageUrl"]
						}
					});
			content.push({
						xtype : "box",
						style : {
							display : "block",
							wordWrap: "break-word",
							wordBreak: "break-all",
							color : "rgb(102,102,102)",
							margin : '24px 0px 0px 14px',
							width : "198px",
							heigth : "48px",
							position : "absolute",
							left : "0px"
						},
						autoEl : {
							tag : "h4",
							html : this.data["bean.title"]
						}
					});
			content.push({
				xtype : "box",
				width : 320,
				height : 118,
				hidden : true,
				style : {
					display : 'block',
					backgroundColor : '#000',
					opacity : '0.2',
					position : "absolute",
					left : "0px",
					top : "0px"
				},
				autoEl : {
					tag : "div"
				}
			});
			content.push({
				xtype : "box",
				width : 20,
				height : 20,
				hidden : true,
				style : {
					position : "absolute",
					opacity : '0.2',
					left : "125px",
					top : "49px"
				},
				autoEl : {
					tag : "img",
					src : '../images/pencil.png'
				}
			});
			content.push({
				xtype : "box",
				width : 20,
				height : 20,
				hidden : true,
				style : {
					position : "absolute",
					opacity : '0.2',
					left : "175px",
					top : "49px"
				},
				autoEl : {
					tag : "img",
					src : '../images/trash.png'
				}
			});
//			content.push({
//						xtype : "button",
//						text : "编辑",
//						scope : this,
//						handler : this.onEdit
//			});
//			content.push({
//						xtype : "button",
//						text : "删除",
//						scope : this,
//						handler : this.onDel
//			})
		}
		//<a class="icon18_common edit_gray js_edit" href="javascript:void(0);" onclick="return false;" data-id="2">编辑</a>
		//<a class="icon18_common del_gray js_del"   href="javascript:void(0);" onclick="return false;" data-id="2">删除</a>
		this.items = content;
		this.callParent(arguments);
		this.addEvents('onEdit');
		this.addEvents('onDel');
		this.itemImage = this.items.items[0];
		this.itemText = this.items.items[1];
		this.itemCover = this.items.items[2];
		this.itemEdit = this.items.items[3];
		this.itemDel  = this.items.items[4];
		var me = this;
		/*this.listeners = {
			afterrender : {
				fn : function() {
					me.itemImage.getEl().on("mouseover",me.onshowOperation);
					me.itemImage.getEl().on("mouseout",me.onhideOperation);
				}
			}
		}*/
		this.listeners = {
				afterrender : {
					fn : function() {
						me.getEl().on("mouseover",me.onCoverOperation,me);
						me.getEl().on("mouseout",me.onDiscoverOperation,me);
						me.itemEdit.getEl().on("mouseover",me.onEditLight,me);
						me.itemEdit.getEl().on("mouseout",me.onEditDark,me);
						me.itemDel.getEl().on("mouseover",me.onDelLight,me);
						me.itemDel.getEl().on("mouseout",me.onDelDark,me);
						me.itemEdit.getEl().on("click",me.onEdit,me);
						me.itemDel.getEl().on("click",me.onDel,me);
					}
				}
			}
	},
	
	onCoverOperation: function(){
		this.itemCover.show();
		this.itemEdit.show();
		if (!this.main)
			this.itemDel.show();
	},
	onDiscoverOperation : function() {
		this.itemCover.hide()
		this.itemEdit.hide();
		this.itemDel.hide();
	},
	onEditLight: function(){
		this.itemEdit.getEl().dom.style.opacity = '0.8';
	},
	onEditDark : function() {
		this.itemEdit.getEl().dom.style.opacity = '0.2';
	},
	onDelLight: function(){
		this.itemDel.getEl().dom.style.opacity = '0.8';
	},
	onDelDark : function() {
		this.itemDel.getEl().dom.style.opacity = '0.2';
	},
	onEdit: function() {
		//console.log(this.itemImage.getEl());
		//console.log(this.itemText.getEl());
		this.fireEvent('onEdit',this);
	},
	onDel : function() {
		this.fireEvent('onDel',this);
	},
	
	onImageChange: function(url) {
		this.data["bean.imageUrl"]=url;
		this.itemImage.getEl().dom.src="../" + url;
	},
	
	onTitleChange: function(title) {
		this.data["bean.title"]=title;
		this.itemText.getEl().dom.innerHTML=title;
	},
	onSetRecord : function(data) {
		//console.log("文章数据已保存（未做处理）");
		this.data = data;
	}
});