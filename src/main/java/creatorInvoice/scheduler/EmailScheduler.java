package creatorInvoice.scheduler;

import creatorInvoice.config.AdminConfig;
import creatorInvoice.mail.Mail;
import creatorInvoice.mail.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {

    @Autowired
    private AdminConfig adminConfig;
    @Autowired
    private SimpleEmailService simpleEmailService;

    private static final String SUBJECT = "Daily reminder";

    @Scheduled(cron = "0 0 10 * * *")
    //@Scheduled(fixedDelay = 10000)
    public void sendInformationEmail(){
        String setText = "UWAGA!!! Przypominam o wysłaniu codziennych płatności do zdefiniowanych kontrahentów. ";
        simpleEmailService.send(new Mail(
                adminConfig.getAdminMail(),
                SUBJECT,
                setText)
        );
    }

}

