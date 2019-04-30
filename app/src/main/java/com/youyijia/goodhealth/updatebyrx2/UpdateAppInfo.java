package com.youyijia.goodhealth.updatebyrx2;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 描述: 检测app更新Bean
 * --------------------------------------------
 * 工程:
 * #0000     mwy     创建日期: 2016-09-29 15:14
 */

public class UpdateAppInfo implements Parcelable {

    /**
     * version_code : 1
     * version_name : 1.0
     * packet_name : http://prebk.youjuke.com/download/app-release_v1.0.apk
     * update_info : 1.上线版本v1.0
     */

    //private String version_code;//版本号
    private String version_name;//版本名称
    private String packet_name;//下载链接
    //private String update_info;//更新信息
    private String file_name;//文件名字

    public String getVersion_name() {
        return version_name;
    }

    public void setVersion_name(String version_name) {
        this.version_name = version_name;
    }

    public String getPacket_name() {
        return packet_name;
    }

    public void setPacket_name(String packet_name) {
        this.packet_name = packet_name;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.version_name);
        dest.writeString(this.packet_name);
        dest.writeString(this.file_name);
    }

    public UpdateAppInfo() {
    }

    protected UpdateAppInfo(Parcel in) {
        this.version_name = in.readString();
        this.packet_name = in.readString();
        this.file_name = in.readString();
    }

    public static final Creator<UpdateAppInfo> CREATOR = new Creator<UpdateAppInfo>() {
        @Override
        public UpdateAppInfo createFromParcel(Parcel source) {
            return new UpdateAppInfo(source);
        }

        @Override
        public UpdateAppInfo[] newArray(int size) {
            return new UpdateAppInfo[size];
        }
    };
}
