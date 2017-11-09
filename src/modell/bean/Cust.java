package modell.bean;

public class Cust {

    private String licnaNo;
    private String name;
    private String address;
    private String ulica;
    private String houseNo;
    private String town;

    public Cust(String licnaNo, String name, String address) {
        this.licnaNo = licnaNo;
        this.name = name;
        this.address = address;
//        this.ulica = ulica;
//        this.houseNo = houseNo;
//        this.town = town;
    }

    public String getLicnaNo() {
        return licnaNo;
    }

    public void setLicnaNo(String licnaNo) {
        this.licnaNo = licnaNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
//
//    @Override
//    public String toString() {
//        return "Cust{" +
//                "licnaNo=" + licnaNo +
//                ", name='" + name + '\'' +
//                ", ulica='" + ulica + '\'' +
//                ", houseNo='" + houseNo + '\'' +
//                ", town='" + town + '\'' +
//                '}';
//    }

    @Override
    public String toString() {
        return "Cust{" +
                "licnaNo=" + licnaNo +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
