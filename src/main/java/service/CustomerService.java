package service;

import models.Customer;

import java.util.ArrayList;

public class CustomerService {
    public ArrayList<Customer> list = new ArrayList<>();

    public CustomerService() {
        list.add(new Customer(1,"toan","https://photographer.vn/wp-content/uploads/2016/06/nhung-bi-quyet-de-co-nhung-buc-anh-dep5.jpg"));
        list.add(new Customer(2,"nam","https://cdn.nguyenkimmall.com/images/companies/_1/tin-tuc/kinh-nghiem-meo-hay/C%C3%B4ng%20ngh%E1%BB%87/9-cach-chup-anh-dep.jpeg.jpg"));
    }

    public ArrayList<Customer> getList() {
        return list;
    }

    public void setList(ArrayList<Customer> list) {
        this.list = list;
    }

    public void save(Customer customer){
        list.add(customer);
    }

    public void edit(int index, Customer customer){
        list.set(index,customer);
    }

    public void delete(int index){
        list.remove(index);
    }

    public int findById(int id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }
}
