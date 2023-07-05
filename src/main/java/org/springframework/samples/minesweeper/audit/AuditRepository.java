package org.springframework.samples.minesweeper.audit;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AuditRepository extends CrudRepository<Audit, Integer> {
	
	@Query("SELECT audit FROM Audit audit ORDER BY audit.startDate DESC")
	public List<Audit> findAudits(Pageable pageable);
	
	@Query("SELECT audit FROM Audit audit WHERE audit.inProgress=true AND audit.user.username = :username")
    public Audit findActiveAuditByUsername(String username);
}