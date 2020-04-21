package gc.information.getImageInfo.domain;

public class Metadata {
    private String name;
    private String fullTitle;
    private String contentType;
    private String dpiResolution;
    private String orientation;
    private String pixelHeight;
    private String pixelWidth;
    private String imageUrlHttp;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullTitle() {
        return fullTitle;
    }

    public void setFullTitle(String fullTitle) {
        this.fullTitle = fullTitle;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getDpiResolution() {
        return dpiResolution;
    }

    public void setDpiResolution(String dpiResolution) {
        this.dpiResolution = dpiResolution;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getPixelHeight() {
        return pixelHeight;
    }

    public void setPixelHeight(String pixelHeight) {
        this.pixelHeight = pixelHeight;
    }

    public String getPixelWidth() {
        return pixelWidth;
    }

    public void setPixelWidth(String pixelWidth) {
        this.pixelWidth = pixelWidth;
    }

    public String getImageUrlHttp() {
        return imageUrlHttp;
    }

    public void setImageUrlHttp(String imageUrlHttp) {
        this.imageUrlHttp = imageUrlHttp;
    }

    @Override
    public String toString() {
        return "Metadata{" +
                "name='" + name + '\'' +
                ", fullTitle='" + fullTitle + '\'' +
                ", contentType='" + contentType + '\'' +
                ", dpiResolution='" + dpiResolution + '\'' +
                ", orientation='" + orientation + '\'' +
                ", pixelHeight='" + pixelHeight + '\'' +
                ", pixelWidth='" + pixelWidth + '\'' +
                ", imageUrlHttp='" + imageUrlHttp + '\'' +
                '}';
    }
}
