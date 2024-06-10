<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="fixed/header.jsp"%>
    <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
    <link rel="stylesheet" href="/css/chatting.css">
<div class="maincontent">
<div id="chatbox">

    <div id = "chatlist-box" class="chatbox-info">
        <div class="chatlist-menu">
            <ul class="chatlist-menu-nav">
                <li class="chatlist-menu-active font14">
                    <span>전체 대화</span>
                    <span class="chatsel-drop-down"></span>
                </li>
                <li>
                    <span>구매 대화</span>
                    <div class="chatsel-drop-down"></div>
                </li>
                <li>
                    <span>판매 대화</span>
                    <div class="chatsel-drop-down"></div>
                </li>
            </ul>
        </div>

        <div id="chatlist">

        </div>
    </div>

    <div id="conversationDiv" class="chatbox-info">
        <input type="hidden" id="nick" placeholder="Choose a nickname" value=<c:out value='${usernick}'/>>
        <button id="disconnect" disabled="disabled" onclick="disconnect();"></button>
        <div class="chat-profile-box">
            <div class="chat-profile-img">

            </div>
            <div class="chat-user-nick">

            </div>
        </div>
        <div id="response" class="response-active"></div>
        <div id="sendbox" disabled="disabled">
            <div class="msg-text">
                <textarea id="message" rows="6" cols="80" name="content" placeholder="메세지를 입력하세요." onkeydown="handleKeyPress(event)"></textarea>
            </div>
            <button id="sendMessage" onclick="sendMessage();"></button>
        </div>
    </div>
