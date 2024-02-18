package credit.application.system.controllers

import credit.application.system.dtos.CreditDTO
import credit.application.system.dtos.CreditListView
import credit.application.system.dtos.CreditView
import credit.application.system.entities.Credit
import credit.application.system.services.impl.CreditService
import jakarta.websocket.server.PathParam
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import java.util.stream.Collectors

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

    @GetMapping
    fun findByCustomerId(@RequestParam(value = "customerId") customerId: Long): List<CreditListView> {
        return this.creditService.findAllByCustomerId(customerId).stream()
            .map { credit: Credit -> CreditListView(credit) }
            .collect(Collectors.toList())
    }

    @GetMapping
    fun findByCreditCode(@RequestParam(value = "customerId") customerId: Long, @PathVariable creditCode: UUID): CreditView {
        val credit = this.creditService.findByCreditCode(customerId, creditCode)
        return CreditView(credit)
    }

}