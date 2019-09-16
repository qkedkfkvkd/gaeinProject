$(function(){
	
	// 학부모 휴대폰 번호 중복 확인
	$('#parentPhoneOverlapBtn').on('click', function(e){
		e.preventDefault();
		var inputParentPhone = $('#inputParentPhone').val();
		console.log(`inputParentPhone:${inputParentPhone}`);
		
		if($("#inputParentPhone").val() ==''){
            alert('학부모의 휴대폰 번호를 입력하세요');
            $("#inputParentPhone").focus();
            return false;
        }
		
		$.ajax({
            url: '/parentPhoneOverlapChk',
            type: 'POST',
            data: inputParentPhone,
            dataType: 'json',
            contentType: "application/json; charset=UTF-8",
            success: function (response) {
                if(response.result == 1){
                	alert('사용 가능한 휴대폰 번호 입니다.')
                    $('#parentPhoneOverlapClickChk').val(1);
                } else if(response.result == 0){
                	alert('이미 가입된 휴대폰 번호 입니다');
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
	$("#inputParentPhone").on('change', function(e) {
		e.preventDefault();
		$('#parentPhoneOverlapClickChk').val(0);
	});
	
	
	// 학생 등록 처리 전 공백  및  이메일 유효성 검사  ex)abc@gmail.com
	$('#StudentJoinFrmBtn').on('click', function(e){
		e.preventDefault();
		
		var idOverlapClickChk = $('#idOverlapClickChk').val();
		var emailOverlapClickChk = $('#emailOverlapClickChk').val();
		
		console.log(`idOverlapClickChk:${idOverlapClickChk}`);
		console.log(`emailOverlapClickChk:${emailOverlapClickChk}`);
		console.log('0이면 사용 중, 1이면 사용 가능');
		
        if($("#inputId").val() ==''){
            alert('아이디를 입력하세요');
            $('#idOverlapClickChk').val(0);
            // 만약 아이디를 입력하고 중복확인 버튼을 눌러서 사용가능한 아이디라고 나오면 중복확인 버튼 체크값이 1로 바뀐다.
            // 재입력하고자 값을 지웠을 경우 다시 눌러줘야한다.
            $("#inputId").focus();
            return false;
        }
        
        if($("#idOverlapClickChk").val() == 0){
            alert('아이디 중복확인을 해주세요');
            $("#inputId").focus();
            return false;
        }
        
        if($("#inputPassword").val() ==''){
            alert('비밀번호를 입력하세요');
            $("#inputPassword").focus();
            return false;
        }

        if($("#inputPasswordCheck").val() ==''){
            alert('비밀번호를 다시 한번 더 입력하세요');
            $("#inputPasswordCheck").focus();
            return false;
        }
        
        if($("#inputPassword").val()!== $("#inputPasswordCheck").val()){
            alert('비밀번호를 둘다 동일하게 입력하세요');
            return false;
        }
        
        if($("#inputName").val() ==''){
            alert('이름을 입력하세요');
            $("#inputName").focus();
            return false;
        }
        
        if($("#inputBirth").val() ==''){
            alert('생년월일을 선택하세요');
            $("#inputBirth").focus();
            return false;
        }
        
        var email = $('#inputEmail').val();
        if(email == ''){
            alert('이메일을 입력하세요');
            $('#emailOverlapClickChk').val(0);
            // 중복확인 버튼을 눌러줘야하므로 중복확인 버튼을 안누른 값으로 셋팅한다.
            // 0:안누름 / 1:누름
            $("#inputEmail").focus();
            return false;
        } else {
            var emailRegex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
            if (!emailRegex.test(email)) {
                alert('이메일 주소가 유효하지 않습니다. ex)abc@gmail.com');
                $("#inputEmail").focus();
                $('#emailOverlapClickChk').val(0);
                // 중복확인 버튼을 눌러줘야하므로 중복확인 버튼을 안누른 값으로 셋팅한다.
                // 0:안누름 / 1:누름
                return false;
            }
        }
        
        if($("#emailOverlapClickChk").val() == 0){
            alert('이메일 중복확인을 해주세요');
            $("#inputEmail").focus();
            return false;
        }
        
        if($("#inputPhone").val() ==''){
            alert('휴대폰 번호를 입력하세요');
            $("#inputPhone").focus();
            return false;
        }
        
        if($("#inputParentName").val() ==''){
            alert('학부모의 이름을 입력하세요');
            $("#inputParentName").focus();
            return false;
        }
        
        if($("#inputParentPhone").val() ==''){
            alert('학부모의 휴대폰 번호를 입력하세요');
            $('#parentPhoneOverlapClickChk').val(0);
            // 중복확인 버튼을 눌러줘야하므로 중복확인 버튼을 안누른 값으로 셋팅한다.
            // 0:안누름 / 1:누름
            $("#inputParentPhone").focus();
            return false;
        }
        
        if($("#parentPhoneOverlapClickChk").val() == 0){
            alert('학부모 휴대폰번호 중복확인을 해주세요');
            $("#inputParentPhone").focus();
            return false;
        }
        
        if($("#inputAddr").val() ==''){
            alert('주소를 입력하세요');
            $("#inputAddr").focus();
            return false;
        }
        
        /*if($("#agree").is(":checked") == false){
            alert('약관에 동의하셔야 합니다');
            return false;      
        }*/
        
        $('#studentJoinFrm').submit();
        
        /*$.ajax({
            url: '/addMember',
            type: 'POST',
            data: {
            	member_id:$('#inputId').val(),
            	member_pw:$('#inputPassword').val(),
                name:$("#inputName").val(),
                
                email:$('#InputEmail').val(),
                
                telNO:$("#inputtelNO").val(),
                mobileNO:$("#inputMobile").val()
            },
            dataType: "json",
            success: function (response) {
                if(response.result == 1){
                    alert('가입 완료');
                    location.replace('../index.php'); // 화면 갱신
                } else if(response.result == 0){
                    alert('이미 가입된 아이디입니다');
                } else if(response.result == -2){
                    alert('입력된 값이 없습니다');
                } else {
                    alert('등록중에 에러가 발생했습니다');
                }
            },
            error: function(jqXHR, textStatus, errorThrown){
                alert("arjax error : " + textStatus + "\n" + errorThrown);
            }
        });*/
        
    });

});