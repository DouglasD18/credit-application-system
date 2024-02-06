package credit.application.system.services.impl

import credit.application.system.entities.Customer
import credit.application.system.repositories.CustomerRepository
import credit.application.system.services.ICustomerService
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val repository: CustomerRepository
): ICustomerService {
    override fun save(customer: Customer): Customer = this.repository.save(customer)

    override fun findById(id: Long): Customer = this.repository.findById(id).orElseThrow{
        throw RuntimeException("Id $id not found!")
    }

    override fun delete(id: Long) = this.repository.deleteById(id)
}