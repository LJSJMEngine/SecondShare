let stompClient;
let roomId = RoomData.room_id;
let isInitFlag = false;
let chatArrayLog = new Array();
console.log(PostData.user);
$(function () {
    stompClient = new StompJs.Client({
        brokerURL: 'ws://' + location.host + '/ws-stomp'
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

        publishMessage("INIT", "/pub/init", "getMessageList", currentUser.id);


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
            if (!event.shiftKey){
                event.preventDefault();
                messageSend();
            }
        }
    });

});
function messageSend() {
        var message = $("#chatInput").val();
        publishMessage("MESSAGE" , "/pub/message", message, currentUser.id);
        $("#chatInput").val('');
}
function subChat(chat) {

    console.log(chatArrayLog);
    console.log(chat.chat_id);
    console.log(chatArrayLog.indexOf(chat.chat_id));
    if(chatArrayLog.indexOf(chat.chat_id) != -1)
    {
        return;
    }

    Time = calculateLaterDate(chat.createDate);

    let Message;
    let myMessageSet = [" text-end"," float-right","my-message"];
    let otherSet = ["","","other-message"];
    let isCurrentUserSub = chat.sender_id == currentUser.id;
    let setType = isCurrentUserSub ? myMessageSet : otherSet;
    console.log(otherSet);
    console.log(setType);

    console.log(chat.content.replaceAll("\n","<br>"));

    var currentContent = chat.content.replaceAll("\n","<br>").replaceAll(" ","&nbsp;");
   //var currentContent = chat.content.split('\n').map(line => {
   //return (<span>{line}<br/></span);
   //});

    MyMessage =
    `<li class="clearfix">
        <div class="message-data ` + setType[0] + `">
            <span class="message-data-time">`+ Time + `</span>
        </div>
        <div class="message ` + setType[2] + setType[1] + `"> ` + currentContent + `</div>
    </li>`;

    chatArrayLog.push(chat.chat_id);
    $("#NewChatList").append(MyMessage);

  let chatUl = document.querySelector('.chat-history');
  chatUl.scrollTop = chatUl.scrollHeight;
}
function publishMessage(TYPE , dest , message, senderId) {
        var JsonBody = JSON.stringify({room_id: roomId, content: message, sender_id: senderId})

        if(TYPE == "MESSAGE") { // 현재 방의 최근 상태 변경.
            stompClient.publish({
                destination: "/pub/roomUpdateDate",
                body: JsonBody
            });
        }
        console.log("[PUBLISH] : dest : " + dest + " Message : " + message + " senderId :" + senderId);
        stompClient.publish({ // 메세지 보내기.
            destination: dest,
            body: JsonBody
        });
}
function calculateLaterDate(subDate){
    var dateValue = new Date(subDate);
    var today = new Date();
    console.log(dateValue);
    var day = today - dateValue;
    var hour = Math.ceil(day / (1000 * 60 * 60)) - 1;
    day = Math.ceil(day / (1000 * 60 * 60 * 24)) - 1;
    var calcDate = new Date(today - dateValue);
    console.log(day);
    if(hour < 0.5)
        day = "방금";
    else if(hour < 24)
        day = hour +"시간 전";
    else if(day > 30)
        day = dateValue.getFullYear() + ". " (dateValue.getMonth() + 1) + ". " + dateValue.getDate();
    else if(day == 0)
        day = "오늘";
    else if(day == 1)
        day = "어제";
    else
        day = day + "일전";


    var hourStr = dateValue.getHours() / 10 > 1 ? dateValue.getHours() : "0" + dateValue.getHours();
    var minStr = dateValue.getMinutes() / 10 > 1 ? dateValue.getMinutes() : "0" + dateValue.getMinutes();

    return Time = day + " " + hourStr + ":" + minStr;
}