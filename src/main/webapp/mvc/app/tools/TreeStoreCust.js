Ext.define('mvc.tools.TreeStoreCust', {
	extend : 'Ext.data.TreeStore',
	pageSize : 20,
	autoLoad : false,
	currentPage:1,
	defaultRootId:'',
	listeners :{
//		beforeexpand : function(node,eOpts){
//			var pkey = node.get('bean.pkey');
//			//var store = this.getKeyTreeView().getStore();
//			this.getProxy().url = url;
//			this.getProxy().extraParams['filter']=Ext.encode([{"property":"locPkey","value":pkey}])
//		},
//		beforeload:function(store,oper){
//			this.getProxy().url = store.url;
//			//当filter为空时不请求
//			if(!oper.params.filter && !this.getProxy().extraParams['filter']){
//				return false;
//			}
//		}
	},
	proxy : {
		type : 'ajax',
		reader : {
			type : 'json',
			root : 'items',
			totalProperty : 'total'
		}
	}
//	nextPage: function(options) {
//        this.loadPage(this.currentPage + 1, options);
//    },
//    previousPage: function(options) {
//        this.loadPage(this.currentPage - 1, options);
//    },
//	getTotalCount: function() {
//        return this.totalCount || 0;
//    },
//	loadPage: function(page, options) {
//        var me = this;
//
//        me.currentPage = page;
//
//        // Copy options into a new object so as not to mutate passed in objects
//        options = Ext.apply({
//            page: page,
//            start: (page - 1) * me.pageSize,
//            limit: me.pageSize,
//            addRecords: !me.clearOnPageLoad
//        }, options);
//
//        if (me.buffered) {
//            options.limit = me.viewSize || me.defaultViewSize;
//            return me.loadToPrefetch(options);
//        }
//        me.read(options);
//    },
//    load: function(options) {
//        options = options || {};
//        options.params = options.params || {};
//
//        var me = this,
//            node = options.node || me.tree.getRootNode();
//
//        // If there is not a node it means the user hasnt defined a rootnode yet. In this case lets just
//        // create one for them.
//        if (!node) {
//            node = me.setRootNode({
//                expanded: true
//            }, true);
//        }
//        
//        // Assign the ID of the Operation so that a ServerProxy can set its idParam parameter,
//        // or a REST proxy can create the correct URL
//        options.id = node.getId();
//
//        if (me.clearOnLoad) {
//            if(me.clearRemovedOnLoad) {
//                // clear from the removed array any nodes that were descendants of the node being reloaded so that they do not get saved on next sync.
//                me.clearRemoved(node);
//            }
//            // temporarily remove the onNodeRemove event listener so that when removeAll is called, the removed nodes do not get added to the removed array
//            me.tree.un('remove', me.onNodeRemove, me);
//            // remove all the nodes
//            node.removeAll(false);
//            // reattach the onNodeRemove listener
//            me.tree.on('remove', me.onNodeRemove, me);
//        }
//
//        Ext.applyIf(options, {
//            node: node
//        });
//
//        me.callParent([options]);
//        
//        if (me.loading && node) {
//            node.set('loading', true);
//        }
//        
//        return me;
//    },
//    onProxyLoad: function(operation) {
//        var me = this,
//            successful = operation.wasSuccessful(),
//            records = operation.getRecords(),
//            node = operation.node;
//            resultSet = operation.getResultSet();
//
//        if (resultSet) {
//            me.totalCount = resultSet.total;
//        }
//
//        me.loading = false;
//        node.set('loading', false);
//        if (successful) {
//            if (!me.clearOnLoad) {
//                records = me.cleanRecords(node, records);
//            }
//            records = me.fillNode(node, records);
//        }
//        
//        
//        me.fireEvent('read', me, operation.node, records, successful);
//        me.fireEvent('load', me, operation.node, records, successful);
//        Ext.callback(operation.callback, operation.scope || me, [records, operation, successful]);
//    }
})