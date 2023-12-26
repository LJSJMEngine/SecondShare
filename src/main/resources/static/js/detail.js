
let buttonFlag = false;
$(function(){


    // 글 삭제 버튼
    $("#btnDel").click(function(){
        let answer = confirm("게시글을 삭제하시겠습니까?");
        if(answer){
            $("form[name='frmDelete']").submit();
        }
    });

    // 거래 확인 버튼
    $("#chkTrade").click(function(){
        let answer = confirm("거래를 완료하시겠습니까? \n거래가 완료되면 게시글의 거래가 종료되고, \n거래한 회원에게 알림이 갑니다.");
        if(answer){
            publishNotice("해당 게시글이 거래가 종료되었습니다.","SOLDOUT");
            $("form[name='chkTrade']").submit();
        }
    });

    // 현재 글의 id
    const id = $("input[name='post_id']").val().trim();

    // 현재 글의 댓글들 불러오기
    loadComment(id);

    // 댓글 등록하기
    $("#btn_comment").click(function(){
        if(buttonFlag)
            return;
        buttonFlag =true;
        // 입력한 댓글
        const content = $("#input_comment").val().trim();

        if(!content){
            alert("댓글을 입력해주세요!");
            $("#input_comment").focus();
            return;
        }

        // 댓글 값 전달
        const data = {
            "id": id,
            "user_id": logged_id,
            "content": content,
        };

        $.ajax({
            url: "/comment/write",
            type: "POST",
            data: data,
            cache: false,
            success: function(data, status){
                if(status == "success"){
                    if(data.status !== "OK"){
                        alert(data.status);
                        return;
                    }

                    publishNotice(content,"ADDCOMMENT");
                    loadComment(id);
                    $("#input_comment").val('');
                    buttonFlag = false;
                }
            }
        })
    });

});

// 특정 글의 댓글 목록 불러오기
function loadComment(post_id){
    $.ajax({
        url: "/comment/list?id=" + post_id,
        type: "GET",
        cache: false,
        success: function(data, status){
            if(status == "success"){
                if(data.status !== "OK"){
                    alert(data.status);
                    return;
                }
                buildComment(data);

                addDelete();
            }
        },
    });
}
function onClickChatBtn(commentUserId){
    var $form = $('<form action="/chat/room" method="post"></form>');

//https://joyhong.tistory.com/104

	$form.appendTo('body');

    const postId = $("input[name='post_id']").val().trim();
	var post_id = $('<input type="hidden" id="post_id" name="post_id" value="' + postId + '">');
	var buyer_id = $('<input type="hidden" id="buyer_id" name="buyer_id" value="' + commentUserId + '">');

	$form.append(post_id).append(buyer_id);
	$form.submit();
}
function buildCommentChat(comment){
        const chatBtn = "";

        let commentUser_id = parseInt(comment.user.id);
        const postId = $("input[name='post_id']").val().trim();
        const funcName = "onClickChatBtn(" + commentUser_id + ")";
        console.log(funcName);
        if((logged_id == commentUser_id && logged_id != postData.user_id) ||
            (logged_id != commentUser_id && logged_id == postData.user_id)) {
            const newBtn = `
            <button type="button btn-outline-dark" onclick="${funcName}">1:1 채팅하기</button>`;
            return newBtn;
        }
        return chatBtn;
}
function publishNotice(content,type){
        const postId = $("input[name='post_id']").val().trim();
        publishNoticeMessage(type, "/pub/Notice", content, postData.user_id, postId);
}
function buildComment(result){
    console.log("COMMENT ADD");
    $("#cmt_cnt").text(result.count);   // 댓글 총 개수
    const out = [];

    result.data.forEach(comment => {
        let id = comment.id;
        let content = comment.content.trim();
        let regdate = comment.regdate;
        let user_id = parseInt(comment.user.id);
        let username = comment.user.username;
        let name = comment.user.name;

        const chatBtn = buildCommentChat(comment);

        const delBtn = (logged_id !== user_id) ? '' : `
                <i class="btn fa-solid fa-delete-left text-danger" data-bs-toggle="tooltip"
                data-cmtdel-id="${id}" title="삭제"></i>
                `;
        const row = `
            <tr>
                <td><span><strong>${username}</strong>(${name})</span></td>
                <td>
                    <span>${content}</span>${delBtn}
                </td>
                <td><span>${regdate}</span></td>
                <td>${chatBtn}</td>
            </tr>
            `;
        out.push(row);
    });

    $("#cmt_list").html(out.join("\n"));
}

