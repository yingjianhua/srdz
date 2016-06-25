Ext.define('mvc.view.wa.WaActVoteEntry.TriggerImgCell', {
			extend : 'Ext.form.field.Trigger',
			alias : 'widget.beantriggercell',

			editable : false,
			trigger1Cls: Ext.baseCSSPrefix + 'form-clear-trigger',
		    trigger2Cls: Ext.baseCSSPrefix + 'form-search-trigger',
			
			beanName : null,
			grid : null,

			onTrigger1Click : function(){
				this.beanParams = null;
		        this.setValue();
		    },
			
			onTrigger2Click : function() {
				var selection = this.grid.getSelectionModel().getSelection()[0];
				var win = Ext.create("mvc.view.wa.WaActVoteEntry.WinImg");
				win.on('trigger', this.onTrigger, this);
				win.show();
				win.setActiveRecord(selection);
			},

			//回调 -- Cell上的一离开，编辑控件已经没了，直接操作表格
			onTrigger : function(data, params) {
				this.setValue(data);
				var record = this.grid.getView().getSelectionModel().getSelection()[0];
				record.set(this.beanName,this.getValue());
			},

		});