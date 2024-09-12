package web.chat;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;
import java.util.Vector;

@Component  // 해당 클래스를 스프링 컨테이너 빈에 등록
public class ChatController extends TextWebSocketHandler {

    // - 클라이언소켓들의 접속명단을 저장하는 컬렉션 프레임워크 // ArrayList(비동기)  vs Vector(동기)
    private List< WebSocketSession > currentClientSocketList = new Vector<>();

    // 1. 클라이언트가 서버 웹소켓에 접속 요청하고 연결된 후 (after established)
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("session = " + session);
        System.out.println("[서버웹소켓 측] JS 웹소켓이 들어옴 enter");
        // - 접속된 클라이언트소켓을 접속명단에 저장
        currentClientSocketList.add(session);
        // - 현재 접속된 인원수
        System.out.println("서버소켓의 접속 인원 : " + currentClientSocketList.size());
    }

    // 2. 클라이언트가 서버 웹소켓에 접속을 끊은 후 (after closed)
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("session = " + session);
        System.out.println("[서버웹소켓 측] JS 웹소켓이 나감 exit");
        // - 접속된 클라이언소켓을 접속명단에서 제외
        currentClientSocketList.remove(session);
        // - 현재 접속된 인원수
        System.out.println("서버소켓의 접속 인원 : " + currentClientSocketList.size());
        // - 다른 클라이언트 소켓들에게 나간 멤버를 알림
        for (WebSocketSession clientSession : currentClientSocketList){
            // 목록에 저장된 각 세션을 호출해서 메시지 전송
            TextMessage textMessage = new TextMessage("Hello, ClientSocket");
            handleTextMessage(null, textMessage);
        }
    }

    // 3. 클라이언트가 서버 소켓에 메시지를 보냈을 때
    // 서버가 메시지를 받을 때 이후 로직 구현
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("session = " + session + ", message = " + message);
        System.out.println(message.getPayload());  // TextMessage payload=[Hello, Ser..],
            // 메시지 내용 DB처리? -> service > dao DB처리
        // - 특정 세션에서 받은 메시지를 접속된 다른 클라이언트 소켓들에게도 전달
        for (WebSocketSession clientSession : currentClientSocketList){
            // 목록에 저장된 각 세션을 호출해서 메시지 전송
            clientSession.sendMessage(message);
        }
    }
}
