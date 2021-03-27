package creatorInvoice.repository;

import creatorInvoice.model.InvoicePosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoicePositionRepository extends JpaRepository<InvoicePosition, Long> {

}
