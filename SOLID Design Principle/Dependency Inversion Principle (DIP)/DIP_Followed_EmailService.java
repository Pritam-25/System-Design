interface EmailClient {
    void sendEmail(String recipient, String message);
}

class GmailClient implements EmailClient {
    @Override
    public void sendEmail(String recipient, String message) {
        System.out.println("Sending email via Gmail to " + recipient);
        System.out.println("message: " + message);
    }
}

class OutlookClient implements EmailClient {

    @Override
    public void sendEmail(String recipient, String message) {
        System.out.println("Sending email via Outlook to " + recipient);
        System.out.println("message: " + message);
    }
}

class EmailService {
    private EmailClient emailClient;

    EmailService(EmailClient emailClient) {
        this.emailClient = emailClient;
    }

    void send(String recipient, String message) {
        emailClient.sendEmail(recipient, message);
    }
}

public class DIP_Followed_EmailService {
    public static void main(String[] args) {
        EmailService gEmailService = new EmailService(new GmailClient());
        gEmailService.send("pritam", "hi from pritam via gmail");
        System.out.println("-------------------------------------------");

        EmailClient outlookClient = new OutlookClient();
        EmailService outlookService = new EmailService(outlookClient);
        outlookService.send("koushik", "hi from koushik via outlook");
    }
}