// 댓글 삭제 기능
function addDelete(){
    // 현재 글의 id
    const id = $("input[name='id']");

    $("[data-cmtdel-id]").click(function(){
        if(!confirm("댓글을 삭제하시겠습니까?")) return;

        const comment_id = $(this).attr("data-cmtdel-id");

        $.ajax({
            url: "/comment/delete",
            type: "POST",
            cache: false,
            data: {"id": comment_id},
            success: function(data, status){
                if (status == "success"){
                    if(data.status !== "OK"){
                        alert(data.status);
                        return;
                    }

                    loadComment(id);
                }
            }
        });
    });
}

// ----------------- 좋아요 기능 -------------------------
// 클릭 이벤트 리스너 등록
document.addEventListener('DOMContentLoaded', function() {
    // 하트 아이콘과 좋아요 개수를 나타내는 요소 가져오기
    var heartIcon = document.getElementById('heartIcon');
    var likeCountElement = document.getElementById('likeCount');

    // 클릭 이벤트 리스너 등록
    document.getElementById('likeSection').addEventListener('click', function() {
        // 중복 클릭 방지
        if (this.disabled) {
            return;
        }

        // 버튼 비활성화
        this.disabled = true;

        // 현재 post_id
        var postId = getPostIdFromUrl();

        // 현재 user_id
        $.get('/board/detail/currentUserId', function(response) {
            var userId = response;

            // 콘솔에 현재 사용자 ID 출력
            console.log('현재 사용자 ID:', userId);

            // 서버로 전송할 데이터 준비
            var requestData = {
                user_id: userId,
                post_id: postId,
            };

            // 서버로 AJAX 요청
            $.ajax({
                type: 'POST',
                url: '/board/detail/heart/' + postId,
                contentType: 'application/json;charset=UTF-8',
                data: JSON.stringify(requestData),
                success: function(response) {
                    // 서버 응답 성공 시 처리
                    if (response) {
                        // 하트 아이콘의 클래스를 업데이트
                        heartIcon.classList.toggle('fas');
                        heartIcon.classList.toggle('far');
                        heartIcon.classList.toggle('text-danger');

                        // 업데이트된 좋아요 개수로 텍스트 업데이트
                        // likeCountElement.textContent = response.likeCount;

                        // 버튼 다시 비활성화
                        document.getElementById('likeSection').disabled = false;

                        // 페이지 전체를 새로고침
                        location.reload(true);

                    } else {
                        console.error('좋아요 상태 업데이트 실패');
                    }
                },
                error: function(error) {
                    // 버튼 다시 활성화
                    document.getElementById('likeSection').disabled = false;

                    console.error('AJAX 요청 실패', error);
                }
            });
        });
    });

    // 현재 post_id 가져오는 함수
    function getPostIdFromUrl() {
        // 현재 URL에서 pathname을 가져옴 (예: "/board/detail/123")
        var pathname = window.location.pathname;

        // URL을 '/'로 분리하고, 마지막 부분이 게시물 ID일 것이므로 추출
        var parts = pathname.split('/');
        var postId = parts[parts.length - 1];

        // 추출한 게시물 ID 반환 (문자열로 반환됨)
        return postId;
    }

    // 콘솔에 게시물 ID 출력
    console.log('현재 게시물 ID:', getPostIdFromUrl());
});
