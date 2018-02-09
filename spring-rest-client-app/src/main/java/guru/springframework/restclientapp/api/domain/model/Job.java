//: guru.springframework.restclientapp.api.domain.model.Job.java



package guru.springframework.restclientapp.api.domain.model;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class Job implements Serializable {

    private final static long serialVersionUID = -6095971880029680368L;

    private String title;
    private String company;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}///:~