Ext.define('mvc.tools.SearchField', {
    extend: 'Ext.form.field.Trigger',
    alias: 'widget.irilleSearchfield',
    trigger1Cls: Ext.baseCSSPrefix + 'form-clear-trigger',
    trigger2Cls: Ext.baseCSSPrefix + 'form-search-trigger',
	diySql : [],
	first:true,
    hasSearch : false,
    paramName : 'query',
    flds:'pkey',

    initComponent: function() {
        var me = this;

        me.callParent(arguments);
        me.on('specialkey', function(f, e){
            if (e.getKey() == e.ENTER) {
                me.onTrigger2Click();
            }
        });
        if(typeof(me.store.isStore) == 'undefined')
        {
            me.store = Ext.data.StoreManager.get(me.store);
        }
        me.store.remoteFilter = true;
        if (!me.store.proxy.hasOwnProperty('filterParam')) {
            me.store.proxy.filterParam = me.paramName;
        }
    },

    afterRender: function(){
        this.callParent();
        this.triggerCell.item(0).setDisplayed(false);
    },

    onTrigger1Click : function(){
        var me = this;

        if (me.hasSearch) {
            me.setValue('');
            var array = [];
            array.push({
            	id:'flds',
            	property: "param",
            	value: '1=1'
            })
            decoded = me.store.decodeFilters(array),
			me.store.filters = me.diySql;
			
			me.store.filters.replace(decoded[0]);
       		filters = me.store.filters.items;

       		me.store.clearFilter(true);
	        me.store.filter(filters);
            me.hasSearch = false;
            me.triggerCell.item(0).setDisplayed(false);
            me.updateLayout();
        }
    },

    onTrigger2Click : function(){
        var me = this,
            value = me.getValue();
        if(me.first) {
       		Ext.apply(me.diySql,me.store.filters);
        	me.first = false;
        }
        if (value.length > 0) {
        	me.store.filter([{
        		id:'flds',
                property: 'flds',
                value: this.flds
            },{
            	id:'param',
                property: 'param',
                value: value
            }]);
            me.hasSearch = true;
            me.triggerCell.item(0).setDisplayed(true);
            me.updateLayout();
        }
    }
});