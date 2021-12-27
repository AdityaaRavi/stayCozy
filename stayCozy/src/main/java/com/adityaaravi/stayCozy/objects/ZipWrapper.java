package com.adityaaravi.stayCozy.objects;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ZipWrapper {
    
    // status boolean
    private boolean status;

    // the actual city data
    private ZipData data;

    // create getter and setter methods for each field above
    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ZipData getData() {
        return data;
    }

    public void setData(ZipData data) {
        this.data = data;
    }
}
