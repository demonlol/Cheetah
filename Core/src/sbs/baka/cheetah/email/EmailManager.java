package sbs.baka.cheetah.email;
import java.util.Map;
import java.util.Properties;
import javax.mail.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sun.mail.imap.IMAPFolder;
import javax.mail.Message;

/*
todo >> email client which we actually like?,
  - group together senders and change visibility to archive or similar
    - enable blocking from all messages except from X and Y
    - show ______ when there's unread mail since last checked
    - show ______ when there's unread mail we don't care about
    ______________________________________________________________________________________________________________________________________________

 todo >> [CONTACTS]:
      - set colors for contacts
      - add profile pictures for them
      - create notes and add flags/tags to them like (MALE, FEMALE, P.H. : {TIME_SINCE_LAST_POSTED}, ACCEPTS LATE WORK, DECLINES LATE WORK, IN-PERSON CLASS,
      - for ex: just right-click on the contacts button, a menu like copy, paste, etc pops up, and click the "Email {name}:{email}" selection
        - a similar editor to the custom texteditor pops up.
        - allows you send an email right there (including with single/multiple contacts/and or recipients, titles, bold, italic, underline, imageS, etc)
 todo >> [FLAGS CREATE]:
  - store emails on via HARDFILE. Enable sort and filter (name, email, title, text, folder, known contact, flags, class): through our emails ideally
      - GOAL: get a very slick and fun to use design
  - EMAILS: nothing ever gets deleted
  - FOLDERS FOR EMAILS W/W/O TAGS: make folders for example (English, History, Art, Math)
 */
/*
        Accounts
        Add New
        Allow Notifications
        Contacts
            Contact Object: Email(string)*, Name(string), Importance({NONE = 1, LOW = 1, HIGH = 1, SEVERE = 1}), Color
            (rgb)
            Sync Contacts
                Canvas...
                    Course...
                        Teachers
                        Students
            View Contacts
                Create Contact
                Edit Contacts[i]
            Get All Emails From Contact
        Cache
            Clear Cache
        [allow multiple email accounts to exist. allow all inboxes at once, or just one]
    Email Object
        Contact(class)
        Subject(string)
        Body(string)
        Sent(long)
    EmailListener
        onNewEmail(Contact, EmailObj)
        onNewReply(Contact, EmailObj)
 */
/*
SimpleEmail - This class is used to send basic text based emails.
MultiPartEmail - This class is used to send multipart messages. This allows a text message with attachments either inline or attached.
HtmlEmail - This class is used to send HTML formatted emails. It has all of the capabilities as MultiPartEmail allowing attachments to be easily added. It also supports embedded images.
ImageHtmlEmail - This class is used to send HTML formatted emails with inline images. It has all of the capabilities as HtmlEmail but transform all image references to inline images.
EmailAttachment - This is a simple container class to allow for easy handling of attachments. It is for use with instances of MultiPartEmail and HtmlEmail.
 */

public class EmailManager {

    public static void main(String[] args) {
        new EmailManager().readInboundEmails("", (short) 443, "", "");
    }

    private static final Logger logger = LoggerFactory.getLogger(EmailManager.class);

    public void readInboundEmails(String hostName, short port, String user, String pass) {
        //create session object
        Session session = this.getImapSession(hostName, port);

        try {
            //connect to message store
            Store store = session.getStore("imap");
            store.connect(hostName, port, user, pass);

            //open the inbox folder
            IMAPFolder inbox = (IMAPFolder)store.getFolder("INBOX");
            inbox.open(Folder.READ_WRITE);

            //fetch messages
            Message[] messages = inbox.getMessages();

            //read messages
            for(int i = 0; i < messages.length; i++) {
                Message msg = messages[i];
                Address[] fromAddress = msg.getFrom();
                String from = fromAddress[0].toString();
                String subject = msg.getSubject();
                Address[] toList = msg.getRecipients(Message.RecipientType.TO);
                Address[] ccList = msg.getRecipients(Message.RecipientType.CC);
                String contentType = msg.getContentType();

                System.out.println(from + " ... " + contentType);
            }
        } catch (AuthenticationFailedException e) {
            logger.error("Exception in reading EMails : " + e.getMessage());
        } catch (MessagingException e) {
            logger.error("Exception in reading EMails : " + e.getMessage());
        } catch (Exception e) {
            logger.error("Exception in reading EMails : " + e.getMessage());
        }
    }
    private Session getImapSession(String hostName, short port) {
        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imap");
        props.setProperty("mail.debug", "true");
        props.setProperty("mail.imap.host", hostName);
        props.setProperty("mail.imap.port", String.valueOf(port));
        props.setProperty("mail.imap.ssl.enable", "true");
        Session session = Session.getDefaultInstance(props, null);
        session.setDebug(true);
        return session;
    }
}