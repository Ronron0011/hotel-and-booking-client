package fa.training.mockproject.mockprojectfjb05group01.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@MappedSuperclass
public class BaseModel {
    @CreationTimestamp
    @Column(name="created_time", nullable=false, updatable=false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDate createdTime;

    @UpdateTimestamp
    @Column(name="updated_time", nullable=false, updatable=true, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDate updatedTime;
}
