package com.ylz.seaf.core;

/**
 * Created by 86189 on 2019/5/17.
 */
public class User {
    String userName;
    String password;
    String accessChannel;
    String verifyMode;
    String hardWareSerial;
    String cardNo;
    String psamNo;
    String cardReaderCoding;
    String imagData;
    String scanCodeAuthNum;

    public String getAccessChannel() {
        return accessChannel;
    }

    public void setAccessChannel(String accessChannel) {
        this.accessChannel = accessChannel;
    }

    public String getVerifyMode() {
        return verifyMode;
    }

    public void setVerifyMode(String verifyMode) {
        this.verifyMode = verifyMode;
    }

    public String getHardWareSerial() {
        return hardWareSerial;
    }

    public void setHardWareSerial(String hardWareSerial) {
        this.hardWareSerial = hardWareSerial;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getPsamNo() {
        return psamNo;
    }

    public void setPsamNo(String psamNo) {
        this.psamNo = psamNo;
    }

    public String getCardReaderCoding() {
        return cardReaderCoding;
    }

    public void setCardReaderCoding(String cardReaderCoding) {
        this.cardReaderCoding = cardReaderCoding;
    }

    public String getImagData() {
        return imagData;
    }

    public void setImagData(String imagData) {
        this.imagData = imagData;
    }

    public String getScanCodeAuthNum() {
        return scanCodeAuthNum;
    }

    public void setScanCodeAuthNum(String scanCodeAuthNum) {
        this.scanCodeAuthNum = scanCodeAuthNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
