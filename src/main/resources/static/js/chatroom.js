
$(function () {
console.log(RoomData);
var sock = new SockJS("/ws-stomp");
console.log(sock);
        var client = Stomp.over(sock); // 1. SockJS를 내부에 들고 있는 client를 내어준다.
console.log(client);

client.connect({}, function () {
//            //// 3. send(path, header, message)로 메시지를 보낼 수 있다.
//            //client.send('/publish/chat/join', {}, JSON.stringify({chatRoomId: 2, writer: 3}));
//            //// 4. subscribe(path, callback)로 메시지를 받을 수 있다. callback 첫번째 파라미터의 body로 메시지의 내용이 들어온다.
//            //client.subscribe('/subscribe/chat/room/' + roomId, function (chat) {
//            //    var content = JSON.parse(chat.body);
//            //    chatBox.append('<li>' + content.message + '(' + content.writer + ')</li>')
//            //});
//            console.log("setConnect");
//        });
//        $( "#send" ).click(function () {
//            var message = messageInput.val();
//            client.send('/publish/chat/message', {}, JSON.stringify({chatRoomId: roomId, message: message, writer: member}));
//            messageInput.val('');
        });


    $("form").on('submit', (e) => e.preventDefault());
    $( "#connect" ).click(() => connect());
    $( "#disconnect" ).click(() => disconnect());
});


function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}
function disconnect() {
    stompClient.deactivate();
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.publish({
        destination: "/app/hello",
        body: JSON.stringify({'name': $("#name").val()})
    });
}

function sendChat(message) {
    $("#chatList").append("<tr><td>" + message + "</td></tr>");
}
