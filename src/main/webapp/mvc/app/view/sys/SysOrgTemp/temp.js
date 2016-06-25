Ext.onReady(function() {
			/**
			 * 同步ajax调用 返回json Object
			 * 
			 * @param {}
			 *            urlStr
			 * @param {}
			 *            paramsStr 为字符串键值对形式"key=value&key2=value2"
			 * @return {} 返回json Object
			 */
			function ajaxSyncCall(urlStr) {

				var obj;
				var result;
				// For IE
				if (window.ActiveXObject) {
					obj = new ActiveXObject('Microsoft.XMLHTTP');
				}
				// For Not IE
				else if (window.XMLHttpRequest) {
					obj = new XMLHttpRequest();
				}
				obj.onreadystatechange = function() {

					if (obj.readyState == 4 && obj.status == 200) {
						result = obj.responseText;

					}
				}
				// param:Type:'get/post';URL;sync(是否异步传输)
				obj.open('GET', urlStr, false);
				// obj.setRequestHeader('Content-Type',
				// 'application/x-www-form-urlencoded');
				obj.send(null);

				return result;
			}
			var sm = new Ext.grid.CheckboxSelectionModel();
			var rn = new Ext.grid.RowNumberer()
			var proj = {
				header : "pro",
				// renderTo:"hello",
				dataIndex : "pro",
				editor : {
					xtype : 'textfield',
					allowBlank : false
				}
			};
			// 返回model和store的fields字段值
			function getFields() {
				var col = "[rn,sm,";
				var stor_str = "[";
				var result = "";
				var json = Ext.util.JSON
						.decode(ajaxSyncCall("creatcol.json?id="
								+ Math.random()));
				// 遍历外层对象
				for (data in json) {
					// 第二层循环,遍历数组
					for (dt1 in json[data]) {
						// 第三层循环，遍历hash数据
						for (dt2 in json[data][dt1]) {
							// 将header和dataIndex的值一hash格式传到数组中，在声明columnModel时用
							// var temp = {};
							col += "{header:" + "'" + dt2 + "',";
							col += "dataIndex:" + "'" + dt2 + "',";
							// col += sm+"'"+dt2+"',";
							// col +=new Ext.grid.RowNumberer()+"'"+dt2+"',";
							col += "editor:{ xtype:'textfield',allowBlank: false}},";
							stor_str += "{name:" + "'" + dt2 + "'},";
						}
						col = col.substring(0, col.lastIndexOf(","));
						stor_str = stor_str.substring(0, stor_str
										.lastIndexOf(","));
						col += "]";
						stor_str += "]";
						result = col + "$" + stor_str;
						return result;
					}

				}
			}

			var result = getFields();
			alert(result);
			var index = result.indexOf("$");
			var columnModel = result.substring(0, index);
			var storeStr = result.substring(index + 1);
			// var sm = new Ext.grid.CheckboxSelectionModel();
			var cm = new Ext.grid.ColumnModel(
					// new Ext.grid.RowNumberer(),
					// sm,
					eval(columnModel)

			);
			cm.defaultSortable = true;
			// ArrayReader
			var store = new Ext.data.Store({
						root : 'coders',
						// remoteSort: true,
						proxy : new Ext.data.HttpProxy({
									url : 'creatcol.json?id=' + Math.random(),
									method : 'GET'
								}),
						reader : new Ext.data.JsonReader({
									idProperty : 'threadid',
									root : 'coders'
								}, eval(storeStr))
					});
			store.load({
						params : {
							start : 0,
							limit : 5
						}
					});

			var tbar = [new Ext.Toolbar.TextItem('Tools bar:'), {
						xtype : "tbfill"
					}, {
						pressed : true,
						text : "Refresh",
						handler : function() {
							Ext.Msg.alert("Refresh", "Refresh!")
						}
					}, {
						xtype : "tbfill"
					}, {
						pressed : true,
						text : "Selection"
					}, {
						xtype : "tbseparator"
					}, {

						pressed : true,
						text : "Add",
						handler : function() {
							Ext.Msg.alert("Add", "add infro!")
						}
					}, {
						xtype : "tbseparator"
					}, {
						pressed : true,
						text : "save",
						handler : function() {
							Ext.Msg.alert("Save", "save infro successed!!!")
						}
					}, {
						xtype : "tbseparator"
					}];
			var grid = new Ext.grid.EditorGridPanel({
				draggable : true,
				store : store,
				cm : cm,
				el : grid,
				renderTo : 'w',
				width : 630,
				height : 320,
				loadMask : true,
				// tbar:tbar,
				tools : [{
							id : "save"
						}, {
							id : "help",
							handler : function() {
								Ext.Msg.alert("Help", "Need help!")
							}
						}, {
							id : "close",
							handler : function() {
								grid.hide();
							}
						}],
				bbar : [new Ext.PagingToolbar({
							store : store,
							pageSize : 5,
							displayInfo : true,
							displayMsg : "Displaying {0} - {1} of {2}",
							emptyMsg : "No data to display",
							items : ['-', {
										pressed : true,
										enableToggle : true,
										text : 'show preview',
										cls : 'x-btn-text-icon details',
										toggleHandler : function(btn, pressed) {
											var view = grid.getView();
											view.showPreview = pressed;
											view.refresh();
										}
									}]
						})],
				collapsible : true,
				multiSelect : true,
				title : 'Edit Plants',
				frame : true,
				clicksToEdit : 2,
				trackMouseOver : true,
				// viewConfig: {
				stripeRows : true,
				enableTextSelection : true
					// }

				});
			grid.render();

		});