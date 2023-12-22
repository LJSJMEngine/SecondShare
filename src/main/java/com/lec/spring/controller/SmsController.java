import com.lec.spring.DTO.UserDto;
import com.lec.spring.domain.User;
import com.lec.spring.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SmsController {

    private final MessageService messageService;

    public SmsController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/user/register")
    public ResponseEntity<?> sendSms(@RequestBody UserDto.SmsCertificationDto smsCertificationDto) {
        try {
            messageService.sendSMS(smsCertificationDto.getPhoneNumber());
            return ResponseEntity.ok("SMS sent successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending SMS");
        }
    }


}
