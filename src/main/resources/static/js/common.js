$(function(){

let stomp;
$(function () {
    $("#popbutton").click(function(){
            console.log("콘솔체크");
           $("#notificationArea").modal('show');
       })
   })
    stomp = new StompJs.Client({
        brokerURL: 'ws://localhost:8093/ws-stomp'
    });
    stomp.activate();

    //Stomp연결
    stomp.onConnect = (frame) => {
        stomp.subscribe('/sub/notice/', function (notice) {
                var content = JSON.parse(notice.body);
                console.log(content);
                subChat(content);
        });
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