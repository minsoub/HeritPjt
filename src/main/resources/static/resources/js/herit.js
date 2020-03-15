/**
 * Json 데이터를 ajax로 호출해서 결과 완료 후 리턴 페이지로 이동한다.
 * 기본적으로 frm 폼데이터가 정의되어 있어야 한다. (기본적인 명 : form name=frm)
 * 
 * @param param
 * @param goUrl
 * @param returnUrl
 * @returns
 */
function goJsonInput(params, goUrl, returnUrl)
{
	if(confirm('요청작업을 진행 하시겠습니까?')){
		$.ajax({
			type : 'post',
			url : goUrl,
			dataType : 'json',
			data : params,
			success : function(result){		
				console.log(result);
				if (result == "1")
				{
					alert('요청을 완료하였습니다.');	
					if (returnUrl != "")
					{
						console.log(returnUrl);
						$('#frm').attr('method', "post");
						$('#frm').attr('action',  returnUrl);
						$('#frm').submit();
					}
				}else {
					alert("요청하신 작업에 에러가 발생하였습니다!!!");
				}
			},
			error : function(e){
				alert(e);
			}
		})
	}	
}

function goSaveCategory(mode, category_name, seq, goUrl)
{
	var params = {
		"seq" : seq, 
		"category_name" : category_name,
		"mode" : mode
	}
	var rlt;
	$.ajax({
		type : 'post',
		url : goUrl,
		dataType : 'json',
		data : params,
		success : function(result){		
			console.log(result);
			if (result == "-1")
			{
				alert("요청하신 작업에 에러가 발생하였습니다!!!");				
			}else {
				alert('요청을 완료하였습니다.');
				if (mode == "I")
				{
					// seq 번호를 리턴해야 한다.
					rlt = result;
				}
			}
		},
		error : function(e){
			alert(e);
		}
	});
	if (mode == "I")
	{
		return rlt;
	}
}

/**
 * Paging move
 * 
 * @param page
 * @returns
 */
function goPage(page){

	var f = document.frm;
	f.target = "_self";
	f.pageNo.value = page;
	f.action = location.href;	
	f.submit();
}