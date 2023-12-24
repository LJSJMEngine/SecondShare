$(function(){


    // 글 삭제 버튼
    $("#btnDel").click(function(){
        let answer = confirm("게시글을 삭제하시겠습니까?");
        if(answer){
            $("form[name='frmDelete']").submit();
        }
    });

    // 현재 글의 id
    const id = $("input[name='post_id']").val().trim();

    // 현재 글의 댓글들 불러오기
    loadComment(id);

    // 댓글 등록하기
    $("#btn_comment").click(function(){
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
                    loadComment(id);
                    $("#input_comment").val('');
                }
            }
        })
    });

    window.onload = function(){
        document.getElementById("chkTrade").onclick = function(){
            window.open("/board/chkTrade", "", "width=500px,height=350px,top=200px,left=200px;");
        }
    }

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
function buildComment(result){
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