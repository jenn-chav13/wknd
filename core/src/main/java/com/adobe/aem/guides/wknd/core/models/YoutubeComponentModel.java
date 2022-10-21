package com.adobe.aem.guides.wknd.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = SlingHttpServletRequest.class)
public class YoutubeComponentModel {

    @ValueMapValue
    private String videoId;

    public String getVideoId() {
        return "mi-modelo " + videoId;
    }
    
}
