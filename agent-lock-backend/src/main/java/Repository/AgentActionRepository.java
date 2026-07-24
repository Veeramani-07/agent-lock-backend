package Repository;

import Entity.AgentActionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgentActionRepository extends JpaRepository<AgentActionEntity, String> {
    List<AgentActionEntity> findByStatus(String status);
}