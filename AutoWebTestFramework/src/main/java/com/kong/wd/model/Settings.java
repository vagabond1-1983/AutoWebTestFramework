package com.kong.wd.model;

public class Settings implements IBean {
    private String browser;
    private String server;
    private Integer port;
    private String url;

    public Settings() {
        super();
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Settings [browser=" + browser + ", server=" + server
                + ", port=" + port + ", url=" + url + "]";
    }
}
