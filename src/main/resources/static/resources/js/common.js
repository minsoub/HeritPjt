$(function(){
	// date picker
	if($('.datePic').length > 0){
		$('.datePic').datepicker({
			dateFormat:"yy.mm.dd",
			showOn:"button",
			buttonText:'',
			buttonImageOnly:true,
			buttonImage:"/resources/images/common/btn_cal.png"
		});
	}

	var tblChckAll = $('table th input[type="checkbox"].chckAll');
	var inputChck = $('table td input[type="checkbox"]');
	inputChck.on('click', function(){
		var chckedLeng = $('table td input[type="checkbox"]:checked');
		if(inputChck.length == chckedLeng.length){ // 전체 체크 되었을 시
			tblChckAll.prop('checked', true);
		} else {
			tblChckAll.prop('checked', false);
		}
	});
	tblChckAll.on('click', function(){
		var chckStatus = $(this).prop('checked');
		var cntChck = $(this).parents('table').find('td input[type="checkbox"]');
		cntChck.each(function(){
			$(this).prop('checked', chckStatus);
		 });
	});
	// input type="file"
	var fileTarget = $('.wrapInput.typeFile .inputFile');
	fileTarget.on('change', function(){	
		if(window.FileReader){ // modern browser
			if(!$(this)[0].files[0].type.match(/image\//)) return;
			var filename = $(this)[0].files[0].name;
			var reader = new FileReader();
			reader.onload = function(e){
				var src = e.target.result;
				$('.listThumbProd').remove();
				$('.wrapInput.typeFile').after('<div class="listThumbProd"><img src="' + src + '" alt=""></div>');
			}
			reader.readAsDataURL($(this)[0].files[0]);
		}
		else{ // old IE
			// var filename = $(this).val().split('/').pop().split('\\').pop(); // 파일명만 추출
		}
		$(this).siblings('.input').val(filename);
	});

	// layer popup - open
	window.openLayer = function(el){
		var h = 0;
		var w = 0;
		var scArea = $('.wrapPop .cnt .scrollArea');
		$(el).show();
		h = $(el).find('.popCnt').outerHeight();
		w = $(el).find('.popCnt').outerWidth();
		$(el).find('.popCnt').css({'margin-top':-(h/2), 'margin-left':-(w/2)});
		if($(el).find('.scrollArea').length > 0){ // has scrollArea
			scArea.scrollTop(0);
		}
	}
	// layer popup - close
	window.closeLayer = function(el){
		$(el).hide();
	}
	// layer popup - close
	var btnClosePop = $('.wrapPop .btnClose, .wrapPop .btnClose a');
	btnClosePop.on('click', function(){
		var el = $(this).parents('.wrapPop');
		closeLayer(el);
		return false;
	});

	// user 브라우저 버전 체크
	userAgentCheck();
});

/**
 * @module [공통] user agent check
 * @description (적용된 예)<html class="ie10">
 * @example
 *  (적용된 예)<html class="ie10">
 */
function userAgentCheck(){
	var ua = navigator.userAgent.toString().toLowerCase();
	var agent = {};
	var $html = document.getElementsByTagName('html')[0];
	var addClassName = '';

	agent = {
			ie : (/msie/i).test(ua) || (/trident/i).test(ua),
			msedge : (/edge/i).test(ua), // MS edge(applewekit,chrome,safari)
			firefox: (/firefox/i).test(ua),
			webkit: (/applewebkit/i).test(ua),
			chrome: (/chrome/i).test(ua)
	};

	agent.safari = (agent.webkit) && (!agent.chrome);

	// ie 버전 체크
	if(agent.ie){
		var ieVersion = ua.match(/(msie |trident.*rv[ :])([0-9]+)/)[2];
		ieVersion = Math.floor(ieVersion);
		agent.ie = "ie"+ieVersion;
	}

	// ms edge, chrome, safari일 경우 중복되는 값 삭제하기
	if(agent.msedge){ agent.webkit = agent.chrome = agent.safari = false; }

	if(agent.chrome || agent.safari){ agent.webkit = false; }

	var reverseFn = function(){
		var classArr = [];
		for(var value in agent){
			if(agent[value]){
				if(value == 'os' || value == 'ie'){
					classArr.push(agent[value]);
				}else{
					classArr.push(value);
				}
			}
		}
		addClassName = classArr.reverse().join(' ');
	}();
	$html.className = addClassName;
}

// 수면분석 - 주간 calendar
function monthPicker(){
	$('.monthPic').monthpicker({
		monthNames: ['1월(JAN)', '2월(FEB)', '3월(MAR)', '4월(APR)', '5월(MAY)', '6월(JUN)',
			'7월(JUL)', '8월(AUG)', '9월(SEP)', '10월(OCT)', '11월(NOV)', '12월(DEC)'],
		monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
		showOn:'button',
		buttonImage:'/resources/images/common/btn_cal.png',
		buttonImageOnly:true,
		buttonText:'',
		changeYear:false,
		yearRange:'c-2:c+2',
		dateFormat:'yy.mm'
	});
}

// 수면분석 - 연간 calendar
function yearPicker(){
	$('.yearPic').datePicker({
		format:'YYYY',
		language:'en',
		min:'2018',
		max:'2029'
	});
}