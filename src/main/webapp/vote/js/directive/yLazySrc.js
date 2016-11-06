angular.module("mainApp")
.directive("yLazySrc",[
                       "$window",
                       "$document",
                       function(e,t){
                    	   var o=angular.element(e),
                    	   n=t[0],
                    	   a=!1,
                    	   r=function(){
                    		   var e=0,
                    		   t={};
                    		   return{
                    			   push:function(o){
                    				   t[e++]=o,
                    				   setTimeout(function(){
                    					   l(o)
                    				   })
                    			   },
                    			   del:function(e){
                    				   t[e]&&delete t[e]
                    			   },
                    			   size:function(){
                    				   return Object.keys(t).length
                    			   },
                    			   get:function(){
                    				   return t
                    			   }
                    		   }
                    	   }(),
                    	   i=function(){
                    		   var t,
                    		   o="number"==typeof e.pageYOffset?e.pageYOffset:(((t=n.documentElement)||(t=body.parentNode))&&"number"==typeof t.ScrollTop?t:body).ScrollTop;
                    		   return{offsetY:o}
                    	   },
                    	   s=function(t){
                    		   var o,
                    		   a=t[0].getBoundingClientRect(),
                    		   r=i(),
                    		   s=r.offsetY,
                    		   l=a.height,
                    		   c=a.top+s,
                    		   d=Math.max(n.documentElement.clientHeight,e.innerHeight||0);
                    		   return(s>=c&&c+l>=s||c>=s&&s+d>=c)&&(o=!0),o
                    	   },
                    	   l=function(e,t,n){
                    		   return t>=0&&n?s(n.elem)?n.load(t):!1:void(0===r.size()?(o.off("scroll resize touchmove",l),a=!1):angular.forEach(r.get(),
                    				   function(e,t){
		                    			   s(e.elem)&&e.load(t)
		                    			   }))
		                   },
		                   c=function(){
		                	   0==a&&(a=!0,o.on("scroll resize touchmove",l))
		                   };
		                   return{
		                	   restrict:"A",
		                	   scope:{},
		                	   link:function(e,t,o){
		                		   r.push({
		                			   elem:t,
		                			   load:function(n){
		                				   return t.css("background-image","none"),
		                				   t.on("load",function(){t.css("opacity",1)}),t.attr("src",o.yLazySrc),n>=0&&r.del(n),e.$destroy(),!0
		                			   }
		                		   }),
		                		   c()
		                	   }
		                   }
		               }
	])