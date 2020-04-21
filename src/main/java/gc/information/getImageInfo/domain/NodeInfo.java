package gc.information.getImageInfo.domain;

import java.util.List;

public class NodeInfo {
    private String baseProdname;
    private String prodnum;
    private String oid;
    private String level;
    private List<String> status;
    private String cultureCode;
    private List<NodeInfo> children;

    public String getBaseProdname() {
        return baseProdname;
    }

    public void setBaseProdname(String baseProdname) {
        this.baseProdname = baseProdname;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public List<String> getStatus() {
        return status;
    }

    public void setStatus(List<String> status) {
        this.status = status;
    }

    public String getCultureCode() {
        return cultureCode;
    }

    public void setCultureCode(String cultureCode) {
        this.cultureCode = cultureCode;
    }

    public List<NodeInfo> getChildren() {
        return children;
    }

    public void setChildren(List<NodeInfo> children) {
        this.children = children;
    }

    public String getProdnum() {
        return prodnum;
    }

    public void setProdnum(String prodnum) {
        this.prodnum = prodnum;
    }
}
