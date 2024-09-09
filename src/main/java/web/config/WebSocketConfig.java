package web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import web.chat.ChatController;

@Configuration  // 해당 클래스를 스프링 컨테이너 빈에 등록
@EnableWebSocket // WebSocketConfig를 스프링 WebSocket 설정으로 등록
public class WebSocketConfig implements WebSocketConfigurer  {

    @Autowired private ChatController chatController;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // registry.addHandler(컨트롤러객체, "웹소켓 매핑주소");
        registry.addHandler(chatController, "/chat/conn");
    }
}
