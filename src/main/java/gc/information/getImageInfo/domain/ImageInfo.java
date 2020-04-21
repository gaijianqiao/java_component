package gc.information.getImageInfo.domain;

public class ImageInfo {
    private String oid;
    private Metadata metadata;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public String toString() {
        return "ImageInfo{" +
                "oid='" + oid + '\'' +
                ", metadata=" + metadata +
                '}';
    }
}
