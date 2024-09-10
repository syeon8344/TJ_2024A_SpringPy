package web.chat;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;
import java.util.Vector;

@Component  // 해당 클래스를 스프링 컨테이너 빈에 등록
public class ChatController extends TextWebSocketHandler {

    // - 클라이언소켓들의 접속명단을 저장하는 컬렉션 프레임워크 // ArrayList(비동기)  vs Vector(동기)
    private List< WebSocketSession > currentClientSocketList = new Vector<>();

    // 1. 클라이언트가 서버 웹소켓에 접속 요청했을때
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("session = " + session);
        System.out.println("[서버웹소켓 측] JS 웹소켓이 들어옴 enter");
        // - 접속된 클라이언트소켓을 접속명단에 저장
        currentClientSocketList.add(session);
        // - 현재 접속된 인원수
        System.out.println("서버소켓의 접속 인원 : " + currentClientSocketList.size());
    }

    // 2. 클라이언트가 서버 웹소켓에 접속을 끊으면
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("session = " + session);
        System.out.println("[서버웹소켓 측] JS 웹소켓이 나감 exit");
        // - 접속된 클라이언소켓을 접속명단에서 제외
        currentClientSocketList.remove(session);
        // - 현재 접속된 인원수
        System.out.println("서버소켓의 접속 인원 : " + currentClientSocketList.size());
    }
}
