$(document).ready(function() {

    var isUsernameValidated = false;
    var isEmailValidated = false;

    // 아이디 중복확인
    $("#idChkButton").click(function(event) {
        event.preventDefault();
        var username = $("#username_reg").val();

        if(username == '' || username.length == 0) {
            $("#label1").css("color", "red").html("<br>아이디는 필수 입력 사항입니다.");
            event.preventDefault();
    		return false;
        }

        $.ajax({
            url : '/user/register/username',
            data : {
                username : username
            },
            type : 'POST',
            dataType : 'json',
            success : function(result) {
                if (result == true) {
                    $("#label1").css("color", "limegreen").html("<br>사용 가능한 ID 입니다.");
                    isUsernameValidated = true;
                } else {
                    $("#label1").css("color", "red").html("<br>이미 사용중인 ID 입니다.");
                    $("#username").val('');
                    event.preventDefault();
    		        return false;
                }
            }
        });
    });


    // 비밀번호 형식 검증
    $("#password").on('input', function() {
        var password = $(this).val();
        var pwRegExp = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*\W).{8,22}$/;


        if (!pwRegExp.test(password)) {
            $("#label2").css("color", "red").html("<br>비밀번호 형식에 맞지 않습니다.");
        } else {
            $("#label2").css("color", "limegreen").html("<br>사용 가능한 비밀번호 입니다.");
        }

    });

    // 비밀번호 확인 검증
    $("#passwordChk").on('input', function() {
        var password = $("#password").val();
        var passwordConfirm = $(this).val();

        if (password !== passwordConfirm) {
            $("#label3").css("color", "red").html("<br>입력한 비밀번호와 일치하지 않습니다.");
        } else {
            $("#label3").css("color", "limegreen").html("<br>비밀번호가 일치합니다.");
        }
    });

    // 이메일 형식 검증
    $("#email").on('input', function() {

        var email = $(this).val();
        var emailRegExp = /^[a-zA-Z0-9]+@[a-zA-Z0-9]+\.[a-z]+$/;

        if (!emailRegExp.test(email)) {
            $("#label5").css("color", "red").html("<br>이메일 형식에 맞지 않습니다.");
        }
        else {
            $("#label5").css("color", "limegreen").html("<br>이메일 형식 확인.");
        }


        $.ajax({
            url : '/user/register/email',
            data : {
                email : email
            },
            type : 'POST',
            dataType : 'json',
            success : function(result) {
                if (result == true) {
                    $("#label6").css("color", "limegreen").html("<br>사용 가능한 이메일입니다.");
                    isEmailValidated = true;
                } else {
                    $("#label6").css("color", "red").html("<br>이미 사용중인 이메일입니다.");
                    $("#username_reg").val('');
                    event.preventDefault();
                    return false;
                }
            }
        });

    });


    // 필수 입력사항 기재 여부 및 형식 오류 검증 (회원가입 방지)
    $('form[name="frm"]').submit(function(event) {
        var username = $("#username_reg").val();
        var password = $("#password").val();
        var passwordChk = $("#passwordChk").val();
        var name = $("#name").val();
        var email = $("#email").val();

        if (username == '' || username.length == 0) {
            alert("아이디는 필수 입력 사항입니다.")
            $("#username_reg").focus();
            event.preventDefault();
            return false;
        }

        if (!isUsernameValidated) {
            alert("아이디 중복확인을 해주세요.")
            event.preventDefault();
            return false;
        }


        if(password == '' || password.length == 0) {
           alert("비밀번호는 필수 입력 사항입니다.")
           $("#password").focus();
           event.preventDefault();
           return false;
        }

        if(passwordChk == '' || passwordChk.length == 0) {
           alert("비밀번호 확인을 해주세요.")
           $("#passwordChk").focus();
           event.preventDefault();
           return false;
        }

        if(name == '' || name.length == 0) {
           alert("이름을 입력해주세요.")
           $("#name").focus();
           event.preventDefault();
           return false;
        }

        if(email == '' || email.length == 0) {
           alert("이메일을 입력해주세요.")
           $("#email").focus();
           event.preventDefault();
           return false;
        }
    });


    // 인증번호 전송
    $("#smsButton").click(function() {
        var phoneNum = $("#phoneNM").val();

        if (phoneNum === '') {
            alert("전화번호를 입력하세요.");
            return;
        }

        $.ajax({
            url: '/user/register/sms',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({phoneNumber: phoneNum}),
            success: function(response) {
                alert(response);
            },
            error: function(xhr, status, error) {
                alert("문자 전송 중 오류가 발생했습니다.")
            }
        });

    });


    $("#smsChkButton").click(function() {
        var phoneNumber = $("#phoneNM").val();
        var certificationNumber = $("#verifyNM").val();

        $.ajax({
            url: '/user/register/verify',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({ phoneNumber: phoneNumber, randomNumber: certificationNumber }),
            success: function(response) {
                $("#label4").css("color", "limegreen").html("<br>" + response);
            },
            error: function(xhr, status, error) {
                $("#label4").css("color", "red").html("<br>" + xhr.responseText);
                $("#verifyNM").val('').focus();
            }
        });
    });



    // -------------------------------------

    // 저장된 쿠키 확인
    var savedUsername = getCookie("username");
    if (savedUsername != '') {
        // If there is, fill the username field and check the 'Remember Me' box
        $("#username").val(savedUsername);
        $("#remember").prop("checked", true);
    }

    // 체크박스 변화 처리
    $("#remember").change(function() {
        if ($("#remember").is(":checked")) {
            // If checked, save the current username in a cookie
            setCookie("username", $("#username").val(), 7);
        } else {
            // If unchecked, delete the cookie
            deleteCookie("username");
        }
    });

    // 아이디가 바뀌면 쿠키 재설정
    $("#username").keyup(function() {
        if ($("#remember").is(":checked")) {
            setCookie("username", $("#username").val(), 7);
        }
    });

    // 쿠키 설정
    function setCookie(cookieName, value, exdays) {
        var exdate = new Date();
        exdate.setDate(exdate.getDate() + exdays);
        var cookieValue = encodeURIComponent(value) + "; expires=" + exdate.toGMTString();
        document.cookie = cookieName + "=" + cookieValue;
    }

    // 쿠키 삭제
    function deleteCookie(cookieName) {
        document.cookie = cookieName + "=; expires=Thu, 01 Jan 1970 00:00:01 GMT;";
    }

    // 쿠키 값 따오기
    function getCookie(cookieName) {
        var name = cookieName + "=";
        var decodedCookie = decodeURIComponent(document.cookie);
        var ca = decodedCookie.split(';');
        for (var i = 0; i < ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0) == ' ') {
                c = c.substring(1);
            }
            if (c.indexOf(name) == 0) {
                return c.substring(name.length, c.length);
            }
        }
        return "";
    }


});

