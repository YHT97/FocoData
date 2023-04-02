package flumine.sgm.focodata.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class RequestModel {
    @Id
    @GeneratedValue
    @Column
    Long id;
    @Column
    Long device;
    @Column
    Double data;
    @Column
    String type;
    @Column
    LocalDate date;

    public RequestModel(Long device_id, Double data, String type) {
        this.device = device_id;
        this.data = data;
        this.type = type;
        this.date = LocalDate.now();
    }

    public RequestModel() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDevice() {
        return device;
    }

    public void setDevice(Long device) {
        this.device = device;
    }

    public Double getData() {
        return data;
    }

    public void setData(Double data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
