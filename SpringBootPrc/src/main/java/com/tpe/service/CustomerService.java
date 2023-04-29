package com.tpe.service;

import com.tpe.domain.Customer;
import com.tpe.dto.CustomerDTO;
import com.tpe.exception.ConflictException;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public void saveCustomer(Customer customer) {

        boolean isExistCustomer = customerRepository.existsByEmail(customer.getEmail());
        if (isExistCustomer) {
            throw new ConflictException("Customer already exist by email:" + customer.getEmail());
        }
        customerRepository.save(customer);

    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers;
    }

    public Customer getCustomerById(Long id) {
       Customer customer = customerRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Customer not found by id "+id));
   return customer;
    }

    public void deleteCustomer(Long id) {
       Customer customer= getCustomerById(id);
       customerRepository.delete(customer);
    }

    public CustomerDTO getCustomerDTOById(Long id) {
        Customer customer = getCustomerById(id);
//        CustomerDTO customerDTO = new CustomerDTO();
//        customerDTO.setName(customer.getName());
//        customerDTO.setLastName(customer.getLastName());
//        customerDTO.setEmail(customer.getEmail());
//        customerDTO.setPhone(customer.getPhone());
        CustomerDTO customerDTO=new CustomerDTO(customer);
        return customerDTO;
    }

    public void updateCustomer(Long id, CustomerDTO customerDTO) {
        Customer customer=getCustomerById(id);
        boolean isExistEmail=customerRepository.existsByEmail(customerDTO.getEmail());

        if(isExistEmail && !customer.getEmail().equals(customerDTO.getEmail())  ){
            throw new ConflictException("Customer already exist by email:"+customerDTO.getEmail());
        }
        customer.setName(customerDTO.getName());
        customer.setLastName(customerDTO.getLastName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhone(customerDTO.getPhone());
        customerRepository.save(customer);

    }

    public Page<Customer> getAllCustomerPage(Pageable pageable) {
        Page<Customer> customers = customerRepository.findAll(pageable);
        return customers;

    }

    public List<Customer> getCustomerByName(String name) {
        List<Customer> customers=customerRepository.findByName(name);
        return customers;

    }

    public List<Customer> getCustomersByNameAndLastName(String name, String lastName) {
        List<Customer> customerList=customerRepository.findByNameAndLastName(name,lastName);
        return customerList;
    }
}
