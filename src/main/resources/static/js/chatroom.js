$(function () {
    console.log(RoomData);
    var sock = new SockJS("http://localhost:8093/ws-stomp");
    var roomId = RoomData.room_id;
    console.log(sock);


    var client = Stomp.over(sock); // 1. SockJS를 내부에 들고 있는 client를 내어준다.



    client.connect({}, function (frame) {
            console.log("Connected");
             //            //// 3. send(path, header, message)로 메시지를 보낼 수 있다.
            client.send('/pub/message', {}, JSON.stringify({chatRoomId: 2,message:"messageTest", writer: 3}));
//// 4. subscribe(path, callback)로 메시지를 받을 수 있다. callback 첫번째 파라미터의 body로 메시지의 내용이 들어온다.
            client.subscribe('/sub/chat/room/' + roomId, function (chat) {
                console.log(chat);
                var content = JSON.parse(chat.body);
                subChat(console.message);
        });
    });



    $( "#sendChat" ).click(function () {
        var message = $("#chatInput").val();
        console.log(message);
        console.log(client);
        client.send('/chat/pub/chat/message', {}, JSON.stringify({chatRoomId: roomId, message: message, writer: RoomData.buyer_id}));
        console.log(client);
        $("#chatInput").val('');
    });
});
function subChat(message) {
    $("#chatList").append("<tr><td>" + message + "</td></tr>");
}
