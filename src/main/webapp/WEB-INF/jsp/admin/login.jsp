<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="description" content="Herit 서비스 솔루션 관리자">
	<title>Herit 관리자</title>
	<link rel="stylesheet" href="/resources/css/common.css" />
	<link rel="stylesheet" href="/resources/css/contents.css" />
	<script type="text/javascript" src="/resources/js/jquery-1.9.1.js"></script>
</head>
<body>
<div class="wrapLogin">
	<h1 class="logo"><img src="/resources/images/common/logo.png" alt="HERIT"></h1>
	<form name="frm" method="post">
	<div class="cnt">
		<p><input type="text" name="id" id="id" placeholder="ADMIN ID"></p>
		<p><input type="password" name="pass"  id="pass" placeholder="PASSWORD"></p>
		<p><button type="button" class="btn">로그인</button></p>
		<p class="txt">아이디,비밀번호 찾기는 관리자에게 문의 바랍니다. (02-xxx-xxxx)</p>
	</div>
	</form>
</div>
<script>
var message = "${returnCode}";
if(message == "9999"){
    alert("로그인에 실패하였습니다. \n 아이디 및 비밀번호를 확인해주세요");
}
$(document).ready(function(){
    $(".btn").click(function(){
    	fn_login();
    });
});

function fn_login()
{
	if ($("#id").val() == "")
	{
		alert("아이디를 입력하세요!!!");
		$("#id").focus();
		return;
	}
	if ($("#pass").val() == "")
	{
		alert("패스워드를 입력하세요!!!");
		$("#pass").focus();
		return;	
	}
	frm.action = "/admin/loginProcess";
	frm.submit();
}
</script>
</body>
</html>