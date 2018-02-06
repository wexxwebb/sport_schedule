package db.xml.xmlWrapper;

import db.entities._inter.AdminData;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AdminDataTable {

    @XmlElement(name = "AdminData")
    private List<AdminData> adminDataList;

    public AdminDataTable() {

    }

    public AdminDataTable(List<AdminData> adminDataList) {
        this.adminDataList = adminDataList;
    }

    public List<AdminData> getAdminDataList() {
        return adminDataList;
    }

    public void setAdminDataList(List<AdminData> adminDataList) {
        this.adminDataList = adminDataList;
    }
}
