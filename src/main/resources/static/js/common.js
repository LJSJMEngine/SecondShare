let stomp;
let userId = -1;
let isModalTrigger = false;
$(function () {

    $("#notificationArea").on('hidden.bs.modal', function(e) {
        if(!isModalTrigger) return;
        isModalTrigger = false;
        //알림표시 제거
        $(".noticeRedButton").css("display" ,"none");

        $(".notificationList").empty();
        publishNoticeMessage("INIT", "/pub/NoticeInit", "getMessageList", userId,-1);

    });
    $("#notificationArea").on('shown.bs.modal', function(e) {
        if(isModalTrigger) return;
        isModalTrigger = true;
        //알림표시 제거
        publishNoticeMessage("UPDATE", "/pub/NoticeUpdateRead", "setReadChk", userId,-1);
    });
    stomp = new StompJs.Client({
        brokerURL: 'ws://' + location.host + '/ws-notice'
    });

    stomp.activate();
    //Stomp연결
    stomp.onConnect = (frame) => {
        stomp.subscribe('/sub/notice/' + userId, function (notice) {
                var content = JSON.parse(notice.body);
                subNotice(content);
        });
        if(userId > -1) // Notice초기화
            publishNoticeMessage("INIT", "/pub/NoticeInit", "getMessageList", userId,-1);


    };

    //웹소켓 에러
    stomp.onWebSocketError = (error) => {
        console.error('Error with websocket', error);
    };

    //Stomp 에러
    stomp.onStompError = (frame) => {
        console.error('Broker reported error: ' + frame.headers['message']);
        console.error('Additional details: ' + frame.body);
    };
});
function publishNoticeMessage(TYPE , dest , message, userId, postId) {
    var JsonBody = JSON.stringify({user_id: userId, content: message, status: 0});
    switch(TYPE){
    case "SENDCHAT":
        JsonBody = JSON.stringify({subject: "새로운 채팅 알림", user_id: userId, contents: message, status: 0, post_id : postId});
    break;
    case "ADDCOMMENT":
        JsonBody = JSON.stringify({subject: "새로운 댓글 알림", user_id: userId, contents: message, status: 0, post_id : postId});
    break;
    case "SOLDOUT":
        JsonBody = JSON.stringify({subject: "판매완료 알림", user_id: userId, contents: message, status: 0, post_id : postId});
    break;
    default:
        JsonBody = JSON.stringify({user_id: userId, content: message, status: 0});
    break;
    }
        stomp.publish({ // 메세지 보내기.
            destination: dest,
            body: JsonBody
        });
}
function subNotice(notice) {
        var displayState = notice.readChk ? "none" : "inline";
        $(".noticeRedButton").css("display" ,displayState);
    if(notice.status == -1) {
        return;
    }
    const content = notice.contents;
    const stateClass = notice.status;
    let subject = notice.subject;
    let subjectColor = "h4";
    if(notice.readChk)
    {
        subject += " (읽음)";
        subjectColor = "h5 text-muted";
    }
    let btnHtml = ``;
        if(notice.post_id != 0)
        btnHtml =  `<a href="/board/detail/` + notice.post_id+ `" class="col-4 btn btn-secondary popover-test justify-content-md-end" >바로가기</a>`;
    const noticeContent =`
        <span class="text-start text-break state state-` + stateClass + `"></span>
        <p class="text-start text-break ` + subjectColor + `">${subject}</p>
        <div class="row row-cols-2">
            <p class="col-8 text-start">${content}</p>
            ${btnHtml}
        </div>
        <hr>`;

    $(".notificationList").prepend(noticeContent);

}
function userSet(user){
    userId = user;
}