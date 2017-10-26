//: guru.springframework.spring5didemo.examplebeans.YamlBean.java

package guru.springframework.spring5didemo.examplebeans;


import java.util.List;


public class YamlBean {

    private List<String> names;

    public YamlBean(List<String> names) {
        this.names = names;
    }

    public List<String> getNames() {
        return this.names;
    }

}///~