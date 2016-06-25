Ext.define('mvc.view.wm.WmNews.WinTemplate', {
			extend : 'Ext.window.Window',
			width : 950,
			autoScroll:true,
			height : '80%',
			pkey : null,
			layout : {
				type : 'hbox',
				pack : 'start',
				align : 'stretchmax'
			},
			form : null,
			resizable : true,
			url : base_path + '/wm_WmNews_',
			modal : true,
			iconCls : 'app-icon',
			insFlag : true,
			articles : null,
			form : null,
			initComponent : function() {
				var me = this;
				var articles = []
				if (me.insFlag)
					me.url = me.url + 'ins';
				else
					me.url = me.url + 'upd';
				var addpanel = Ext.create('mvc.view.wm.WmNews.ContainerAdd');
				addpanel.on("onAdd", me.onAdd, me);
				this.items = [
					Ext.create('Ext.panel.Panel', {
						margin : 10,
//						border : 1,
//						style: {
//						    borderColor: '#E7E7EB',
//						    borderStyle: 'solid'
//						},
						layout : {
							type : 'vbox',
							pack : 'start',
							align : 'stretchmax'
						},
						items : addpanel
					}), {
						anchor : '100%',
						plain : true,
						xtype : Ext.create(
							'mvc.view.wm.WmNews.Form', {
								layout : 'anchor',
								insFlag : me.insFlag
							})
					}];
				this.buttonAlign = 'right', this.buttons = [{
							text : '重置',
							scope : this,
							iconCls : 'win-refresh-icon',
							handler : this.onReset
						}, {
							text : '关闭',
							scope : this,
							iconCls : 'win-close-icon',
							handler : this.onClose
						}, {
							text : '保存',
							scope : this,
							iconCls : 'win-save-icon',
							handler : this.onSave
						}];
				this.callParent(arguments);
				this.addEvents('create');
				this.articles = this.items.items[0];
				this.form = this.items.items[1];
				this.listeners = {
					beforerender : {
						fn : function() {
							if (!me.insFlag) {
								var data;
								Ext.Ajax.request({
									async : false,
									url : base_path+ '/wm_WmNews_load?pkey='+ me.pkey,
									success : function(response, options) {
										var result = Ext.decode(response.responseText);
										if (result.success) {
											data = result;
										} else {
											Ext.MessageBox.show({
												title : msg_title,
												msg : result.msg,
												buttons : Ext.MessageBox.OK,
												icon : Ext.MessageBox.ERROR
											});
										}
									}
								});
								for (var i = 0; i < data.count; i++) {
									var com = Ext.create('mvc.view.wm.WmNews.ContainerPic',{
											data : data.items[i],
											main : i == 0
										})
									com.on("onEdit", me.onEdit, me)
									com.on("onDel", me.onDel, me)
									me.articles.insert(i,com)
								}
							} else {
								var com = Ext.create('mvc.view.wm.WmNews.ContainerPic',{
										data : {"bean.imageUrl": "/images/empty.png","bean.title":"标题","bean.type":0,"bean.showCoverPic":0},
										main : true
									})
								com.on("onEdit", me.onEdit, me)
								com.on("onDel", me.onDel, me)
								me.articles.insert(0,com);
							}
						}
					},
					afterrender : {
						fn : function() {
							me.onEdit(me.articles.items.items[0]);
						}
					}
				}
			},
			setActiveRecord : function(record, article) {
				if (this.form.article === article) {
					//console.log("该文章已在编辑状态,不做任何处理");
					return;
				}
				if (this.form.article) {
					//console.log("编辑文章切换，当前编辑文章内容保存中");
					this.form.article.onSetRecord(this.form.getValues());
				}
				this.form.article = article;
				this.form.activeRecord = record;
				if (record || this.form.activeRecord) {
					this.form.getForm().loadRecord(record);
				} else {
					this.form.getForm().reset();
				}
			},
			onReset : function() {
				var picpanel = this.items.items[0].items.items[0];
				var img = picpanel.items.items[0];
				// console.log(img.el);
				// img.el.on('mouseover',this.onMouseover)
				// this.setActiveRecord(this.form.activeRecord);
			},
			onAdd : function() {
				var me = this;
				if(me.articles) {
					var com = Ext.create('mvc.view.wm.WmNews.ContainerPic',{
						data : {"bean.imageUrl": "/images/empty.png","bean.title":"标题","bean.type":0,"bean.showCoverPic":0},
						main : false
					})
					com.on("onEdit", me.onEdit, me)
					com.on("onDel", me.onDel, me)
					me.articles.insert(me.articles.items.items.length-1,com);
					me.onEdit(com);
				}
			},
			onEdit : function(article) {
				var data = article.data;
				var record = Ext.create("mvc.model.wm.WmNews", data);
				this.setActiveRecord(record, article);
			},
			onDel : function(article) {
				this.articles.remove(article);
			},
			onClose : function() {
				this.close();
			},
			onSave : function() {
				var me = this;
				me.form.article.onSetRecord(me.form.getValues());
				var store = Ext.create("mvc.store.wm.WmNews");
				console.log(me.articles.items.items.length);
				Ext.each(me.articles.items.items, function(article) {
					if(article.data) {
						if(article.data["bean.title"] && article.data["bean.title"].length>64) {Ext.example.msg("保存失败", "标题长度不能超过64");console.log(article.data);me.onEdit(article);return;}
						if(article.data["bean.author"] && article.data["bean.author"].length>8) {Ext.example.msg("保存失败", "作者长度不能超过8");console.log(article.data);me.onEdit(article);return;}
						if(article.data["bean.summary"] && article.data["bean.summary"].length>120) {Ext.example.msg("保存失败", "摘要长度不能超过120");console.log(article.data);me.onEdit(article);return;}
						store.add(Ext.create("mvc.model.wm.WmNews", article.data));
					}
				});
				var form = me.form.getForm();
				Ext.Ajax.request({
							url : me.url,
							params : mvc.Tools.storeValues(store, {
										insFlag : me.insFlag
									}),
							success : function(response, options) {
								var result = Ext.decode(response.responseText);
								if (result.success) {
									me.fireEvent('create', me, result);
									me.close();
								} else {
									Ext.MessageBox.show({
												title : msg_title,
												msg : result.msg,
												buttons : Ext.MessageBox.OK,
												icon : Ext.MessageBox.ERROR
											});
								}
							}
						});
			}
		});