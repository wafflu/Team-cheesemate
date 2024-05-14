function setConnected(connected) {
    // document.getElementById('connect').disabled = connected;
    document.getElementById('disconnect').disabled = !connected;
    document.getElementById('conversationDiv').style.visibility
        = connected ? 'visible' : 'hidden';
    document.getElementById('response').innerHTML = '';
}

//채팅 연결
function connect(roomid) {
    disconnect();
    roomnum = roomid;
    console.log("roomid : "+roomid)
    let socket = new SockJS('/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        //여기서 메세지 로드시킴
        loadchatmsg();
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/messages/'+roomnum, function(messageOutput) {
            showMessageOutput(JSON.parse(messageOutput.body), true);
        });
    });
}

//채팅 연결 끊기
function disconnect() {
    if(stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
    loadChatRoom();
}

// 채팅방 리스트 불러오기
function loadChatRoom(){
    let chatlist = $("#chatlist");
    $.ajax({
        url: '/loadchatroom',
        type : 'GET',
        processData : false,
        contentType : false,
        dataType : 'json',
        success : function(result){
            let str = "";
            chatlist.children().remove();
            str+="<ul>"
            result.forEach((chat)=>{
                let crno = chat.no;
                str += '<li onclick="connect(\'' + crno + '\')"> 채팅방번호 : '+chat.no+' 닉네임 '+chat.seller_nk+'</li>';
            })
            str+="</ul>"
            chatlist.append(str);
        },
        error : function(result){
            alert("오류");
        }
    });
}

//메세지전송
function sendMessage() {
    let nick = document.getElementById('nick').value;
    let message = document.getElementById('message').value;
    saveMessagedb(roomnum, nick, message);
    stompClient.send("/app/chat/"+roomnum, {},
        JSON.stringify({'nick':nick, 'message':message}));
    document.getElementById('message').value = "";
}
//Enter
function handleKeyPress(event){
    if(event.key === "Enter"){
        sendMessage();
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


function loadsendMessage(roomnum, nick, message) {
    stompClient.send("/app/chat/"+roomnum, {},
        JSON.stringify({'nick':nick, 'message':message}));
}

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
            console.log(result)
        },
        error : function(result){
            alert("메세지 로딩 오류");
        }
    });
}

// 메세지 표기
function showMessageOutput(messageOutput, load) {
    let response = document.getElementById('response');
    let p = document.createElement('p');
    p.style.wordWrap = 'break-word';
    // 현재 사용자의 닉네임 가져오기
    let currentNick = $("#nick").val();

    let date = messageOutput.r_date;

    if(!load){
        let originalDate = messageOutput.r_date; // "2024-05-14 11:23:36"와 같은 형식의 날짜 문자열
        let formattedDate = originalDate.split(" ")[1].substring(0, 5); // "11:23"과 같은 형식의 시간 문자열 추출
        date = formattedDate;
    }

    // 닉네임과 메시지의 닉네임 비교 후 클래스 추가
    if (currentNick === messageOutput.nick) {
        p.classList.add("buyermsg");
    } else {
        p.classList.add("sellermsg");
    }
    p.appendChild(document.createTextNode(messageOutput.nick + ": "
        + messageOutput.message + " (" + date + ")"));
    response.appendChild(p);
    scrollDown();
}

// scroll 함수
function scrollDown(){
    let chatUl = document.getElementById('response');
    chatUl.scrollTop = chatUl.scrollHeight;
}