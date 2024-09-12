console.log("chat")
// 비회원제 채팅, 익명 식별이름 생성 e.g. 익명1100
// Math.random() = 0~1 사이 실수, Math.floor() = 소수점 아래 버리기 -> 1 ~ 곱한 값까지의 정수
let nickname = `익명${Math.floor(Math.random()*10000) + 1}`
// JS 클라이언트 웹소켓
let clientSocket = new WebSocket("ws://localhost:8080/chat/conn")  // ip:port/웹소켓 매핑 주소
console.log(clientSocket);

// [1] clientSocket의 onclose, onerror, onopen, onmessage 오버라이드 필요
// 1) socket.onopen: 서버 소켓과 접속 성공시 실행되는 함수를 대입
clientSocket.onopen = (e) => {
    console.log("Connected to ServerSocket!")
    clientSocket.send(JSON.stringify({type: "join", nickname: nickname, date: new Date().toLocaleString()}))
}

// 2) socket.onmessage: 서버 소켓에서 메시지를 보내올 때 실행되는 함수를 대입
clientSocket.onmessage = (e) => {
    console.log(e);
    console.log(e.data);
    let msgBox = document.querySelector("#msgBox")
    // JSON 형식의 문자열이 응답이므로 타입 변환
    let data = JSON.parse(e.data)
    if (data.type == "join"){
        // join 타입: 새 멤버 참여, 안내 메시지 출력
        msgBox.innerHTML += `<div class="info">
                                <span>[${data.date.split(" ")[3]} ${data.date.split(" ")[4]}]</span> 
                                <span>(${data.nickname}님이 참여하셨습니다.)</span>
                            </div>`
    }
    else if (data.type == "leave"){
        msgBox.innerHTML += `<div class="info">
                                <span>[${data.date.split(" ")[3]} ${data.date.split(" ")[4]}]</span> 
                                <span>(${data.nickname}님이 나가셨습니다.)</span>
                            </div>`
    }
    else if (data.nickname == nickname){
        // 자신이 보낸 메시지일 경우 날짜를 왼쪽에
        msgBox.innerHTML += `<div class="msgTo">
                                <div>${data.nickname}</div>
                                <div>
                                    <span>[${data.date.split(" ")[3]} ${data.date.split(" ")[4]}]</span> 
                                    <span>${data.message}</span>
                                </div>
                            </div>`
    } else {
        // 다른 사람의 메시지일 경우 날짜를 오른쪽에 
        msgBox.innerHTML += `<div class="msgFrom">
                                <div>${data.nickname}</div>
                                <div>
                                    <span>${data.message}</span>
                                    <span>[${data.date.split(" ")[3]} ${data.date.split(" ")[4]}]</span> 
                                </div>
                            </div>`
    }
}

// [2] 메시지 전송 이벤트
function onMsgSend(){
    // - 현재 클라이언트 소켓과 연결 유지된 서버 소켓에게 메시지 전달
    msgInput = document.querySelector("#msgInputBox")
    // 닉네임과 메시지를 포매팅해 전송
    let message = {
        'type':'msg',
        'nickname': nickname,
        'message': msgInput.value,
        'date': new Date().toLocaleString()
    }
    clientSocket.send(JSON.stringify(message))
    // [object Object] vs "{\"nickname\":\"익명1172\",\"message\":\"aaaaaaaaa\",\"date\":\"2024. 9. 12. 오후 12:17:56\"}"
    // HTTP에서는 contentType: application/json
    // @GetMapping()에서는 자동으로 JSON -> DTO
    // WebSocket에서는 JSON.stringify()로 문자열 타입인 JSON 형식으로 변환해서 전송해야 한다.
    // {key: value}: 객체 타입 JSON 형식, "{key: value}": 문자열 타입 JSON 형식
}