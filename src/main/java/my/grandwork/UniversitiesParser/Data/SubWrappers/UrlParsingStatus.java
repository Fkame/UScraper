package my.grandwork.UniversitiesParser.Data.SubWrappers;

import org.openqa.selenium.WebDriverException;

import my.grandwork.UniversitiesParser.Parsers.emuns.University;

public class UrlParsingStatus {
    public String url;
    public boolean isSuccess;
    public String specifyDetails;
    public Exception exception;
    public WebDriverException webDriverException;

    public UrlParsingStatus(String url, boolean isSuccess, String specifyDetails) {
        this.url = url;
        this.isSuccess = isSuccess;
        this.specifyDetails = specifyDetails;
    }

    public UrlParsingStatus() { }
}
