package fr.esgi.pa.editing_together_api.config.sockets;

import lombok.Data;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;


@Data
class msgObject {
    String msg;
    Integer projectId;
}

@Controller
public class TestClass {

//    @MessageMapping("/{id}")
//    @SendTo("/listener/projects/{id}")
//    public String chat(@DestinationVariable Integer id, msgObject msg) {
//        System.out.println("SALUT MEC : " + msg.projectId);
//        return msg.msg;
//    }
}
