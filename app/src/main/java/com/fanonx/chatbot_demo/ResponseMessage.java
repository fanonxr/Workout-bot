package com.fanonx.chatbot_demo;

public class ResponseMessage {
    /**
     * response message class
     * user or chatbot responds with a stirng
     * */
    private String textMessage;
    private boolean isUser;

    /**
     * Constructor,
     * need both values to create a response object
     * */
    public ResponseMessage(String textMessage, boolean isUser) {
        this.textMessage = textMessage;
        this.isUser = isUser;
    }

    /** getter for text message. */
    public String getTextMessage() {
        return textMessage;
    }

    /** setter for text message. */
    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    /** getter for is user value. */
    public boolean isUser() {
        return isUser;
    }

    /** setter for is user value. */
    public void setUser(boolean user) {
        isUser = user;
    }
}
