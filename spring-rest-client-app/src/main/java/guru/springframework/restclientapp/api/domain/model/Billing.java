//: guru.springframework.restclientapp.api.domain.model.Billing.java


package guru.springframework.restclientapp.api.domain.model;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class Billing implements Serializable {

    private final static long serialVersionUID = -6943470952206420942L;

    private Card card;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}///:~