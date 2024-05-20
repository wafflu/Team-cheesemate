<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chat Application</title>
    <style>
        /* CSS 스타일링 */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        #messageArea {
            list-style-type: none;
            padding: 0;
            margin: 0;
            overflow-y: scroll;
            max-height: 300px;
        }
        #messageArea li {
            margin-bottom: 5px;
        }
        #messageInput {
            width: calc(100% - 70px);
            margin-right: 10px;
            padding: 5px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }
        #sendButton {
            width: 60px;
            padding: 5px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }
    </style>
</head>
<body>
<div class="container">
    <ul id="messageArea"></ul>
    <div>
        <input type="text" id="messageInput" placeholder="Type your message...">
        <button id="sendButton" onclick="sendMessage()">Send</button> <!-- 버튼에 onclick 이벤트 추가 -->
    </div>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js" integrity="sha512-iKDtgDyTHjAitUDdLljGhenhPwrbBfqTKWO1mkhSFH3A7blITC9MhYon6SjnMhp4o0rADGw9yAC6EW4t5a4K3g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.5.1/sockjs.min.js"></script>
<script>
    var stompClient = null;
    var roomId = "1";
    function connect() {
        console.log("connect");
        var socket = new SockJS('/chat');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/sendMessage/' + roomId, function (message) {
                showMessage(JSON.parse(message.body));
            });
        });
    }

    function sendMessage() {
        var messageContent = document.getElementById('messageInput').value;
        console.log("sendMessage");
        var message = {
            nick: 'user1',  // 사용자 이름 또는 ID
            content: messageContent
        };
        stompClient.send("/sendMessage/" + roomId, {}, JSON.stringify(message));
    }

    function showMessage(message) {
        console.log("showMessage");
        var messageArea = document.getElementById('messageArea');
        var messageElement = document.createElement('li');
        messageElement.appendChild(document.createTextNode(message.nick + ': ' + message.content));
        messageArea.appendChild(messageElement);
    }

    // 방에 입장할 때 연결
    connect();

</script>
</body>
</html>
