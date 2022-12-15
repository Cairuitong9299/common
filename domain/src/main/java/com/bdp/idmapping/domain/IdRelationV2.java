package com.bdp.idmapping.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @Auther: CAI
 * @Date: 2022/11/5 - 11 - 05 - 16:16
 * @Description: com.bdp.idmapping.domain
 * @version: 1.0
 */
public class IdRelationV2 implements Serializable {
    private String hid;
    private String imei;
    private String imei2;
    private String udid;
    private String vaid;
    private String oaid;
    private String cid;
    private String uuid;
    private String gaid;
    private String pcbNumber;
    private String ssoid;
    private String iccid;
    private String meid;
    private String androidId;
    private String buuid;
    private long buuidLastLoginAt;
    private String finger;
    private int platform;
    private Date create;
    private Date update;
    private Set<String> duids;
    private String imeiMd5;
    private String guidMd5;

    public IdRelationV2() {
    }

    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getImei2() {
        return imei2;
    }

    public void setImei2(String imei2) {
        this.imei2 = imei2;
    }

    public String getUdid() {
        return udid;
    }

    public void setUdid(String udid) {
        this.udid = udid;
    }

    public String getVaid() {
        return vaid;
    }

    public void setVaid(String vaid) {
        this.vaid = vaid;
    }

    public String getOaid() {
        return oaid;
    }

    public void setOaid(String oaid) {
        this.oaid = oaid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getGaid() {
        return gaid;
    }

    public void setGaid(String gaid) {
        this.gaid = gaid;
    }

    public String getPcbNumber() {
        return pcbNumber;
    }

    public void setPcbNumber(String pcbNumber) {
        this.pcbNumber = pcbNumber;
    }

    public String getSsoid() {
        return ssoid;
    }

    public void setSsoid(String ssoid) {
        this.ssoid = ssoid;
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public String getMeid() {
        return meid;
    }

    public void setMeid(String meid) {
        this.meid = meid;
    }

    public String getAndroidId() {
        return androidId;
    }

    public void setAndroidId(String androidId) {
        this.androidId = androidId;
    }

    public String getBuuid() {
        return buuid;
    }

    public void setBuuid(String buuid) {
        this.buuid = buuid;
    }

    public long getBuuidLastLoginAt() {
        return buuidLastLoginAt;
    }

    public void setBuuidLastLoginAt(long buuidLastLoginAt) {
        this.buuidLastLoginAt = buuidLastLoginAt;
    }

    public String getFinger() {
        return finger;
    }

    public void setFinger(String finger) {
        this.finger = finger;
    }

    public int getPlatform() {
        return platform;
    }

    public void setPlatform(int platform) {
        this.platform = platform;
    }

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }

    public Date getUpdate() {
        return update;
    }

    public void setUpdate(Date update) {
        this.update = update;
    }

    public Set<String> getDuids() {
        return duids;
    }

    public void setDuids(Set<String> duids) {
        this.duids = duids;
    }

    public String getImeiMd5() {
        return imeiMd5;
    }

    public void setImeiMd5(String imeiMd5) {
        this.imeiMd5 = imeiMd5;
    }

    public String getGuidMd5() {
        return guidMd5;
    }

    public void setGuidMd5(String guidMd5) {
        this.guidMd5 = guidMd5;
    }

    @Override
    public String toString() {
        return "IdRelationV2{" +
                "hid='" + hid + '\'' +
                ", imei='" + imei + '\'' +
                ", imei2='" + imei2 + '\'' +
                ", udid='" + udid + '\'' +
                ", vaid='" + vaid + '\'' +
                ", oaid='" + oaid + '\'' +
                ", cid='" + cid + '\'' +
                ", uuid='" + uuid + '\'' +
                ", gaid='" + gaid + '\'' +
                ", pcbNumber='" + pcbNumber + '\'' +
                ", ssoid='" + ssoid + '\'' +
                ", iccid='" + iccid + '\'' +
                ", meid='" + meid + '\'' +
                ", androidId='" + androidId + '\'' +
                ", buuid='" + buuid + '\'' +
                ", buuidLastLoginAt=" + buuidLastLoginAt +
                ", finger='" + finger + '\'' +
                ", platform=" + platform +
                ", create=" + create +
                ", update=" + update +
                ", duids=" + duids +
                ", imeiMd5='" + imeiMd5 + '\'' +
                ", guidMd5='" + guidMd5 + '\'' +
                '}';
    }
}
