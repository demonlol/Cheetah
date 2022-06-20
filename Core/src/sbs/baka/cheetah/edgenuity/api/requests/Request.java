package sbs.baka.cheetah.edgenuity.api.requests;

import org.jsoup.*;

import java.io.*;
import java.util.*;

public class Request {

    public String url, requestBody;
    public Connection.Method method;
    public Map<String, String> headers, cookies;
    public ResponseHandler responseHandler;

    public boolean followRedirects;

    public Connection.Response response;

    public Request() {
        this.headers = new HashMap<>();
        this.cookies = new HashMap<>();
    }

    public static class Builder {

        private Request request;

        public Builder() {
            this.request = new Request();
        }

        public Builder withBody(String body) {
            this.request.requestBody = body;
            return this;
        }
        public Builder withBod(String body) {
            this.request.requestBody = body;
            return this;
        }
        public Builder withUrl(String url) {
            this.request.url = url;
            return this;
        }
        public Builder withHeader(String k, Object v) {
            if(request.headers != null && !request.headers.isEmpty())
                request.headers.put(k, String.valueOf(v));
            else {
                request.headers = new HashMap<>();
                request.headers.put(k, String.valueOf(v));
            }
            return this;
        }
        public Builder withHeaders(Map<String, String> headers) {
            if(this.request.headers != null && headers != null && !headers.isEmpty())
                this.request.headers.putAll(headers);
            else
                this.request.headers = headers;
            return this;
        }
        public Builder withCookie(String k, Object v) {
            if(request.cookies != null && !request.cookies.isEmpty())
                request.cookies.put(k, String.valueOf(v));
            else {
                request.cookies = new HashMap<String, String>();
                request.cookies.put(k, String.valueOf(v));
            }
            return this;
        }
        public Builder withCookies(Map<String, String> cookies) {
            if(cookies != null && !cookies.isEmpty())
                this.request.cookies.putAll(cookies);
            else
                this.request.cookies = cookies;
            return this;
        }
        public Builder willFollowRedirects(boolean w) {
            this.request.followRedirects = w;
            return this;
        }
        public Builder withMethod(Connection.Method method) {
            this.request.method = method;
            return this;
        }
        public Builder withResponseHandler(ResponseHandler responseHandler) {
            this.request.responseHandler = responseHandler;
            return this;
        }

        public Connection.Response execute() throws IOException {
            Connection.Response response = Jsoup.connect(request.url)
                    .method(request.method)
                    .requestBody(request.requestBody)
                    .cookies(request.cookies)
                    .headers(request.headers)
                    .followRedirects(request.followRedirects)
                    .ignoreContentType(true)
                    .execute();

            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println("REQ URL: " + request.url);
            System.out.println("REQ METHOD: " + request.method.name());
            if(request.requestBody != null && !request.requestBody.isEmpty()) {
                System.out.println("REQ BODY\n: " + request.requestBody.replaceAll("\n", ""));
            }
            if(request.headers != null && !request.headers.isEmpty()) {
                System.out.println("REQ HEADERS: ");
                for (Map.Entry<String, String> entry : request.headers.entrySet()) {
                    System.out.println(entry.getKey() + " : " + entry.getValue());
                }
            }
            if(request.cookies != null && !request.cookies.isEmpty()) {
                System.out.println("REQ COOKIES: ");
                for (Map.Entry<String, String> entry : request.cookies.entrySet()) {
                    System.out.println(entry.getKey() + " : " + entry.getValue());
                }
            }

            System.out.println("---------                                                                   ---------");

            System.out.println("STATUS: " + response.statusCode() + " / " + response.statusMessage());
            System.out.println("RES URL: " + response.url());
            String body = response.body();

            if(!body.trim().isEmpty()) {
                System.out.println("RES BODY\n: " + body.replaceAll("\n", ""));
            }
            if(!response.headers().isEmpty()) {
                System.out.println("RES HEADERS: ");
                for (Map.Entry<String, String> entry : response.headers().entrySet()) {
                    System.out.println(entry.getKey() + " : " + entry.getValue());
                }
            }
            if(!response.cookies().isEmpty()) {
                System.out.println("RES COOKIES: ");
                for (Map.Entry<String, String> entry : response.cookies().entrySet()) {
                    System.out.println(entry.getKey() + " : " + entry.getValue());
                }
            }
            System.out.println("-------------------------------------------------------------------------------------");

            for (int i = 0; i < 3; i++) System.out.println();

            return response;
        }
        public Connection.Response executeAndPrint() throws IOException {
            Connection.Response response = Jsoup.connect(request.url)
                    .method(request.method)
                    .requestBody(request.requestBody)
                    .cookies(request.cookies)
                    .headers(request.headers)
                    .followRedirects(request.followRedirects)
                    .ignoreContentType(true)
                    .execute();

            return response;
        }
    }

}
