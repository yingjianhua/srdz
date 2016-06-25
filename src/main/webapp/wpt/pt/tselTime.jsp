<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="time_sel_container" style="display:none;z-index:3;">	
	
	<div class="tsel_handle">
		<div class="tsel_btn tsel_hover" val="">
			<dl>
				<dt>今天</dt>
				<dd>12-31</dd>
			</dl>	
		</div>
		<div class="tsel_btn" val="">
			<dl>
				<dt>明天</dt>
				<dd>01-01</dd>
			</dl>	
		</div>
		<div class="tsel_btn" val="">
			<dl>
				<dt>后天</dt>
				<dd>01-02</dd>
			</dl>	
		</div>
		<div class="tsel_btn tsel_btn_more" val="">
			<span class="tsel_btn2">更多</span>
		</div>
	</div>
	<div class="clear"></div>
	
	<div class="tsel_slide tsel_time tsel_show">
		<input type="hidden" class="time_val" value="" /><!--选中的时间会JS写入到这里来-->
		
		<div class="tsel_item">
			<h3>早午餐</h3>	
			<a href="javascript:;" val="10:00">10:00</a>
			<a href="javascript:;" val="10:30">10:30</a>
			<a href="javascript:;" val="11:00">11:00</a>
			<a href="javascript:;" val="11:30">11:30</a>
			<a href="javascript:;" val="12:00">12:00</a>
			<a href="javascript:;" val="12:30">12:30</a>
			<a href="javascript:;" val="13:00">13:00</a>
			<a href="javascript:;" val="13:30">13:30</a>
			<div class="clear"></div>			
		</div>
		<div class="tsel_item">
			<h3>下午茶</h3>	
			<a href="javascript:;" val="14:00">14:00</a>
			<a href="javascript:;" val="14:30">14:30</a>
			<a href="javascript:;" val="15:00">15:00</a>
			<a href="javascript:;" val="15:30">15:30</a>
			<a href="javascript:;" val="16:00">16:00</a>
			<a href="javascript:;" val="16:30">16:30</a>			
			<div class="clear"></div>			
		</div>
		<div class="tsel_item">
			<h3>晚餐</h3>	
			<a href="javascript:;" val="17:00">17:00</a>
			<a href="javascript:;" val="17:30">17:30</a>
			<a href="javascript:;" val="18:00">18:00</a>
			<a href="javascript:;" val="18:30">18:30</a>
			<a href="javascript:;" val="19:00">19:00</a>
			<a href="javascript:;" val="19:30">19:30</a>		
			<a href="javascript:;" val="20:00">20:00</a>	
			<a href="javascript:;" val="20:30">20:30</a>		
			<div class="clear"></div>			
		</div>
			
	</div>
	<div class="tsel_slide tsel_calendar" year="" month="" selectDate="" nowDate="2015-12-31"><!--这里的nowDate放入今天的时间，比它时的日期不被选中-->
		<input type="hidden" class="date_val" value="2015-12-31" />	
	</div>
	
	<a href="javascript:;" class="cm_btn1 tsel_next">确定</a>
	<div class="tsel_zw"></div>
	
</div>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/base.js"></script>
<script>
	
</script>
<script type="text/javascript">
$(function() {
	var today = new Date();
	var tomorrow = new Date(today.getTime()+oneDayMilliseconds);
	var after_tomorrow = new Date(tomorrow.getTime()+oneDayMilliseconds);
	var tsel_handle = "<div class=\"tsel_btn tsel_hover\" val=\""+formatDate2(today)+"\">" +
			"<dl>" +
				"<dt>今天</dt>" +
				"<dd>"+formatDate1(today)+"</dd>" +
			"</dl>" +
		"</div>" +
		"<div class=\"tsel_btn\" val=\""+formatDate2(tomorrow)+"\">" +
			"<dl>" +
				"<dt>明天</dt>" +
				"<dd>"+formatDate1(tomorrow)+"</dd>" +
			"</dl>" +
		"</div>" +
		"<div class=\"tsel_btn\" val=\""+formatDate2(after_tomorrow)+"\">" +
			"<dl>" +
				"<dt>后天</dt>" +
				"<dd>"+formatDate1(after_tomorrow)+"</dd>" +
			"</dl>" +
		"</div>" +
		"<div class=\"tsel_btn tsel_btn_more\">" +
			"<span class=\"tsel_btn2\">更多</span>"
		"</div>";
		$(".tsel_handle").html(tsel_handle);
		//日期选择器选中
		$(".tsel_btn").each(function(num) {
			$(this).click(function() {
				$(this).addClass("tsel_hover").siblings().removeClass("tsel_hover");
				if($(this).hasClass("tsel_btn_more")) {
					//日期选择器选中（更多）
					$(".tsel_time").toggleClass("tsel_show");
					if($(".tsel_calendar").hasClass("tsel_show")) {
						//重新生成日历
						$(".tsel_calendar").removeClass("tsel_show");
					} else {
						$(".tsel_calendar").addClass("tsel_show");
					}
				} else {
					$(".tsel_time").addClass("tsel_show");
					$(".tsel_calendar").removeClass("tsel_show");
				}
			})
		})
		createCalendar($(".tsel_calendar"), today.getFullYear(), today.getMonth());
})
	//点击确定按钮，将所选的日期和时间设置到宴会时间表单中
	$(".tsel_next").click(function() {
		var d = $(".tsel_btn.tsel_hover").attr("val");
		var t = $(".tsel_item a.hover").attr("val");
		if(d && t) {
			$(".text.rt.date_val").val(d+" "+t);
			$(".text.rt.date_val").next().html(d+" "+t)
			$(".text.rt.date_val").next()[0].style.color = "#000000";
		}
		$(".time_sel_container").hide();
		$(".contact_way_container").show();
	})
		//写入时间到指定的input中
	
	$(".tsel_item a").on("click",function(){
		var t = $(this).attr("val");
		var d = $(".tsel_btn.tsel_hover").attr("val");
		d = parseFloat(d.split("/")[1] + "." + d.split("/")[2]);
		d2 = new Date().getMonth() + 1 + "." + new Date().getDate();
		t = parseFloat(t.split(":")[0] + "." + t.split(":")[1]);
		var now = parseFloat(new Date().getHours() + "." + new Date().getMinutes());
		if(t < now && d <= d2){
			console.log(1)
			tipbox("时间不能早于当前时间")
		}else{
		var _par = $(this).parents(".tsel_slide");
		$("a",_par).removeClass("hover");
		$(this).addClass("hover");
		$(".time_val",_par).val($(this).attr("val"));	
		}
	});
