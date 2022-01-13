package controllers;

import models.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import service.CustomerService;

import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.ArrayList;


@Controller
public class HomeController {
    CustomerService customerService = new CustomerService();

    @GetMapping("/customer")
    public ModelAndView show(){
        ModelAndView modelAndView = new ModelAndView("show");
        modelAndView.addObject("customers", customerService.list);
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView showCreate(){
        ModelAndView modelAndView = new ModelAndView("create");
        Customer customer = new Customer();
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }
    @PostMapping ("/create")
    public ModelAndView create(@ModelAttribute Customer customer, @RequestParam MultipartFile upImg){
        String fileName = upImg.getOriginalFilename();
        try {
            FileCopyUtils.copy(upImg.getBytes(), new File("E:\\Tu\\modul4\\Demo_Thymeleaf_MD4-master\\Demo_Thymeleaf_MD4-master\\src\\main\\webapp\\WEB-INF\\file\\" + fileName));
            String urlImg = "/i/"+ fileName;
            customer.setImg(urlImg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("redirect:/customer");
        return modelAndView;
    }

    @GetMapping("/delete")
    public ModelAndView deleteCustomer(@RequestParam int id){
        int index = customerService.findById(id);
        Customer customer = customerService.list.get(index);
        String filedelete = customer.getImg().replaceAll("/i/","");
        String file1 = "E:\\Tu\\modul4\\Demo_Thymeleaf_MD4-master\\Demo_Thymeleaf_MD4-master\\src\\main\\webapp\\WEB-INF\\file\\" +filedelete;
        System.out.println("namefile" + file1);
            File file = new File(file1);
            if(file.exists()){
                file.delete();
            }
            customerService.delete(index);
        ModelAndView modelAndView = new ModelAndView("redirect:/customer");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditCustomer(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("customer", customerService.list.get(customerService.findById(id)));
        return modelAndView;
    }

    @PostMapping ("/edit")
    public ModelAndView editCustomer(@ModelAttribute Customer customer, @RequestParam MultipartFile upImg, @RequestParam String img){
        String fileName = upImg.getOriginalFilename();
        if (upImg != null) {
            try {
                FileCopyUtils.copy(upImg.getBytes(), new File("E:\\Tu\\modul4\\Demo_Thymeleaf_MD4-master\\Demo_Thymeleaf_MD4-master\\src\\main\\webapp\\WEB-INF\\file\\" + fileName));
                String urlImg = "/i/"+ fileName;
                customer.setImg(urlImg);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else {
            customer.setImg(img);
        }


        int index= (customerService.findById(customer.getId())) ;
        customerService.edit(index, customer);
        ModelAndView modelAndView = new ModelAndView("redirect:/customer");
        return modelAndView;
    }

    @PostMapping("/uppFile")
        public String uppFile(@RequestParam MultipartFile upImg) {
        String fileName = upImg.getOriginalFilename();
        try {
            FileCopyUtils.copy(upImg.getBytes(), new File("E:\\Tu\\modul4\\Demo_Thymeleaf_MD4-master\\Demo_Thymeleaf_MD4-master\\src\\main\\webapp\\WEB-INF\\file\\" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/customer";
    }

}
