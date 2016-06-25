Ext.Loader.setConfig({// 启用loader，因为html只加载app.js
	enabled : true
});
Ext.Loader.setPath('Ext.ux', '../extfile/extjs4/examples/ux');
var required = '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>';
var app_icon_upd = '../extfile/images/mvc/upd.gif';
var app_icon_del = '../extfile/images/mvc/del.gif';
var app_icon_set = '../extfile/images/icons/set-ico.gif';
var app_icon_approve = '../extfile/images/mvc/approve.gif';
var app_icon_unapprove = '../extfile/images/mvc/unapprove.gif';
var msg_title = '系统提示';
var msg_text = '信息成功保存!';
var msg_del = '信息删除成功!';
var msg_submit = '信息提交成功!';
var msg_ajax = '请求失败!';
var msg_confirm_title = '信息确认';
var msg_confirm_msg = '您确认要删除所选的记录吗?';
var wait_title = '数据传送';
var wait_msg = '数据传送中,请稍候...';
var form_empty_text = '请选择';
var bean_split = '##';
var bean_submit = '数据提交成功';
var line_pr = 'listLine';
var ENABLED_TRUE = 1;
var ENABLED_FALSE = 0;
var base_path = getBasePath(); //项目根路径
var lg_user_pkey,lg_user_name;
var lg_dept_pkey,lg_dept_name;
var lg_cell_pkey,lg_cell_name;
var lg_org_pkey,lg_org_name;
var base_path = getBasePath(); // 项目根路径
var sys_menus,sys_menuOBJ; //系统安装的模块信息
//单据状态
var BEAN_VERSION = 'bean.rowVersion';
var BEAN_STATUS = 'bean.status';
var STATUS_INIT = 11,STATUS_VERIFIED=58,STATUS_APPROVE=68,STATUS_INOUT=81,STATUS_TALLY=83,STATUS_CLOSE=98,STATUS_BAD=99;
var CONT_INIT = 1, CONT_APPROVE=2;
var STATUS_CHECKED = 68, STATUS_PUBLISHED = 68;
var STATUS_DONE = 98;


function getBasePath() {
	var pathName = window.document.location.pathname;
	var projectName = pathName
			.substring(0, pathName.substr(1).indexOf('/') + 1);
	if (projectName)
		if (projectName == '/mvc' || projectName == '/cli')
			return '';
	return projectName;
}

// 判断登录是否
Ext.Ajax.on('requestcomplete', checkUserSessionStatus, this);
function checkUserSessionStatus(conn, response, options) {
	if (typeof(response.status) == "undefined") {
		return; // TODO form-file-submit文件上传时，此回调的response没有header??
	}
	var sessionStatus = response.getResponseHeader("sessionstatus");
	if (typeof(sessionStatus) != "undefined") {
		Ext.Msg.alert('提示', '会话超时，请重新登录!', function(btn, text) {
			if (btn == 'ok') {
				var redirect = 'login.jsp';
				window.location = redirect;
			}
		});
	}
};

Ext.applyIf(Ext.form.VTypes, {
       // 固定电话及小灵通
		"tel" : function(_v) {
		   return /(^\d{3}\-\d{7,8}$)|(^\d{4}\-\d{7,8}$)|(^\d{3}\d{7,8}$)|(^\d{4}\d{7,8}$)|(^\d{7,8}$)/
		     .test(_v);
		},
		"telText" : "该输入项目必须是电话号码格式，例如：</br>0513-89500414,051389500414,89500414",
		"telMask" : /[0-9\-]/i,
		// 手机
		"mob" : function(_v) {
		   return /^1\d{10}$/.test(_v);
		},
		"mobText" : "该输入项目必须是手机号码格式，例如：</br>13912345678",
		"mobMask" : /[0-9]/i,
		// 营业执照
		"busCode" : function(_v) {
		   return /^\d{15}$/.test(_v);
		},
		"busCodeText" : "该输入项目必须是营业执照格式，例如：</br>330302000012345",
		"busCodeMask" : /[0-9]/i,
		// 身份证
		"sfzCode" : function(_v) {
		   return /(^\d{15}$)|(^\d{17}(\d|\x|\X)$)/
		     .test(_v);
		},
		"sfzCodeText" : "该输入项目必须是身份证格式，例如：</br>330302020202020,33030219020202020X",
		"sfzCodeMask" : /[0-9\x\X]/i
      });

Ext.application({ 
			requires : ['mvc.tools.BeanTrigger','mvc.tools.BeanTriggerList', 'mvc.tools.BeanTriggerCell',
					'mvc.tools.BeanTriggerMulti', 'mvc.tools.FieldSetCheck',
					'mvc.tools.ComboxCust', 'mvc.tools.ComboxCustCell',
					'mvc.tools.ComboxTrigger', 'mvc.tools.NumberPct','mvc.tools.MonthField',
					'mvc.tools.ComboxTriggerCell', 'mvc.tools.SearchField',
					'mvc.tools.ComboxAuto','mvc.tools.ComboxAutoCell',
					'mvc.tools.Combox','mvc.tools.DateFieldExp','mvc.tools.DatePickerExp',
					'mvc.tools.ImageField','mvc.tools.FileUrlField','mvc.view.portal.PilotageChart'],
			name : 'mvc',// 应用的名字
			appFolder : 'app',// 应用的目录
			autoCreateViewport : true,
			controllers : ['Main', 'Menu'] //, 'InitManager'   JS用户选项缓存暂时去除
		});