$(function(){
	
	// 특정 학생의 결제정보 등록 처리 전 선택체크 및 공백 유효성 검사
	$('#paymentInfoJoinFrmBtn').on('click', function(e){
		e.preventDefault();
		
        if($("#checkPaymentWay option:selected").val() ==''){
            alert('결제수단을 선택하세요');
            $("#checkPaymentWay").focus();
            return false;
        }
        
        if($("#inputPaymentContent").val() ==''){
            alert('결제수단내용을 입력하세요');
            $("#inputPaymentContent").focus();
            return false;
        }
        
        if($("#inputPaymentDay").val() ==''){
            alert('납부일자를 선택하세요');
            $("#inputPaymentDay").focus();
            return false;
        }
        
        if($("#inputPaymentScheduleMoney").val() ==''){
            alert('납부예정금액을 입력하세요');
            $("#inputPaymentScheduleMoney").focus();
            return false;
        }
        
        if($("#inputActualityPaymentMoney").val() ==''){
            alert('실납부금액을 입력하세요');
            $("#inputActualityPaymentMoney").focus();
            return false;
        }
        
        if($("#inputStartCourseDay").val() ==''){
            alert('시작 수업일을 선택하세요');
            $("#inputStartCourseDay").focus();
            return false;
        }
        
        if($("#inputEndCourseDay").val() ==''){
            alert('마지막 수업일을 선택하세요');
            $("#inputEndCourseDay").focus();
            return false;
        }
        
        /*if($("#agree").is(":checked") == false){
            alert('약관에 동의하셔야 합니다');
            return false;      
        }*/
        
        $('#paymentInfoJoinFrm').submit();
        
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