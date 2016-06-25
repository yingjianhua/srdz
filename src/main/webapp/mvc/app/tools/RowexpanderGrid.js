Ext.define('mvc.tools.RowexpanderGrid', {
	extend : 'Ext.grid.Panel',
	requires:['Ext.ux.RowExpander'],
	alias : 'widget.rowexpandergrid',
	loadMask : true,
	selModel : Ext.create('Ext.selection.CheckboxModel', {}),
	plugins: [{
		pluginId : 'rowexpanderplugin',
        ptype: 'rowexpander',
        rowBodyTpl : '<div class="custom_rowexpandergrid_class" style="padding: 5px 0 5px 0;"></div>',
        getRowBodyFeatureData: function(record, idx, rowValues) {
    	    var me = this
	        me.self.prototype.setupRowData.apply(me, arguments);
	        rowValues.rowBody = me.recordsExpanded[record.internalId]?me.grid.ongetRow(record,me.grid):me.getRowBodyContents(record);
	        rowValues.rowBodyCls = me.recordsExpanded[record.internalId] ? '' : me.rowBodyHiddenCls;
	    },
	    toggleRow: function(rowIdx, record) {
	        var me = this;
	            view = me.view,
	            element = view.getEl(); 
				rowNode = element.query('tr[data-recordid="'+record.internalId+'"]')[0];                                      
	            row = Ext.fly(rowNode, '_rowExpander'),
	            nextBd = row.down(me.rowBodyTrSelector, true),
	            isCollapsed = row.hasCls(me.rowCollapsedCls),
	            addOrRemoveCls = isCollapsed ? 'removeCls' : 'addCls',
	            ownerLock, rowHeight, fireView;
	        
	        Ext.suspendLayouts();
	        row[addOrRemoveCls](me.rowCollapsedCls);
	        Ext.fly(nextBd)[addOrRemoveCls](me.rowBodyHiddenCls);
	        me.recordsExpanded[record.internalId] = isCollapsed;
	        view.refreshSize();
	
	        
	        if (me.grid.ownerLockable) {
	            var ownerLock = me.grid.ownerLockable;
	            var fireView = ownerLock.getView();
	            view = ownerLock.lockedGrid.view;
	            var rowHeight = row.getHeight();
	            row = Ext.fly(view.getNode(rowIdx), '_rowExpander');
	            row.setHeight(rowHeight);
	            row[addOrRemoveCls](me.rowCollapsedCls);
	            view.refreshSize();
	        } else {
	            fireView = view;
	        }
	        fireView.fireEvent(isCollapsed ? 'expandbody' : 'collapsebody', row.dom, record, nextBd);
	        
	        Ext.resumeLayouts(true);
	    }
    }],
    oneTdCount: 5,
    viewConfig : {
    	enableTextSelection : true,
    	listeners : {
    		expandbody : function(rowNode, record, expandRow){
    			var el = Ext.get(expandRow);
    			var customDiv = el.down('div[class=custom_rowexpandergrid_class]');
    			if(customDiv.first()){
    				return;
    			}
    			var grid = this.up('grid');
    			var columns = grid.query('gridcolumn[expandCol]');
    			var count = columns.length;
    			var oneTdCount = grid.oneTdCount;
    			var allCount = Math.ceil(count / oneTdCount) * oneTdCount;
				var row = 0;
    			var html = '<table style="table-layout:fixed;width: 100%;border: 1px solid #c5c5c5;border-collapse: collapse;">';
    			for(var i = 0;i < allCount;i++){
    				if(i % oneTdCount == 0){
    					html += '<tr>';
    					row++;
    				}
					var background = 'background: #eee;';
					if(row % 2 == 0){
						background = 'background: #fff;';
					}
					html += '<td style="white-space:nowrap;vertical-align:middle;height:30px;padding-right:20px;' + background + '">';
    				var col = columns[i];
    				if(col){
						var text = col.text;
						var dataIndex = col.dataIndex;
						var value = record.get(dataIndex);
						var ac = "";
						if(col.md&&value) {
							ac="onclick='if(event.ctrlKey){mvc.Tools.onCreateTab(\""+col.md+"\",\""+col.mn+"\",\"pkey\",\""+value.split(bean_split)[0]+"\")}'";
							ac = ac+" onmouseover='lastChild.color=\"#00f\";lastChild.style=\"text-decoration:underline;cursor:pointer\"' onmouseout='lastChild.color=\"#000\";lastChild.style=\"\"'";
						}
						var renderer = col.renderer;
						if(renderer){
							value = renderer(value);
						}
						if (value == null){
							value = '';
						}
						value = String(value).replace(/<.+?>/g, '');
						//替换所有html代码
						html += ('<div '+ac+'style="overflow:hidden;text-overflow:ellipsis;" title="' + value + '"><b>' + text + '：</b><font> '+ value +'</font></div>');
    				}
					html += '</td>';
					if((i + 1) % oneTdCount == 0 ){
						html += '</tr>';
					}
    			}
    			html += '</table>';
    			customDiv.setHTML(html);
    			
    			if(grid.selModel.checkerOnCls){
    				var baseCSSPrefix = Ext.baseCSSPrefix;
    				el.insertHtml('afterBegin','<td class="' + baseCSSPrefix + 'grid-cell-special"></td>')
    			}
    			return html;
    		}
    	}
    },
    ongetRow : function(record,grid){
    	var me = this;
		var columns = me.query('gridcolumn[expandCol]');
		var count = columns.length;
		var oneTdCount = me.oneTdCount;
		var allCount = Math.ceil(count / oneTdCount) * oneTdCount;
		var row = 0;
		var html = '<div class="custom_rowexpandergrid_class" style="padding: 5px 0 5px 0;">' +
				'<table style="table-layout:fixed;width: 100%;border: 1px solid #c5c5c5;border-collapse: collapse;margin-left:24px">';
		if(grid.selModel.checkerOnCls){
			var html = '<div class="custom_rowexpandergrid_class" style="padding: 5px 0 5px 0;">' +
					'<table style="table-layout:fixed;width: 100%;border: 1px solid #c5c5c5;border-collapse: collapse;margin-left:48px">';
		}
		for(var i = 0;i < allCount;i++){
			if(i % oneTdCount == 0){
				html += '<tr>';
				row++;
			}
			var background = 'background: #eee;';
			if(row % 2 == 0){
				background = 'background: #fff;';
			}
			html += '<td style="white-space:nowrap;vertical-align:middle;height:30px;padding-right:20px;' + background + '">';
			var col = columns[i];
			if(col){
				var text = col.text;
				var dataIndex = col.dataIndex;
				var value = record.get(dataIndex);
				var renderer = col.renderer;
				if(renderer){
					value = renderer(value);
				}
				if (value == null){
					value = '';
				}
				value = String(value).replace(/<.+?>/g, '');
				//替换所有html代码
				html += ('<div style="overflow:hidden;text-overflow:ellipsis;" title="' + value + '"><b>' + text + '：</b>' + value + '</div>');
			}
			html += '</td>';
			if((i + 1) % oneTdCount == 0 ){
				html += '</tr>';
			}
		}
		html += '</table></div>';
		/*if(grid.selModel.checkerOnCls){
			var baseCSSPrefix = Ext.baseCSSPrefix;
			//el.insertHtml('afterBegin','<td class="' + baseCSSPrefix + 'grid-cell-special"></td>')
			html = '<td class="' + baseCSSPrefix + 'grid-cell-special"></td>'+html;
		}*/
		return html;
    }
});