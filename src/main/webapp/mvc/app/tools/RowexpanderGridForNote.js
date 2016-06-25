Ext.define('mvc.tools.RowexpanderGridForNote', {
	extend : 'Ext.grid.Panel',

	xtype : 'row-expander-grid',
	store : 'Companies',
	loadMask : true,
	selModel : Ext.create('Ext.selection.CheckboxModel', {}),
	oneTdCount: 3,
	viewConfig : {
		enableTextSelection : true,
		listeners : {
			expandbody : function(rowNode, record, expandRow) {
				var el = Ext.get(expandRow);
    			var customDiv = el.down('div[class=custom_rowexpandergrid_class]');
    			if(customDiv.first()){
    				return;
    			}
    			var grid = this.up('grid');
    			var columns = record.get('extContent').split(',');
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
    				if(columns[i]){
    					var col = columns[i].split(':');
						var text = col[0];
						var value = col[1];
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
    			html += '</table>';
    			customDiv.setHTML(html);
    			
    			if(grid.selModel.checkerOnCls){
    				var baseCSSPrefix = Ext.baseCSSPrefix;
    				el.insertHtml('afterBegin','<td class="' + baseCSSPrefix + 'grid-cell-special"></td>')
				}
			}
		}
	},
	plugins : [{
		ptype : 'rowexpander',
		rowBodyTpl : '<div class="custom_rowexpandergrid_class" style="padding: 5px 0 5px 0;"></div>'
	}]
});