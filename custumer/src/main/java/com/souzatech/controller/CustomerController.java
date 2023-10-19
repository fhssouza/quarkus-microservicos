package com.souzatech.controller;

import com.souzatech.dto.CustomerDTO;
import com.souzatech.service.CustomerService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/customer")
public class CustomerController {

    @Inject
    private CustomerService customerService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CustomerDTO> findAllCustumers() {
        return customerService.findAllCustumers();
    }

    @POST
    @Transactional
    public Response saveCustomer(CustomerDTO customerDTO) {
        try {
            customerService.createNewCustomer(customerDTO);
            return Response.ok().build();
        }catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response changeCustomer(@PathParam("id") Long id, CustomerDTO customerDTO) {
        try {
            customerService.changeCustomer(id, customerDTO);
            return Response.accepted().build();
        }catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteCustomer(@PathParam("id") Long id) {
        try {
            customerService.deleteCustomer(id);
            return Response.accepted().build();
        }catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

}
