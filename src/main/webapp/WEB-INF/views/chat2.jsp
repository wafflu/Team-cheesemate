<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
</head>
<body onload="disconnect()">
<div>
    <div>
        <input type="text" id="nick" placeholder="Choose a nickname"/>
    </div>
    <br />
    <div>
        <button id="connect" onclick="connect();">Connect</button>
        <button id="disconnect" disabled="disabled" onclick="disconnect();">
            Disconnect
        </button>
    </div>
    <br />
    <div id="conversationDiv">
        <input type="text" id="message" placeholder="Write a message..."/>
        <button id="sendMessage" onclick="sendMessage();">Send</button>
        <p id="response"></p>
    </div>
</div>

<script type="text/javascript">
    var stompClient = null;

    function setConnected(connected) {
        document.getElementById('connect').disabled = connected;
        document.getElementById('disconnect').disabled = !connected;
        document.getElementById('conversationDiv').style.visibility
            = connected ? 'visible' : 'hidden';
        document.getElementById('response').innerHTML = '';
    }

    let roomid = 1;
    //채팅 연
    function connect() {
        var socket = new SockJS('/chat');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function(frame) {
            setConnected(true);
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/messages/'+roomid, function(messageOutput) {
                showMessageOutput(JSON.parse(messageOutput.body));
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
    }

    //메세지전송
    function sendMessage() {
        var nick = document.getElementById('nick').value;
        var message = document.getElementById('message').value;
        stompClient.send("/app/chat/"+roomid, {},
            JSON.stringify({'nick':nick, 'message':message}));
    }

    // 메세지 표기
    function showMessageOutput(messageOutput) {
        var response = document.getElementById('response');
        var p = document.createElement('p');
        p.style.wordWrap = 'break-word';
        p.appendChild(document.createTextNode(messageOutput.nick + ": "
            + messageOutput.message + " (" + messageOutput.r_date + ")"));
        response.appendChild(p);
    }
</script>

</body>
</html>
