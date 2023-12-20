let stompClient;
let roomId = RoomData.room_id;
$(function () {
    stompClient = new StompJs.Client({
        brokerURL: 'ws://localhost:8093/ws-stomp'
    });
    stompClient.activate();
    console.log(MessageList);

    //Stomp연결
    stompClient.onConnect = (frame) => {
        stompClient.subscribe('/sub/chat/room/' + roomId, function (chat) {
                var content = JSON.parse(chat.body);
                console.log(content);
                subChat(content);
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
function subChat(chat) {

    var dateValue = new Date(chat.createDate);
    var today = new Date();
    console.log(dateValue);
    var day = today - dateValue;
    day = Math.ceil(day / (1000 * 60 * 60 * 24)) - 1;
    var calcDate = new Date(today - dateValue);
    console.log(day);
    if(day > 30)
        day = dateValue.getFullYear() + ". " (dateValue.getMonth() + 1) + ". " + dateValue.getDate();
    else if(day == 0)
        day = "오늘";
    else if(day == 1)
        day = "어제";
    else
        day = day + "일전";
    var hourStr = dateValue.getHours() / 10 > 1 ? dateValue.getHours() : "0" + dateValue.getHours();
    var minStr = dateValue.getMinutes() / 10 > 1 ? dateValue.getMinutes() : "0" + dateValue.getMinutes();

    var Time = hourStr + ":" + minStr + " " + day;
    //시 분 AM, 날짜
    let Message;
    let myMessageSet = [" text-end"," float-right"];
    let otherSet = ["",""];
    let setType = Math.random() * 2 > 1 ? myMessageSet : otherSet;
    console.log(otherSet);
    console.log(setType);
    MyMessage =
    `<li class="clearfix">
        <div class="message-data ` + setType[0] + `">
            <span class="message-data-time">`+ Time + `</span>
        </div>
        <div class="message other-message ` + setType[1] + `"> ` + chat.content + `</div>
    </li>`;

    $("#NewChatList").append(MyMessage);
    $("#chatList").append("<tr><td>" +chat.content + "</td></tr>");
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
