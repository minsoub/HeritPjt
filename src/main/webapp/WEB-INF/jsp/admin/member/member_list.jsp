<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jsp/admin/inc/header.jsp"%>

	<!-- contaier -->
	<div class="innerWrap">
		<!-- SNB -->
		<div class="wrapSnb">
			<h2 class="tit">회원관리</h2>
			<ul class="snb">
				<li><a href="" class="active">정회원</a></li>
				<li><a href="">휴면회원</a></li>
				<li><a href="">탈퇴회원</a></li>
			</ul>
		</div>
		<!-- //SNB -->
		<!-- contents -->
		<div class="contents">
			<div class="cntTitWrap">
				<h2 class="cntTitXL fl">정회원</h2>
				<ol class="bcNav">
					<li><a href="" class="home">Home</a></li>
					<li><a href="">회원관리</a></li>
					<li><a href="">정회원</a></li>
				</ol>
			</div>
			<!-- 검색 영역 -->
			<div class="bxCnt mtMmd">
				<table class="tblTypeB">
					<colgroup>
						<col width="100px">
						<col>
						<col width="100px">
						<col>
					</colgroup>
					<tbody>
						<tr>
							<th>아이디</th>
							<td><input type="text" name=""></td>
							<th>연락처</th>
							<td><input type="text" name=""></td>
						</tr>
						<tr>
							<th>성명</th>
							<td><input type="text" name=""></td>
							<th>생년월일</th>
							<td><input type="text" name=""></td>
						</tr>
						<tr>
							<th>가입일</th>
							<td><input type="text" value="" class="datePic"> <span class="dash">~</span> <input type="text" value="" class="datePic"></td>
							<th>최종접속일</th>
							<td><input type="text" value="" class="datePic"> <span class="dash">~</span> <input type="text" value="" class="datePic"></td>
						</tr>				
					</tbody>
				</table>
				<div class="btnWrap mtSm tac">
					<a href="" class="btnPrimary">검색하기</a>
				</div>
			</div>
			<!-- //검색 영역 -->
			<div class="clearfix mtSm">
				<div class="txtBbs fl mtSMd">검색 <span class="colorA">0</span> 명 / 전체 <span class="colorA">1,284</span> 명</div>
				<div class="btnWrap fr"><a href="" class="btnDefault">회원등록</a></div>
			</div>
			<table class="tblTypeA mtSm">
				<thead>
					<tr>
						<th><input type="checkbox" name="" class="chckAll"></th>
						<th>ID</th>
						<th>연락처</th>
						<th>성명</th>
						<th>생년월일</th>
						<th>가입일</th>
						<th>최종 접속일</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><input type="checkbox" name=""></td>
						<td class="tal">aaabbb@herit.com</td>
						<td>010-1234-5678</td>
						<td>관리자</td>
						<td>1982.12.11</td>
						<td>2019.1.22 13:55</td>
						<td>2020.01.01 08:55</td>
					</tr>
					<tr>
						<td><input type="checkbox" name=""></td>
						<td class="tal">bbbccccdd@herit.com</td>
						<td>010-1234-5678</td>
						<td>관리자</td>
						<td>1982.12.11</td>
						<td>2019.1.22 13:55</td>
						<td>2020.01.01 08:55</td>
					</tr>
					<tr>
						<td><input type="checkbox" name=""></td>
						<td class="tal">aaabbb@herit.com</td>
						<td>010-1234-5678</td>
						<td>관리자</td>
						<td>1982.12.11</td>
						<td>2019.1.22 13:55</td>
						<td>2020.01.01 08:55</td>
					</tr>
					<tr>
						<td><input type="checkbox" name=""></td>
						<td class="tal">bbbccccdd@herit.com</td>
						<td>010-1234-5678</td>
						<td>관리자</td>
						<td>1982.12.11</td>
						<td>2019.1.22 13:55</td>
						<td>2020.01.01 08:55</td>
					</tr>
				</tbody>
			</table>
			<!-- paging -->
			<div class="wrapPaging">
				<div class="btnWrap fl">
					<a href="" class="btnDefault">회원등록</a>
				</div>
				<div class="paging">
					<a class="btnFirst" href="">첫 페이지</a>
					<a class="btnPrev" href="">이전 페이지</a>
					<a href="" class="on">1</a>
					<a href="">2</a>
					<a href="">3</a>
					<a href="">4</a>
					<a href="">5</a>
					<a href="">6</a>
					<a href="">7</a>
					<a href="">8</a>
					<a href="">9</a>
					<a href="">10</a>
					<a class="btnNext" href="">다음 페이지</a>
					<a class="btnLast" href="">마지막 페이지</a>
				</div>
			</div>
		</div>
		<!-- //contents -->
	</div>
	<!-- //contaier -->
<script>
$(document).ready(function() {
	load_fnc('1', '0', '0'); //menu script
});
</script>	
<%@include file="/WEB-INF/jsp/admin/inc/footer.jsp"%>