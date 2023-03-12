package com.example.mvccrud.Aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class DeleteCustomerAdvice {

    @Before("execution(public void com.example.mvccrud.service.impl.CustomerServiceImpl.deleteCustomer(..))")
    public void beforeDeleteCustomer(){
        System.out.println("you are deleting this user!!");
    }
}
