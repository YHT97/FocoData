package flumine.sgm.focodata.repository;

import flumine.sgm.focodata.models.RequestModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataRepository extends JpaRepository<RequestModel, Long> {
    List<RequestModel> findAllByDevice(Long device_id);
    List<RequestModel> findAllByDevice(Long device_id, Pageable pageable);
}
