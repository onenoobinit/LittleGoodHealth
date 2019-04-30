package com.youyijia.goodhealth.entity;

/**
 * @author chenliangzhi
 * @date 2018/4/10
 * @describe
 */

public class UpdateInfoBean {

    /**
     * version_code : 1.0
     * packet_name : https://m.youjuke.com/download/Supervisor.apk
     */

    private String version_code;
    private String packet_name;
    private String success;

    public String getVersion_code() {
        return version_code;
    }

    public void setVersion_code(String version_code) {
        this.version_code = version_code;
    }

    public String getPacket_name() {
        return packet_name;
    }

    public void setPacket_name(String packet_name) {
        this.packet_name = packet_name;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "UpdateInfoBean{" +
                "version_code='" + version_code + '\'' +
                ", packet_name='" + packet_name + '\'' +
                ", success='" + success + '\'' +
                '}';
    }
}
