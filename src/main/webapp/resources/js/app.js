var stompClient = null;

function connect() {
    var socket = new SockJS('/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/messages/' + roomId, function (message) {
            showMessage(JSON.parse(message.body));
        });
    });
}

function sendMessage() {
    var messageContent = document.getElementById('messageInput').value;
    var message = {
        sender: 'user1',  // 사용자 이름 또는 ID
        content: messageContent
    };
    stompClient.send("/send-message/" + roomId, {}, JSON.stringify(message));
}

function showMessage(message) {
    var messageArea = document.getElementById('messageArea');
    var messageElement = document.createElement('li');
    messageElement.appendChild(document.createTextNode(message.sender + ': ' + message.content));
    messageArea.appendChild(messageElement);
}

// 방에 입장할 때 연결
connect();
