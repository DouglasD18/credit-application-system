package credit.application.system.services

import credit.application.system.entities.Credit
import java.util.UUID

interface ICreditService {

    fun save(credit: Credit): Credit
    fun findAll(): List<Credit>
    fun findByCreditCode(creditCode: UUID): Credit

}