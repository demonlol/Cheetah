package sbs.baka.cheetah.social.media.sendit;

import org.json.*;
import sbs.baka.cheetah.feature.*;
import sbs.baka.cheetah.gui.*;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.net.http.*;
import java.util.*;

public class SenditSpamFeature extends WindowFeature {
    //https://www.twitchquotes.com/copypastas?page=4
    //

    //dezzy
    //{"recipient_identity":{"type":"id","value":"a3f764e7-edf2-42c2-b2f7-396f73f97939"},"type":"sendit.post-type:question-and-answer-v1","data":{"question":"dsdasdasd"},"ext_data":{"sticker_id":"b3bbeee2-bd46-4eb5-9cdd-95e6eed3247e","author_shadow_token":"c2ecc0d3-4264-449f-ba59-e45118c5a163"},"timer":0}


    public SenditSpamFeature() {
        String name = "Dezzy";
        Scanner scanner = new Scanner(System.in);
        String s;
        while (true) {
            while (!(s = scanner.nextLine()).isEmpty()) {
                //{"recipient_identity":{"type":"id","value":"9b60aeba-f6b7-4db4-8e57-e29418da606f"},"type":"sendit.post-type:question-and-answer-v1","data":{"question":"fasfsafdasd"},"ext_data":{"sticker_id":"c70fa3bc-5a72-4a74-85ec-38f42f1d1d47","author_shadow_token":"c2ecc0d3-4264-449f-ba59-e45118c5a163"},"timer":0}

                // boolean ashleySendit = sendit("{\"recipient_identity\":{\"type\":\"id\"," +
                //                              "\"value\":\"9b60aeba-f6b7-4db4-8e57-e29418da606f\"}," +
                //                              "\"type\":\"sendit.post-type:question-and-answer-v1\"," +
                //                              "\"data\":{\"question\":\"" + s + "\"}," +
                //                              "\"ext_data\":{\"sticker_id\":\"c70fa3bc-5a72-4a74-85ec-38f42f1d1d47\"," +
                //                              "\"author_shadow_token\":\"" + UUID.randomUUID() + "\"},\"timer\":0}");

                boolean dezzySendit = sendit(("{\"recipient_identity\":{\"type\":\"id\"," +
                        "\"value\":\"a3f764e7-edf2-42c2-b2f7-396f73f97939\"}," +
                        "\"type\":\"sendit.post-type:question-and-answer-v1\"," +
                        "\"data\":{\"question\":\"" + s + "\"}," +
                        "\"ext_data\":{\"sticker_id\":\"b3bbeee2-bd46-4eb5-9cdd-95e6eed3247e" +
                        "\",\"author_shadow_token\":\"" + UUID.randomUUID() + "\"},\"timer\":0}\n"));


                System.out.println("[" + name + ",success=" + dezzySendit + "]" + s + "\nInput: ");
            }
        }
    }

