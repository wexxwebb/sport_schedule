package db.xml.xmlWrapper;

import db.POJO.AdminData;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class AdminDataTable {

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
