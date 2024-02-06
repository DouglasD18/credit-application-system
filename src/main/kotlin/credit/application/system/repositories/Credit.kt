package credit.application.system.repositories

import credit.application.system.entities.Credit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface Credit: JpaRepository<Credit, Long>