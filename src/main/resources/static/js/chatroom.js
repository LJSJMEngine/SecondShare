$(function () {
    const stompClient = new StompJs.Client({
        brokerURL: 'ws://localhost:8093/ws-stomp'
    });
    var roomId = RoomData.room_id;
    stompClient.activate();

    //Stomp연결
    stompClient.onConnect = (frame) => {
    console.log('Connected: ' + frame);
    stompClient.subscribe('/sub/chat/room/' + roomId, function (chat) {
            var content = JSON.parse(chat.body);
            console.log(content);
            subChat(content.message);
    });

        stompClient.publish({
            destination: "/pub/message",
            body: JSON.stringify({roomId: 2, message:"messageTest", sender: 3})
        });

    stompClient.publish({
        destination: "/pub/message",
        body: JSON.stringify({roomId: 2, message:"messageTest", sender: 3})
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
        var message = $("#chatInput").val();
        stompClient.publish({
            destination: "/pub/message",
            body: JSON.stringify({roomId: roomId, message: message, sender: RoomData.buyer_id})
        });
        $("#chatInput").val('');
    });
});
function subChat(message) {
    $("#chatList").append("<tr><td>" + message + "</td></tr>");
}
