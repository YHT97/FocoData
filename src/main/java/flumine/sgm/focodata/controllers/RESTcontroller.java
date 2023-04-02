package flumine.sgm.focodata.controllers;

import flumine.sgm.focodata.models.RequestModel;
import flumine.sgm.focodata.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/")
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
    @PostMapping("/{id}/upload")
    ResponseEntity<?> upload(@RequestBody HashMap<String,Double> body, @PathVariable Long device_id){
        var key = body.keySet();
        if(!key.isEmpty()){
            for(var i:key) {
                db.save(new RequestModel(device_id,body.get(i),i));
            }
            return new ResponseEntity<>("ok",HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("ok",HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/{id}/get")
    ResponseEntity<?> load(@PathVariable Long device_id){
        var response = db.findAllByDevice(device_id);

        if(!response.isEmpty()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/{id}/get_p")
    ResponseEntity<?> get_page(@PathVariable Long device_id, @Param("fi") int first,@Param("li") int last,@Param("sort") String sort){
        Pageable pageable = PageRequest.of(first,last, Sort.by(sort));
        var response = db.findAllByDevice(device_id, pageable);
        if (!response.isEmpty()){
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