    public boolean sendit(String json) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://api.getsendit.com/v1/posts"))
                    .POST(HttpRequest.BodyPublishers.ofString(
                            json
                    ))
                    .header("accept", "*/*")
                    .header("accept-encoding", "gzip, deflate, br")
                    .header("accept-language", "en-US,en;q=0.9")
                    .header("app-id", "c2ad997f-1bf2-4f2c-b5fd-83926e8f3c65")
                    .header("app-version", "1.0")
                    .header("content-type", "application/json")
                    .header("origin", "https://web.getsendit.com")
                    .header("referer", "https://web.getsendit.com/")
                    .header("sec-ch-ua", "\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"100\", \"Google Chrome\";v=\"100\"")
                    .header("sec-ch-ua-mobile", "?0")
                    .header("sec-ch-ua-platform", "\"Windows\"")
                    .header("sec-fetch-dest", "empty")
                    .header("sec-fetch-mode", "cors")
                    .header("sec-fetch-site", "same-site")
                    .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.60 Safari/537.36")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("    - Code: " + response.statusCode());
            return response.statusCode() >= 200 && response.statusCode() <= 300;
        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    //         public static void main(String[] args) {
    //         new Main();
    //     }
    //
    //     //https://www.twitchquotes.com/copypastas?page=4
    //     //
    //
    //     //dezzy
    //     //{"recipient_identity":{"type":"id","value":"a3f764e7-edf2-42c2-b2f7-396f73f97939"},"type":"sendit.post-type:question-and-answer-v1","data":{"question":"dsdasdasd"},"ext_data":{"sticker_id":"b3bbeee2-bd46-4eb5-9cdd-95e6eed3247e","author_shadow_token":"c2ecc0d3-4264-449f-ba59-e45118c5a163"},"timer":0}
    //
    //
    //     public Main() {
    //         String name = "Dezzy";
    //         Scanner scanner = new Scanner(System.in);
    //         String s;
    //         while (true) {
    //             while (!(s = scanner.nextLine()).isEmpty()) {
    //                 //{"recipient_identity":{"type":"id","value":"9b60aeba-f6b7-4db4-8e57-e29418da606f"},"type":"sendit.post-type:question-and-answer-v1","data":{"question":"fasfsafdasd"},"ext_data":{"sticker_id":"c70fa3bc-5a72-4a74-85ec-38f42f1d1d47","author_shadow_token":"c2ecc0d3-4264-449f-ba59-e45118c5a163"},"timer":0}
    //
    //                 // boolean ashleySendit = sendit("{\"recipient_identity\":{\"type\":\"id\"," +
    //                 //                              "\"value\":\"9b60aeba-f6b7-4db4-8e57-e29418da606f\"}," +
    //                 //                              "\"type\":\"sendit.post-type:question-and-answer-v1\"," +
    //                 //                              "\"data\":{\"question\":\"" + s + "\"}," +
    //                 //                              "\"ext_data\":{\"sticker_id\":\"c70fa3bc-5a72-4a74-85ec-38f42f1d1d47\"," +
    //                 //                              "\"author_shadow_token\":\"" + UUID.randomUUID() + "\"},\"timer\":0}");
    //
    //                 boolean dezzySendit = sendit(("{\"recipient_identity\":{\"type\":\"id\"," +
    //                                                 "\"value\":\"a3f764e7-edf2-42c2-b2f7-396f73f97939\"}," +
    //                                                 "\"type\":\"sendit.post-type:question-and-answer-v1\"," +
    //                                                 "\"data\":{\"question\":\"" + s + "\"}," +
    //                                                 "\"ext_data\":{\"sticker_id\":\"b3bbeee2-bd46-4eb5-9cdd-95e6eed3247e" +
    //                                                 "\",\"author_shadow_token\":\"" + UUID.randomUUID() + "\"},\"timer\":0}\n"));
    //
    //
    //                 System.out.println("[" + name + ",success=" + dezzySendit + "]" + s + "\nInput: ");
    //             }
    //         }
    //     }
    //
    //     public boolean sendit(String json) {
    //         try {
    //             HttpClient client = HttpClient.newHttpClient();
    //             HttpRequest request = HttpRequest.newBuilder()
    //                     .uri(new URI("https://api.getsendit.com/v1/posts"))
    //                     .POST(HttpRequest.BodyPublishers.ofString(
    //                             json
    //                     ))
    // //                    .header("accept", "* / *")
    // //                    .header("accept-encoding", "gzip, deflate, br")
    // //                    .header("accept-language", "en-US,en;q=0.9")
    // //                    .header("app-id", "c2ad997f-1bf2-4f2c-b5fd-83926e8f3c65")
    // //                    .header("app-version", "1.0")
    // //                    .header("content-type", "application/json")
    // //                    .header("origin", "https://web.getsendit.com")
    // //                    .header("referer", "https://web.getsendit.com/")
    // //                    .header("sec-ch-ua", "\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"100\", \"Google Chrome\";v=\"100\"")
    // //                    .header("sec-ch-ua-mobile", "?0")
    // //                    .header("sec-ch-ua-platform", "\"Windows\"")
    // //                    .header("sec-fetch-dest", "empty")
    // //                    .header("sec-fetch-mode", "cors")
    // //                    .header("sec-fetch-site", "same-site")
    // //                    .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.60 Safari/537.36")
    // //                    .build();
    //
    //     HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    //
    //             System.out.println("    - Code: " + response.statusCode());
    //             return response.statusCode() >= 200 && response.statusCode() <= 300;
    // } catch (URISyntaxException | IOException | InterruptedException e) {
    //         e.printStackTrace();
    //         return false;
    //         }
    //         }

    @Override
    public InternalFrame.Builder getInternalFrame() {
        return new InternalFrame.Builder()
                .withTitle(getName())
                .withSize(400, 150)
                .withRunnableAndInstance((frame) -> {
                    //https://reply.getsendit.com/s/25a2a598-7d4d-4a92-960f-bc4765fa22e6?sc_ewa_page_id=0ADABB64-AC7E-4CB3-9B70-473602821495&sc_ewa=1
                    JTextField linkField = new JTextField("");
                    JPanel linkPanel = new JPanel();
                    linkPanel.setBorder(BorderFactory.createTitledBorder("Sendit Link Input"));
                    linkPanel.setPreferredSize(new Dimension(300, 20));
                    linkPanel.add(linkField);

                    JButton spamButton = new JButton("Spam!");
                    spamButton.addActionListener((a) -> {
                        String text = linkField.getText();
                        if(text.trim().isEmpty() || text.trim().isBlank()) {
                            JOptionPane.showInternalMessageDialog(frame, "Please enter a sendit link.");
                            return;
                        }
                        JSONObject jsonObject;
                        try {
                            jsonObject = new JSONObject(text);
                        } catch (JSONException exception) {
                            jsonObject = null;
                        }

                        if(text.startsWith("https://reply.getsendit.com/s/")) {
                            //parse and get correct xhr request link
                        } else if(jsonObject != null) {
                            //https://api.getsendit.com/v1/posts

//                            new Connection.Request()
//                                    .url(new URL("https://api.getsendit.com/v1/posts"))
//                                    .requestBody("")

                            //{"recipient_identity":{"type":"id","value":"ed650a54-ae50-418b-aa92-d9d941c6b765"},"type":"sendit.post-type:question-and-answer-v1","data":{"question":"dasdsad"},"ext_data":{"sticker_id":"25a2a598-7d4d-4a92-960f-bc4765fa22e6","author_shadow_token":"2e454750-4347-4c5a-bf70-a1b5f501351f","snap_reference_id":null},"timer":0}
                        } else {
                            JOptionPane.showInternalMessageDialog(frame, "Failed to identify sendit link.");
                            return;
                        }
                    });

                    frame.add(linkField);
                    frame.add(spamButton);

                    frame.setLayout(new FlowLayout());
                })
                ;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDesc() {
        return null;
    }

    @Override
    public void internalFrameOpened(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameClosing(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameClosed(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameIconified(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameDeiconified(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameActivated(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameDeactivated(InternalFrameEvent e) {

    }
}
