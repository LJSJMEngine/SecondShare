let stompClient;
let roomId = RoomData.room_id;
$(function () {
    stompClient = new StompJs.Client({
        brokerURL: 'ws://localhost:8093/ws-stomp'
    });
    stompClient.activate();
    console.log(MessageList);
    console.log(dataCheck);

    //Stomp연결
    stompClient.onConnect = (frame) => {
        stompClient.subscribe('/sub/chat/room/' + roomId, function (chat) {
                var content = JSON.parse(chat.body);
                subChat(content.content);
        });

        publishMessage("INIT", "/pub/init", "getMessageList", RoomData.buyer_id);


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
    $("#chatInput").keydown(function(event){

        if(event.keyCode == 13){
            messageSend();
        }
    });

});
function messageSend() {
        var message = $("#chatInput").val();
        publishMessage("MESSAGE" , "/pub/message", message, RoomData.buyer_id);
        $("#chatInput").val('');
}
function subChat(message) {
    $("#chatList").append("<tr><td>" + message + "</td></tr>");
}
function publishMessage(TYPE , dest , message, senderId) {

           var JsonBody = JSON.stringify({room_id: roomId, content: message, sender_id: senderId})

        if(TYPE == "MESSAGE"){
            stompClient.publish({
                destination: "/pub/roomUpdateDate",
                body: JsonBody
            });
        }

        console.log("[PUBLISH] : dest : " + dest + " Message : " + message + " senderId :" + senderId);

        stompClient.publish({
            destination: dest,
            body: JsonBody
        });
}
