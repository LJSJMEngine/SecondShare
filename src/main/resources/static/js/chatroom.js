let stompClient;
let roomId = RoomData.room_id;
$(function () {
    stompClient = new StompJs.Client({
        brokerURL: 'ws://localhost:8093/ws-stomp'
    });
    stompClient.activate();

    //Stomp연결
    stompClient.onConnect = (frame) => {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/sub/chat/room/' + roomId, function (chat) {
                var content = JSON.parse(chat.body);
                console.log(content);
                subChat(content.content);
        });
        stompClient.publish({
          destination: "/pub/init",
          body: JSON.stringify({room_id: roomId, content:"getMessageList", sender_id: RoomData.buyer_id})
        });
    };

    //웹소켓 에러
    stompClient.onWebSocketError = (error) => {
        console.error('Error with websocket', error);
    };

    //Stomp 에러
    stompClient.onStompError = (frame) => {
        console.error('Broker reported error: ' + frame.headers['message']);
        console.error('Additional details: ' + frame.body);
    };
    $( "#sendChat" ).click(function () {
        messageSend();
    });
    $("#sendChat").keydown(function(){
        if(event.keyCode == 13){
            messageSend();
        }
    });

});
function messageSend() {
        var message = $("#chatInput").val();
        console.log(stompClient);
        stompClient.publish({
            destination: "/pub/message",
            body: JSON.stringify({room_id: roomId, content: message, sender_id: RoomData.buyer_id})
        });
        $("#chatInput").val('');
}
function subChat(message) {
    $("#chatList").append("<tr><td>" + message + "</td></tr>");
}
