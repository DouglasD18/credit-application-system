package credit.application.system.repositories

import credit.application.system.entities.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface Customer: JpaRepository<Customer, Long>