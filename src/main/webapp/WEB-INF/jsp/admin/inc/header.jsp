<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="description" content="Herit 서비스 솔루션 관리자">
	<title>Herit 관리자</title>
	<link rel="stylesheet" href="/resources/css/jquery-ui-1.9.2.custom.min.css" />
	<link rel="stylesheet" href="/resources/css/yearpicker.css" />	
	<link rel="stylesheet" href="/resources/css/common.css" />
	<link rel="stylesheet" href="/resources/css/contents.css" />
	<script type="text/javascript" src="/resources/js/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="/resources/js/common.js"></script>	
	<script type="text/javascript" src="/resources/js/plugins.js"></script>
	
	<script type="text/javascript" src="/resources/js/menu.js"></script>
	
	<% /* Jstl taglib */ %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

	<% /* Spring taglib */ %>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
	
</head>
<body>
<div class="wrap">
	<!-- header -->
	<div class="header">
		<div class="innerWrap">
			<h1 class="logo"><a href=""><img src="/resources/images/common/logo.png" alt="HERIT"></a></h1>
			<div class="fr">
				<div class="infoLog">
					관리자
					<span class="btnWrap"><a href="" class="btnDefault"><img src="/resources/images/common/ico_log.gif" alt=""> 로그아웃</a></span>
				</div>
				
				<div id="topmenu-framebox" class="wrapGnb web-topmenu">
					<ul>
						<li id="topmenu1"><a href="">회원관리</a>
							<ul class="depth2">
								<li><a href="/admin/member/list">정회원</a></li>
								<li><a href="">휴면회원</a></li>
								<li><a href="">탈퇴회원</a></li>
							</ul>
						</li>
						<li id="topmenu2"><a href="">검진데이터</a>
							<ul class="depth2">
								<li><a href="">건강검진 요청</a></li>
								<li><a href="">건강검진 현황</a></li>
								<li><a href="">건강병원 등록</a></li>
							</ul>
						</li>
						<li id="topmenu3"><a href="">미션관리</a>
							<ul class="depth2">
								<li><a href="">미션 달성 현황</a></li>
								<li><a href="">걸음수 미션 현황</a></li>
								<li><a href="">건강검진 미션 현황</a></li>
								<li><a href="">공유 미션 현황</a></li>
							</ul>
						</li>
						<li id="topmenu4"><a href="">포인트관리</a>
							<ul class="depth2">
								<li><a href="">포인트 현황</a></li>
								<li><a href="">개인별 포인트 현황</a></li>
								<li><a href="">상품관리</a></li>
								<li><a href="">포인트 몰</a></li>
								<li><a href="">구매내역</a></li>
							</ul>
						</li>
						<li id="topmenu5"><a href="">결제관리</a>
							<ul class="depth2">
								<li><a href="">결제 내역</a></li>
								<li><a href="">요금제 정보</a></li>
							</ul>
						</li>
						<li id="topmenu6"><a href="">통계</a>
							<ul class="depth2">
								<li><a href="">통계</a></li>
							</ul>
						</li>
						<li id="topmenu7"><a href="">관리자관리</a>
							<ul class="depth2">
								<li><a href="/admin/authroity/list">관리자 관리</a></li>
								<li><a href="">권한 관리</a></li>
								<li><a href="">문의 내역</a></li>
							</ul>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<!-- //header -->