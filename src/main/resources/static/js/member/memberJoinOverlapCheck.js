$(function(){
	
	// 아이디 중복 확인
	$('#idOverlapBtn').on('click', function(e){
		e.preventDefault();
		var memberId = $('#inputId').val();
		console.log(`memberId:${memberId}`);
		
		if($("#inputId").val() ==''){
            alert('아이디를 입력하세요');
            $("#inputId").focus();
            return false;
        }
		
		$.ajax({
            url: '/memberIdOverlapChk',
            type: 'POST',
            data: memberId,
            dataType: 'json',
            contentType: "application/json; charset=UTF-8",
            success: function (response) {
                if(response.result == 1){
                	alert('사용 가능한 아이디 입니다.')
                    //$('#idOverlapMessage').text('사용가능한 아이디 입니다.');
                    $('#idOverlapClickChk').val(1);
                } else if(response.result == 0){
                	alert('이미 가입된 아이디 입니다');
                	//$('#idOverlapMessage').text('이미 가입된 아이디 입니다');
                } else {
                    alert('검사 중 에러가 발생했습니다');
                }
            },
            error: function(jqXHR, textStatus, errorThrown){
                alert("arjax error : " + textStatus + "\n" + errorThrown);
            }
        });
	});
	
	// 값을 바꿨을 경우 중복확인 버튼을 다시 눌러야함 (중복확인 버튼 눌럿는지 체크하는 값을 0으로 셋팅) 0:안누름 / 1:누름
	$("#inputId").on('change', function(e) {
		e.preventDefault();
		$('#idOverlapClickChk').val(0);
	});
	
	
	// 이메일 중복 확인
	$('#emailOverlapBtn').on('click', function(e){
		e.preventDefault();
		var memberEmail = $('#inputEmail').val();
		console.log(`memberEmail:${memberEmail}`);
		
        if(memberEmail == ''){
            alert('이메일을 입력하세요');
            $("#inputEmail").focus();
            return false;
        } else {
            var emailRegex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
            if (!emailRegex.test(memberEmail)) {
                alert('이메일 주소가 유효하지 않습니다. ex)abc@gmail.com');
                $("#inputEmail").focus();
                return false;
            }
        }
		
		$.ajax({
            url: '/memberEmailOverlapChk',
            type: 'POST',
            data: memberEmail,
            dataType: 'json',
            contentType: "application/json; charset=UTF-8",
            success: function (response) {
                if(response.result == 1){
                	alert('사용 가능한 이메일 입니다.')
                    //$('#emailOverlapMessage').text('사용가능한 이메일 입니다.');
                    $('#emailOverlapClickChk').val(1);
                	// 회원 등록할 때 중복확인 버튼을 눌렀다는 걸 알려줄 값   0:안누름 / 1:누름
                } else if(response.result == 0){
                	alert('사용 중인 이메일 입니다');
                	$("#inputEmail").focus();
                	//$('#emailOverlapMessage').text('이미 가입된 이메일 입니다');
                } else {
                    alert('검사 중 에러가 발생했습니다');
                }
            },
            error: function(jqXHR, textStatus, errorThrown){
                alert("arjax error : " + textStatus + "\n" + errorThrown);
            }
        });
	});
	
	// 값을 바꿨을 경우 중복확인 버튼을 다시 눌러야함 (중복확인 버튼 눌럿는지 체크하는 값을 0으로 셋팅) 0:안누름 / 1:누름
	$("#inputEmail").on('change', function(e) {
		e.preventDefault();
		$('#emailOverlapClickChk').val(0);
	});
});