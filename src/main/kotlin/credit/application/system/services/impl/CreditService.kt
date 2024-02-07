package credit.application.system.services.impl

import credit.application.system.entities.Credit
import credit.application.system.repositories.CreditRepository
import credit.application.system.services.ICreditService
import org.springframework.stereotype.Service
import java.util.*

@Service
class CreditService(
    private  val repository: CreditRepository
    private val customerService: CustomerService
): ICreditService {
    override fun save(credit: Credit): Credit {
        credit.apply {
            customer = customerService.findById(credit.customer?.id!!)
        }

        return this.repository.save(credit)
    }

    override fun findAllByCustomerId(customerId: Long): List<Credit> = this.repository.findAllByCustomerId(customerId)

    override fun findByCreditCode(customerId: Long, creditCode: UUID): Credit {
        val credit: Credit = (this.repository.findByCreditCode(creditCode) ?: throw RuntimeException("Creditcode $creditCode not found!"))
        return if (credit.customer?.id == customerId) credit else throw RuntimeException("Permission denied")
    }
}