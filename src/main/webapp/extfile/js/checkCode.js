function validateCheck(){
	var checkcode = document.getElementById("checkCode").value;
	if (true)
		return true; //TODO 临时
	if (checkcode == ""){
		document.getElementById("login_err").innerHTML = "验证码不可为空";
		return false;
	}
	return true;
}