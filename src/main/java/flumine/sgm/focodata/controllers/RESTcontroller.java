package flumine.sgm.focodata.controllers;

import flumine.sgm.focodata.models.RequestModel;
import flumine.sgm.focodata.repository.DataRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/")
public class RESTcontroller {

    final
    DataRepository  db;

    public RESTcontroller(DataRepository db) {
        this.db = db;
    }

    @GetMapping("/")
    ResponseEntity<?> index(){
        return new ResponseEntity<>(200, HttpStatus.OK);
    }
    @CrossOrigin
    @PostMapping("/{id}/upload")
    ResponseEntity<?> upload(@RequestBody HashMap<String,Double> body, @PathVariable Long id){
        var key = body.keySet();
        if(!key.isEmpty()){
            for(var i:key) {
                db.save(new RequestModel(id,body.get(i),i));
            }
            return new ResponseEntity<>(body, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("400",HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/{id}/get")
    ResponseEntity<?> get_page(@PathVariable Long id, @Param("PageNumber") int PageNumber,@Param("PageSize") int PageSize,@Param("sort") String sort){
        Pageable pageable = PageRequest.of(PageNumber,PageSize, Sort.by(sort));
        var response = db.findAllByDevice(id, pageable);
        if (!response.isEmpty()){
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        return new ResponseEntity<>("400", HttpStatus.BAD_REQUEST);
    }
}
