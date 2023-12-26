let stomp;
let userId = -1;
let isModalTrigger = false;
$(function () {

    $("#notificationArea").on('hidden.bs.modal', function(e) {
        if(!isModalTrigger) return;
        isModalTrigger = false;
        //알림표시 제거
        $(".noticeRedButton").css("display" ,"none");

        publishMessage("UPDATE", "/pub/NoticeUpdateRead", "setReadChk", userId);
        $(".notificationList").empty();
        publishMessage("INIT", "/pub/NoticeInit", "getMessageList", userId);

    });
    $("#notificationArea").on('shown.bs.modal', function(e) {
        if(isModalTrigger) return;
        isModalTrigger = true;
        //알림표시 제거
    });
    stomp = new StompJs.Client({
        brokerURL: 'ws://' + location.host + '/ws-notice'
    });

    stomp.activate();

    //Stomp연결
    stomp.onConnect = (frame) => {
        stomp.subscribe('/sub/notice/' + userId, function (notice) {
                var content = JSON.parse(notice.body);
                console.log(content);
                subNotice(content);
        });
        if(userId > -1) // Notice초기화
            publishMessage("INIT", "/pub/NoticeInit", "getMessageList", userId);


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
function publishMessage(TYPE , dest , message, userId) {
        var JsonBody = JSON.stringify({user_id: userId, content: message, status: 0})

        console.log("[PUBLISH] : dest : " + dest + " Message : " + message + " status :" + status);
        stomp.publish({ // 메세지 보내기.
            destination: dest,
            body: JsonBody
        });
}
function subNotice(notice) {
    if(notice.status == -1) {
        var displayState = notice.readChk ? "none" : "block";
        $(".noticeRedButton").css("display" ,displayState);
        console.log("NOTICE RED BUTTON CONTROL");
        //빨강버튼 켜라
    }
    else {
        const content = notice.contents;
        const stateClass = notice.status;
        let subject = notice.subject;
        let subjectColor = "h4";
        if(notice.readChk)
        {
            subject += " (읽음)";
            subjectColor = "h5 text-muted";
        }
        const noticeContent =`
            <span class="text-start text-break state state-` + stateClass + `"></span>
            <p class="text-start text-break ` + subjectColor + `">${subject}</p>
            <div class="row row-cols-2">
                <p class="col-8 text-start">${content}</p>
                <a href="#" class="col-4 btn btn-secondary popover-test justify-content-md-end" >바로가기</a>
            </div>
            <hr>`;

        $(".notificationList").append(noticeContent);
    }

}
function userSet(user){
    userId = user;
}