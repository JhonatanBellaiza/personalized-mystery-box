package edu.miu.custommysterybox.controller;

import edu.miu.custommysterybox.dto.request.CustomerUpdateRequestDto;
import edu.miu.custommysterybox.dto.response.CustomerResponseDto;
import edu.miu.custommysterybox.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService managerService;

    /**
     * Retrieve all customers.
     *
     * @return List of CustomerResponseDto if available, otherwise a 204 No Content response.
     */
    @GetMapping("/customers")
    public ResponseEntity<List<CustomerResponseDto>> getAllCustomers() {
        Optional<List<CustomerResponseDto>> customers = managerService.getAllUsers();

        return customers.map(responseDtos -> ResponseEntity.ok(responseDtos))
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    /**
     * Retrieve details of a specific customer by email.
     *
     * @param id The id of the customer.
     * @return CustomerResponseDto if the customer exists, otherwise a 404 Not Found response.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> getCustomerDetails(@PathVariable Long id) {
        Optional<CustomerResponseDto> customer = managerService.getCustomerDetails(id);

        return customer.map(responseDto -> ResponseEntity.ok(responseDto))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * Edit details of a specific customer by email.
     *
     * @param id The id of the customer.
     * @return Updated CustomerResponseDto if successful, otherwise a 400 Bad Request response.
     */
    @PatchMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> editCustomerDetails(@PathVariable Long id, @RequestBody CustomerUpdateRequestDto customerUpdateRequestDto) {
        Optional<CustomerResponseDto> updatedCustomer = managerService.editCustomerDetails(id, customerUpdateRequestDto);

        return updatedCustomer.map(responseDto -> ResponseEntity.ok(responseDto))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }
}
