$(document).ready(function() {
    $("#idChkButton").click(function() {
        var username = $("#username").val();

        if(username == '' || username.length == 0) {
            $("#label1").css("color", "red").text("아이디는 필수 입력 사항입니다.");
    		return false;
        }

        $.ajax({
            url : './register',
            data : {
                username : username
            },
            type : 'POST',
            dataType : 'json',
            success : function(result) {
                if (result == true) {
                    $("#label1").css("color", "green").text("사용 가능한 ID 입니다.");
                } else {
                    $("#label1").css("color", "red").text("이미 사용중인 ID 입니다.");
                    $("#id").val('');
                }
            }
        });

    });
})
