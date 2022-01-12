package controllers;

import models.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import service.CustomerService;

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
        return modelAndView;
    }
    @PostMapping ("/create")
    public ModelAndView create(@ModelAttribute Customer customer){
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("redirect:/customer");
        return modelAndView;
    }

    @GetMapping("/delete")
    public ModelAndView deleteCustomer(@RequestParam int id){
        int index = customerService.findById(id);
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
    public ModelAndView editCustomer(@ModelAttribute Customer customer){
        int index= (customerService.findById(customer.getId())) ;
        customerService.edit(index, customer);
        ModelAndView modelAndView = new ModelAndView("redirect:/customer");
        return modelAndView;
    }

    @GetMapping("/abc")
    public String demoBinlding(){
       return "demobinlding";
    }
    @PostMapping("/uppFile")
    public String upFile(@RequestParam MultipartFile uppImg) {

        return "redirect:/customer";
    }

}
