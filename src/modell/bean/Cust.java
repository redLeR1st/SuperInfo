package modell.bean;

public class Cust {

    private int licnaNo;
    private String name;
    private String address;
    private String ulica;
    private String houseNo;
    private String town;

    public Cust() {
    }

    public Cust(int licnaNo, String name, String address) {
        this.licnaNo = licnaNo;
        this.name = name;
        this.address = address;
//        this.ulica = ulica;
//        this.houseNo = houseNo;
//        this.town = town;
    }

    public int getLicnaNo() {
        return licnaNo;
    }

    public void setLicnaNo(int licnaNo) {
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