</div>
</div>
<script type="text/javascript">
    let stompClient = null;
    let roomnum; // 채팅방 번호
    const userid = "${sessionScope.userId}";

    $(document).ready(function (){
        let roomnum = ${roomid};
        if(roomnum !== 0){
            connect(roomnum);
        } else {
            disconnect();
        }
    })

    function setConnected(connected) {
        // document.getElementById('connect').disabled = connected;
        document.getElementById('disconnect').disabled = !connected;
        document.getElementById('sendbox').style.visibility
            = connected ? 'visible' : 'hidden';
        document.getElementById('response').innerHTML = '';
    }

    //채팅 연결
    function connect(roomid) {
        disconnect();
        roomnum = roomid;
        let socket = new SockJS('/chat');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function(frame) {
            //여기서 메세지 로드시킴
            //if(get)
            loadchatmsg();
            setConnected(true);
            $("#response").removeClass("response-active");
            $('#response').removeAttr('style');
            // console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/messages/'+roomnum, function(messageOutput) {
                showMessageOutput(JSON.parse(messageOutput.body), true);
            });
        });
    }

    // $(document).ready(function() {
    //     $(document).on('click', '.chatullist li', function() {
    //         $(".chatullist li").removeClass("active-sel-chatroom");
    //         $(this).addClass("active-sel-chatroom");
    //     });
    // });


    //채팅 연결 끊기
    function disconnect() {
        if(stompClient != null) {
            stompClient.disconnect();
        }
        setConnected(false);
        console.log("Disconnected");
        loadChatRoom();
    }

    function resizeImage(img) {
        let maxWidth = 50;
        let maxHeight = 50;
        let ratio = Math.min(maxWidth / img.width, maxHeight / img.height);
        img.width = img.width * ratio;
        img.height = img.height * ratio;
    }


    // 채팅방 리스트 불러오기
    function loadChatRoom(){
        let chatlist = $("#chatlist");
        let chatlist_box = $("#chatlist-box");
        $.ajax({
            url: '/loadchatroom',
            type : 'GET',
            processData : false,
            contentType : false,
            dataType : 'json',
            success : function(result){
                let str = "";
                chatlist.children().remove();
                str+="<ul class='chatullist'>"
                result.forEach((chat)=>{
                    if(chat.id == roomnum){
                        str += '<li onclick="connect(\'' + chat.id + '\')" class="chatroomsel active-sel-chatroom"> <img src="/img/display?fileName=' + chat.img_full_rt + '" class="r_chatprofileimg"> <span class="r_chatnick font14">' + chat.usernick + '</span></li>';
                    } else {
                        str += '<li onclick="connect(\'' + chat.id + '\')" class="chatroomsel"> <img src="/img/display?fileName=' + chat.img_full_rt + '" class="r_chatprofileimg"> <span class="r_chatnick font14">' + chat.usernick + '</span></li>';
                    }

                })
                str+="</ul>"
                chatlist.append(str);

                if (chatlist.children().length === 0) {
                    chatlist_box.addClass("chatlist-box");
                } else {
                    $("#chatlist-box").css("background-image", "none");
                }
            },
            error : function(result){
                alert("오류");
            }
        })
    }

    //메세지전송
    function sendMessage() {
        if($("#message").val().length === 0){
            $('#message').val('');
            return;
        }
        let nick = document.getElementById('nick').value;
        let message = document.getElementById('message').value;
        saveMessagedb(roomnum, nick, message);
        stompClient.send("/app/chat/"+roomnum, {},
            JSON.stringify({'nick':nick, 'message':message, 'acid':userid}));
        $('#message').val('');
        // document.getElementById('message').value = "";
    }

    //Enter
    function handleKeyPress(event){
        if(event.keyCode === 13){
            sendMessage();
            event.preventDefault();
            return false;
        }
    }

    function saveMessagedb(roomnum, nick, message){
        let chatmsg = {
          "cr_no" : roomnum,
          "nick" : nick,
          "message" : message
        }

        $.ajax({
            url: '/savemessage',
            type : 'POST',
            processData : false,
            contentType : 'application/json; charset=UTF-8',
            data : JSON.stringify(chatmsg),
            dataType : 'text',
            error : function(result){
                alert("메세지 전송 오류");
            }
        });
    }

    //
    // function loadsendMessage(roomnum, nick, message) {
    //     stompClient.send("/app/chat/"+roomnum, {},
    //         JSON.stringify({'nick':nick, 'message':message, 'acid':userid}));
    // }

    function loadchatmsg(){
        $.ajax({
            url: '/loadchatmsg?roomnum='+roomnum,
            type : 'GET',
            processData : false,
            contentType : false,
            dataType : 'json',
            success : function(result){
                if(result[0] == null){
                    return;
                }
                result.forEach((chat)=>{
                    showMessageOutput(chat, false);
                });
            },
            error : function(result){
                // 이거 나중에 봐야함
                // 채팅로그가 없으면그냥 오류뜸
                // alert("메세지 로딩 오류");
            }
        });
    }

    // 메세지 표기
    function showMessageOutput(messageOutput, load) {
        let res = $("#response");

        // 현재 사용자의 닉네임 가져오기
        let currentNick = $("#nick").val();

        let date = messageOutput.r_date;

        if(!load){
            let originalDate = messageOutput.r_date; // "2024-05-14 11:23:36"와 같은 형식의 날짜 문자열
            let formattedDate = originalDate.split(" ")[1].substring(0, 5); // "11:23"과 같은 형식의 시간 문자열 추출
            date = formattedDate;
        }

        // 닉네임과 메시지의 닉네임 비교 후 클래스 추가
        // let srcValue = $(".r_chatprofileimg").attr("src");
        // console.log(messageOutput.img_full_rt)
        let str="";
        str += "<div class='msg-box'>"
        const usernick = "${sessionScope.userNick}";
        if(messageOutput.nick !== usernick){
            str += "<div class='msg-nick-box'>"
            str += '<img src="/img/display?fileName=' + messageOutput.img_full_rt + '" class="c_chatprofileimg">';
            str += "<span class='msg_nick'>"+messageOutput.nick+"</span>";
            str += "</div>"
        }
        str += "<div class='msg-msg-box'>"
        str += "<div class='msg_msg'>" + messageOutput.message + "</div>";
        str += "<div class='msg_date'>" + date + "</div>";
        str += "</div>"
        str += "</div>"

        res.append(str);

        let num = $("#response").children().length-1;
        // console.log(num);
        if (currentNick === messageOutput.nick) {
            $(".msg-box").eq(num).addClass("buyermsg")
            $(".msg-msg-box").eq(num).addClass("buyer-msg")
            $(".msg_date").eq(num).addClass("buyer-msg-date")
        } else {
            $(".msg-box").eq(num).addClass("sellermsg")
            $(".msg-msg-box").eq(num).addClass("seller-msg")
            $(".msg_date").eq(num).addClass("seller-msg-date")
        }

        scrollDown();
    }

    // scroll 함수
    function scrollDown(){
        let chatUl = document.getElementById('response');
        chatUl.scrollTop = chatUl.scrollHeight;
    }
</script>

<%@include file="fixed/footer.jsp"%>
