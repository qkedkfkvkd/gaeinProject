$(function(){
	// 강사 등록 처리 전 공백  및  이메일 유효성 검사  ex)abc@gmail.com
	$('#StudentJoinFrmBtn').click(function(e){
		e.preventDefault();
		
		var idOverlapClickChk = $('#idOverlapClickChk').val();
		var emailOverlapClickChk = $('#emailOverlapClickChk').val();
		
		console.log(`idOverlapClickChk:${idOverlapClickChk}`);
		console.log(`emailOverlapClickChk:${emailOverlapClickChk}`);
		console.log('0이면 사용 중, 1이면 사용 가능');
		
        if($("#inputId").val() ==''){
            alert('아이디를 입력하세요');
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
            $("#inputEmail").focus();
            return false;
        } else {
            var emailRegex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
            if (!emailRegex.test(email)) {
                alert('이메일 주소가 유효하지 않습니다. ex)abc@gmail.com');
                $("#inputEmail").focus();
                return false;
            }
        }
        
        if($("#emailOverlapClickChk").val() == 0){
            alert('이메일 중복확인을 해주세요');
            $("#inputId").focus();
            return false;
        }
        
        if($("#inputPhone").val() ==''){
            alert('휴대폰 번호를 입력하세요');
            $("#inputPhone").focus();
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
        
        $('#teacherJoinFrm').submit();
        
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