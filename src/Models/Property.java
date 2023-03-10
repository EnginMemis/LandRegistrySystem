package Models;

public class Property {
    private Integer id;
    private String address;
    private String type;
    private Integer value;
    private double area;

    public Property(Integer id, String address, String type, Integer value, double area) {
        this.id = id;
        this.address = address;
        this.type = type;
        this.value = value;
        this.area = area;
    }


    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }
}
