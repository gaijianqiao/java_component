package gc.information.getImageInfo.domain;

public class SearchNode {
    private String oid;
    private String catalog;

    public SearchNode(){}

    public SearchNode(String oid, String catalog) {
        this.oid = oid;
        this.catalog = catalog;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }
}
