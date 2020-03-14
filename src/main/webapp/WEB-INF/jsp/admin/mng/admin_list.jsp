<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jsp/admin/inc/header.jsp"%>

	<!-- contaier -->
	<div class="innerWrap">
		<!-- SNB -->
		<div class="wrapSnb">
			<h2 class="tit">관리자 관리</h2>
			<ul class="snb">
				<li><a href="" class="active">관리자 관리</a></li>
				<li><a href="">권한 관리</a></li>
				<li><a href="">문의 내역</a></li>
			</ul>
		</div>
		<!-- //SNB -->
		<!-- contents -->
		<div class="contents">
			<div class="cntTitWrap">
				<h2 class="cntTitXL fl">관리자 관리</h2>
				<ol class="bcNav">
					<li><a href="" class="home">Home</a></li>
					<li><a href="">관리자 관리</a></li>
					<li><a href="">관리자 관리</a></li>
				</ol>
			</div>
			<div class="bxSrchForm tar">
				<select name="searchId">
					<option value="id">ID</option>
					<option value="name">성명</option>
					<option value="htel">연락처</option>
				</select>
				<input type="text" name="searchString" class="mlXs w30">
				<button type="button" id="btnSearch" class="btnPrimary sizeSm">검색</button>
			</div>
			<p class="txtBbs colorA mtSMd">총 5명</p>
			<table class="tblTypeA mtXs">
				<thead>
					<tr>
						<th>ID</th>
						<th>성명</th>
						<th>권한</th>
						<th>연락처</th>
						<th>핸드폰</th>
						<th>이메일</th>
						<th>최종 접속일</th>
						<th>상세정보</th>
					</tr>
				</thead>				
				<tbody>
				
					<tr>
						<td>system</td>
						<td>홍길동</td>
						<td>시스템 관리자</td>
						<td>02-1234-5678</td>
						<td>010-5678-1234</td>
						<td>neo123@nts.com</td>
						<td>2019.12.31 15:30</td>
						<td>
							<p class="btnWrap"><a href="" class="btnColorE">보기</a></p>
						</td>
					</tr>
					<tr>
						<td>gildong</td>
						<td>홍길동</td>
						<td>운영자</td>
						<td>02-1234-5678</td>
						<td>010-5678-1234</td>
						<td>gildong1234@nts.com</td>
						<td>2019.12.31 15:30</td>
						<td>
							<p class="btnWrap"><a href="" class="btnColorE">보기</a></p>
						</td>
					</tr>
				</tbody>
			</table>
			<!-- btn -->
			<div class="btnWrap mtMmd">
				<a href="" class="btnDefault">등록</a>
			</div>
		</div>
		<!-- //contents -->
	</div>
	<!-- //contaier -->
<script>
$(document).ready(function() {
	load_fnc('7', '0', '0'); //menu script
});
</script>	
<%@include file="/WEB-INF/jsp/admin/inc/footer.jsp"%>