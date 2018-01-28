package temperature;


public class Temperature {
    private String id;

    public Temperature(String date, String value) {
        this.date = date;
        this.value = value;
    }

    private String date;
    private String value;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
