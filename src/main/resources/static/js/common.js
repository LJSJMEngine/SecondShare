let stomp;
let userId = -1;
$(function () {

    stomp = new StompJs.Client({
        brokerURL: 'ws://localhost:8093/ws-stomp'
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
    }

}
function userSet(user){
    console.log("activation");
    console.log(user);
    userId = user;
}