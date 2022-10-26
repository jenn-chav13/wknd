package com.adobe.aem.guides.wknd.core.models;

import javax.annotation.PostConstruct;

import org.apache.commons.httpclient.HttpClient;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Model(adaptables = SlingHttpServletRequest.class)
public class YoutubeComponentModel {

    @ValueMapValue
    @Optional
    private String videoId;
    private String videoTitle;
    private String videoAuthorName;
    private Logger logger = LoggerFactory.getLogger(YoutubeComponentModel.class);

    public String getVideoId() {
        return videoId;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public String getVideoAuthorName() {
        return videoAuthorName;
    }

    @PostConstruct
    public void initalize() {
        logger.info("Entra a Logger Mateo");
        getApiValues();
    }

    public void getApiValues() {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {

            String url = "https://www.youtube.com/oembed?url=http://www.youtube.com/watch?v=VIDEO_ID&format=json";
            url = url.replaceAll("VIDEO_ID", videoId);

            HttpGet request = new HttpGet(url);

            CloseableHttpResponse response = httpClient.execute(request);

            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    // return it as a String
                    String result = EntityUtils.toString(entity);
                    JsonParser parser = new JsonParser();
                    JsonElement jsonTree = parser.parse(result);
                    JsonObject jsonObject = jsonTree.getAsJsonObject();
                    videoTitle = jsonObject.get("title").toString();
                    videoAuthorName = jsonObject.get("author_name").toString();
                }

            

            } catch(Exception error) {
                logger.error("Entra al error 1", error);
            } finally {
                response.close();
            }
        } catch(Exception error) {
            logger.error("Entra al error 2", error);
        }
    }
    
}
