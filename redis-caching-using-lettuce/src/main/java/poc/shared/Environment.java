package poc.shared;

import java.net.MalformedURLException;
import java.net.URL;

public class Environment {

    private URL URL;
    private String releaseContext;
    private String environment;

    Environment( String url, String releaseContext, String environment) throws MalformedURLException{
        this.URL = new URL(url);
        this.releaseContext = releaseContext;
        this.environment = environment;
    }

    public URL getURL(){
        return this.URL;
    }

    public String getReleaseContext(){
        return this.releaseContext;

    }

    public String getEnvironment(){
        return this.environment;
    }
}