package testTask.data;


public class LinkData {
    private String linkLabel;
    private String linkUrl;
    private Boolean webUrl;

    public LinkData(){

    }

    public LinkData(String linkLabel, String linkUrl, boolean webUrl){
        setLinkLabel(linkLabel);
        setLinkUrl(linkUrl);
        setWebUrl(webUrl);
    }

    public void setLinkLabel(String linkLabel) {
        this.linkLabel = linkLabel;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public void setWebUrl(Boolean webUrl) {
        this.webUrl = webUrl;
    }

    public Boolean isWebUrl() {
        return webUrl;
    }

    public String getLinkLabel() {
        return linkLabel;
    }

    public String getLinkUrl() {
        return linkUrl;
    }
}
