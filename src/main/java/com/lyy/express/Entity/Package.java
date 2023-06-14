package com.lyy.express.Entity;


import lombok.Data;

@Data
public class Package {
    private String code;
    private String sendername;
    private String senderphone;
    private String senderaddress;
    private String recipientname;
    private String recipientphone;
    private String recipientaddress;
    private String status;
    private String node;

    public Package() {
    }

    public Package(String code, String sendername, String senderphone, String senderaddress, String recipientname, String recipientphone, String recipientaddress, String status,String node) {
        this.code = code;
        this.sendername = sendername;
        this.senderphone = senderphone;
        this.senderaddress = senderaddress;
        this.recipientname = recipientname;
        this.recipientphone = recipientphone;
        this.recipientaddress = recipientaddress;
        this.status = status;
        this.node = node;
    }
}