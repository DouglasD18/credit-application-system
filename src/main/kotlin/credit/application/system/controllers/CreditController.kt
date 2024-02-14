package credit.application.system.controllers

import credit.application.system.dtos.CreditDTO
import credit.application.system.entities.Credit
import credit.application.system.services.impl.CreditService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/credit")
class CreditController(
    private val creditService: CreditService
) {

    @PostMapping
    fun saveCredit(@RequestBody creditDTO: CreditDTO): String {
        val credit: Credit = this.creditService.save(creditDTO.toEntity())
        return "Credit ${credit.creditCode} - Customer ${credit.customer?.email} saved!"
    }

}