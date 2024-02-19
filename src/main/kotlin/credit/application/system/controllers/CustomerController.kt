package credit.application.system.controllers

import credit.application.system.dtos.CustomerDTO
import credit.application.system.dtos.CustomerUpdate
import credit.application.system.dtos.CustomerView
import credit.application.system.entities.Customer
import credit.application.system.services.impl.CustomerService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/customer")
class CustomerController(
    private val customerService: CustomerService
) {

    @PostMapping
    fun saveCustomer(@RequestBody @Valid customerDTO: CustomerDTO): ResponseEntity<String> {
        val savedCustomer = this.customerService.save(customerDTO.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED).body("Customer ${savedCustomer.email} saved!")
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<CustomerView> {
        val customer: Customer = this.customerService.findById(id)

        return ResponseEntity.status(HttpStatus.OK).body(CustomerView(customer))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCustomer(@PathVariable id: Long) = this.customerService.delete(id)

    @PatchMapping
    fun updateCustomer(
        @RequestParam(value = "customerId") id: Long,
        @RequestBody @Valid customerUpdate: CustomerUpdate
    ): ResponseEntity<CustomerView> {
        val customer: Customer = this.customerService.findById(id)
        val toUpdate: Customer = customerUpdate.toEntity(customer)
        val updated: Customer = this.customerService.save(toUpdate)

        return ResponseEntity.status(HttpStatus.OK).body(CustomerView(updated))
    }

}