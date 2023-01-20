package poji;

import sun.tracing.dtrace.DTraceProviderFactory;

/**
 * @ProjectName: AdrressBooks
 * @Package: dao
 * @ClassName: person
 * @Author: JN
 * @Description: 2
 * @Date: 2020/11/27 8:45
 * @Version: 1.0
 */
public class person {

    private  int id;
    private String name;
    private String phone;
    private String mobile;
    private String address;
    private String gender;
    private String groupName;


    public person( String name, String phone, String mobile, String address, String gender, String groupName) {
        this.name = name;
        this.phone = phone;
        this.mobile = mobile;
        this.address = address;
        this.gender = gender;
        this.groupName = groupName;
    }

    public person(){

    }


    @Override
    public String toString() {
        return "person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", mobile='" + mobile + '\'' +
                ", address='" + address + '\'' +
                ", gender='" + gender + '\'' +
                ", groupName='" + groupName + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