</script>
<script>	
	var oneDayMilliseconds = 24*60*60*1000;
	var now = new Date();
	var today0 = new Date(now.getFullYear(), now.getMonth(), now.getDate(), 0, 0, 0, 0);
	//将日期对象转换成如下格式： 03-15
	function formatDate1(date) {
		var month = date.getMonth()+1;
		var day = date.getDate();
		return (month<10?("0"+month):month)+"-"+(day<10?("0"+day):day);
	}
	//将日期对象转换成如下格式： 2015/1/12
	function formatDate2(date) {
		var month = date.getMonth()+1;
		if(month<10){
			month = "0"+month;
		}
		return date.getFullYear()+"/"+month+"/"+date.getDate();
	}
	function CalendarSel(year, month, day) {
		$(".tscal_cell span").removeClass("hover");
		$(".tscal_cell span:[val='"+year+"/"+month+"/"+day+"']").addClass("hover");
	}
	function createCalendar(panel) {
		var tscal_week = createWeek();
		var tscal_line = createLine(now);
		var content = tscal_week + tscal_line;
		panel.html(content);
		//在日历中点选日期时触发
		$(".tscal_cell span").click(function() {
			if($(this).hasClass("grey"));
			else{
				$(".tscal_cell span").removeClass("hover");
				$(this).addClass("hover");
				var clickDate = new Date($(this).attr("val"));
				$(".tsel_btn2").html(formatDate1(clickDate));
				$(".tsel_btn.tsel_btn_more").attr("val", $(this).attr("val"));
				$(".tsel_time").addClass("tsel_show");
				$(".tsel_calendar").removeClass("tsel_show");
			}
		})
	}
	function createLine(today) {
		var startDay = new Date(today.getTime() - today.getDay()*oneDayMilliseconds);
		var endDay = new Date(today.getFullYear()+1, today.getMonth()+1, -1);
		var date = startDay;
		var tscal_line = "";
		var i = 1;
		while(date.getTime() <= endDay.getTime()) {
			if(date.getDay() == 0) {//假如这天是周日
				if(date.getDate() == 1) {//假如这天是周日切是一号
					tscal_line = tscal_line + "<div class=\"tscal_month\" style=\"padding-top:45px;margin-left:"+date.getDay()*80+"px\">"+(date.getMonth()+1)+"月</div>";
				}
				tscal_line = tscal_line + "<div class=\"tscal_line\">";
			} else {
				if(date.getDate() == 1) {//假如这天是一号
					tscal_line = tscal_line + "</div>";
					tscal_line = tscal_line + "<div class=\"tscal_month\" style=\"padding-top:45px;margin-left:"+date.getDay()*80+"px\">"+(date.getMonth()+1)+"月</div>";
					tscal_line = tscal_line + "<div class=\"tscal_line\">";
					for(var i=date.getDay();i>0;i--) {
						tscal_line = tscal_line;
					}
				}
			}
			if(date.getTime() < today0){
				tscal_line = tscal_line + "<span class=\"tscal_cell\"><span class=\"grey\" val=\""+formatDate2(date)+"\">"+date.getDate()+"</span></span>";
			}
			else
				tscal_line = tscal_line + "<span class=\"tscal_cell\"><span val=\""+formatDate2(date)+"\">"+date.getDate()+"</span></span>";
			
			if(date.getDay() == 6) {
				tscal_line = tscal_line + "</div>";
			}
			date = new Date(date.getTime()+oneDayMilliseconds);
		}
		if(date.getDay() != 6) {
			tscal_line = tscal_line + "</div>";
		}
		return tscal_line;
	}
	//新的月份
	function createMonth(currMonth) {
		return "<div class=\"tscal_month\" style=\"padding-top:45px;margin-left:"+currMonth.getDay()*82+"px\">"+(currMonth.getMonth()+1)+"月</div>";
	}
	//上个月的最后一周
	function createPre(currMonth) {
		var tscal_pre = "<div class=\"tscal_pre\">";
		for(var i=currMonth.getDay();i>0;i--) {
			var date = new Date(currMonth.getTime()-oneDayMilliseconds*i);
			tscal_pre = tscal_pre + "<span val=\""+formatDate2(date)+"\">"+date.getDate()+"</span>";
		}
		tscal_pre = tscal_pre + "</div>";
		return tscal_pre;
	}
	function createWeek() {
		var tscal_week = "<div class=\"tscal_week\">"+
		"<span>日</span>"+	
		"<span>一</span>"+
		"<span>二</span>"+
		"<span>三</span>"+
		"<span>四</span>"+
		"<span>五</span>"+
		"<span>六</span>"+
		"</div>";
		return tscal_week;
	}
</script>
