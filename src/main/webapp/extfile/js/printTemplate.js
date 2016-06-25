function printTable() {
	var LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM')); 
		var tbl = "";
		for(i=0; i<50; i++) {
			tbl += "  <tr>" +
				"    <td>&nbsp;TB_AMJ006</td>" +
				"    <td>名称" + i + "</td>" +
				"    <td>浅灰色</td>" +
				"    <td>185/10</td>" +
				"    <td>" + i + "</td>" +
				"    <td>248.00</td>" +
				"    <td>248.00</td>" +
				"  </tr>";
		}
		
		var div1 = "<div id=\"div1\">" +
				"	<div style=\"LINE-HEIGHT: 30px\" class=size16 align=center><STRONG><font color=\"#0000FF\">测试标题</font></STRONG></DIV>        " +
				"		<table border=0 cellSpacing=0 cellPadding=0 width=\"100%\">" +
				"  			<TBODY>" +
				"			  <tr>" +
				"			    <td width=\"43%\"><font color=\"#0000FF\">所在店铺：<span id=rpt_Pro_Order_List_ctl00_lbl_eShop_Name>方瑞科技</span></font></td>" +
				"			    <td width=\"33%\"><font color=\"#0000FF\">发货单号：<span >2011050810372</SPAN></font></td>" +
				"			    <td><font color=\"#0000FF\">快递单号：</font></td></tr>" +
				"			  <tr>" +
				"			    <td><font color=\"#0000FF\">收 件 人：<span >叶翔</SPAN></font></td> " +
				"			    <td><font color=\"#0000FF\">网店单号：<span>74235823905643</SPAN></font><font color=\"#0000FF\"></font></td>" +
				"			    <td><font color=\"#0000FF\">发货日期：2011-5-10</font></td></tr>" +
				"			  <tr>" +
				"			    <td><font color=\"#0000FF\">电话号码：<span>13935429860　</SPAN></font></td>" +
				"			    <td><font color=\"#0000FF\">收件人ID：<span>whx</SPAN></font></td>" +
				"			    <td><font color=\"#0000FF\">&nbsp;</font></td></tr></TBODY></TABLE>" +
				"	</div>";
		var div2 = "<div id=\"div2\">" +
				"<table border=1 cellSpacing=0 cellPadding=1 width=\"100%\" style=\"border-collapse:collapse\" bordercolor=\"#333333\">" +
				"<thead>" +
				"  <tr>" +
				"    <td width=\"10%\">" +
				"      <div align=center><b>表格页眉</b></DIV></td>" +
				"    <td width=\"25%\">" +
				"      <div align=center><b>品名</b></DIV></td>" +
				"    <td width=\"10%\">" +
				"      <div align=center><b>颜色</b></DIV></td>" +
				"    <td width=\"10%\">" +
				"      <div align=center><b>规格</b></DIV></td>" +
				"    <td width=\"10%\">" +
				"      <div align=center><b>数量</b></DIV></td>" +
				"    <td width=\"15%\">" +
				"      <div align=center><b>单价</b></DIV></td>" +
				"    <td width=\"20%\">" +
				"      <div align=center><b>金额</b></DIV></td></tr>" +
				"</thead>" +
				"  <tbody>" + 
				tbl +
				"</tbody>" +
				"  <tfoot>" +
				"  <tr>" +
				"    <td><b>表格页脚</b></td>" +
				"    <td><b>本页动态合计</b></td>" +
				"    <td><b>&nbsp;</b></td>" +
				"	<td tdata=\"pageNO\" format=\"#\" align=\"left\">" +
				"  	<p align=\"center\"><b>第<font color=\"#0000FF\">#</font>页</b></p>" +
				"	</td>" +
				"	<td tdata=\"pageCount\" format=\"#\" align=\"left\">" +
				"  	<p align=\"center\"><b>总<font color=\"#0000FF\">#</font>页</b></td>    " +
				"	<td width=\"14%\" align=\"right\">　</td>" +
				"	<td width=\"19%\" tdata=\"subSum\" format=\"#,##0.00\" align=\"right\"><font color=\"#0000FF\">###元</font></td>    " +
				" </tr>" +
				"  </tfoot>" +
				"</table>" +
				"</div>";
		var div3 = "<div id=\"div3\">" +
				"  <div style=\"LINE-HEIGHT: 30px\" align=center><font color=\"#0000FF\">感谢您对我们方瑞科技的支持，(发货单的表格外“页脚”，紧跟表格)</font></DIV>" +
				"</div>";
		LODOP.PRINT_INIT("打印控件功能演示_Lodop功能_分页打印综合表格");
		var strStyle="<style> table,td,th {border-width: 1px;border-style: solid;border-collapse: collapse}</style>"
		LODOP.ADD_PRINT_TABLE(128,"5%","90%",314,strStyle+div2);
		LODOP.SET_PRINT_STYLEA(0,"Vorient",3);		
		LODOP.ADD_PRINT_HTM(20,"5%","90%",109,div1);
		LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
		LODOP.SET_PRINT_STYLEA(0,"LinkedItem",1);	
    	LODOP.ADD_PRINT_HTM(444,"5%","90%",54,div3);
		LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
		LODOP.SET_PRINT_STYLEA(0,"LinkedItem",1);	
		LODOP.ADD_PRINT_TEXT(3,653,135,20,"总页号：第#页/共&页");
		LODOP.SET_PRINT_STYLEA(0,"ItemType",2);
		LODOP.SET_PRINT_STYLEA(0,"Horient",1);	
		LODOP.ADD_PRINT_TEXT(3,34,196,20,"总页眉：《翻页表格的演示》");
		LODOP.SET_PRINT_STYLEA(0,"ItemType",1);		
		LODOP.PREVIEW();
//		LODOP.PRINT_DESIGN();
}