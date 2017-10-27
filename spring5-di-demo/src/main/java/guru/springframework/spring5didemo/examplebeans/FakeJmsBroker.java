//: guru.springframework.spring5didemo.examplebeans.FakeJmsBroker.java

package guru.springframework.spring5didemo.examplebeans;


public class FakeJmsBroker {

    private String username;
    private String password;
    private String url;

    public FakeJmsBroker(String username, String password, String url) {
        this.username = username;
        this.password = password;
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "FakeJmsBroker{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}///~