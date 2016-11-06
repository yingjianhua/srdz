mainApp.controller("hostController",[
                                     "$scope",
                                     "$rootScope",
                                     "$location",
                                     "$window",
                                     "$cookies",
                                     "$routeParams",
                                     "utilService",
                                     "infoService",
                                     function(e,t,o,n,a,r,i,s){
                                    	 e.hostInfo=null,
                                    	 e.coordType=null,
                                    	 e.pos=null,
                                    	 e.mapUrl=null,
                                    	 e.skuUrl=null,
                                    	 t.inAPP?e.skuUrl="yhouse://":e.skuUrl="/",
                                    	 e.hostId=i.clearId(r.hostId);
                                    	 var l=o.search();
                                    	 void 0!=l.coordType&&void 0!=l.pos&&(e.coordType=l.coordType,e.pos=l.pos),
                                    	 s.host(e.hostId,e.coordType,e.pos)
	                                    	 .success(function(r,i,s){
	                                    		 if("0"===r.code&&r.data){
		                                    		 if(t.appGt("3.2")){
		                                    			 var l=JSON.parse(JSON.stringify(r.data));
				                                    	 delete l.highlight,
				                                    	 delete l.highs,
				                                    	 delete l.productLabels,
				                                    	 delete l.tags,
				                                    	 l.shareUrl+="undefined"!=typeof a.get("UI")?"&share_ui="+a.get("UI"):"",
				                                    	 n.location="yhouse://broker?scheme="+encodeURIComponent("host/"+e.hostId)+"&use=data&data="+encodeURIComponent(JSON.stringify(l))
			                                    	 }
		                                    		 e.hostInfo=r.data,
			                                    	 e.mapUrl="yhouse://map?coordType="+encodeURIComponent(e.hostInfo.coordType)+"&pos="+encodeURIComponent(e.hostInfo.hostPos)+"&title="+encodeURIComponent(e.hostInfo.hostName)+"&address="+encodeURIComponent(e.hostInfo.address);
			                                    	 try{
			                                    		 n.localStorage.setItem("hostInfo",JSON.stringify(r))
			                                    	 }catch(c){}
			                                    	 if(e.hostInfo.headPics&&e.hostInfo.headPics.length>0){
			                                    		 for(var d=[],p=0;p<e.hostInfo.headPics.length;p++)d[p]={imgSrc:e.hostInfo.headPics[p]};e.hostInfo.headPics=d
			                                    	 }
			                                    	 t.headTitle=e.hostInfo.title,
			                                    	 t.page.pageTitle=e.hostInfo.title,
			                                    	 t.inWX&&commonService.initWxConfig(o.absUrl(),r.data.title,"YHOUSE精选美食与玩乐，款待最重要的人。",o.absUrl(),r.data.sharePicUrl),
			                                    	 t.$emit("pageTitleChanged")
		                                    	 }else 
		                                    		 o.path("/404")
	                                    	})
	                                    	.error(function(){
	                                    		o.path("/404")
	                                    	}),
                                    	e.openFacility=function(a){
                                    		if("undefined"!=typeof _paq&&_paq.push(["trackEvent","Host","More "+a,e.hostId]),t.inAPP){
                                    			 var r=o.protocol()+"://"+o.host();
                                    			 "facility"===a?n.location="yhouse://sku-pop?title="+encodeURIComponent("餐厅设施")+"&icon="+encodeURIComponent("http://r.yhres.com/sku/facilities@3x.png")+"&url="+encodeURIComponent(r+"/sku-pop-app/host/facility/"+e.hostId):"highlight"===a&&(n.location="yhouse://sku-pop?title="+encodeURIComponent("推荐理由")+"&icon="+encodeURIComponent("http://r.yhres.com/sku/special@3x.png")+"&url="+encodeURIComponent(r+"/sku-pop-app/host/highlight/"+e.hostId))
                                    		}else 
                                    			o.path("/sku-pop/host/"+a+"/"+e.hostId)
                                    		},
                                    		e.openAddressTip=function(){
                                    			"undefined"!=typeof _paq&&_paq.push(["trackEvent","Host","Map",e.hostId]),
                                    			t.showDialog("当前版本过低，请更新至最新版后使用。")
                                			},
                                			e.publishArticle=function(){
                                				"undefined"!=typeof _paq&&_paq.push(["trackEvent","Host","Join Share Tag",e.hostId]),t.inAPP?n.location="yhouse://publish?tagKey="+encodeURIComponent(e.hostInfo.shareTagSimpleVO.title):n.location="/share-tag?id="+e.hostInfo.shareTagSimpleVO.id
                                			}
                                		}
                                     ])