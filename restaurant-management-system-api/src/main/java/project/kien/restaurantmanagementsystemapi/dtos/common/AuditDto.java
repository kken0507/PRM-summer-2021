package project.kien.restaurantmanagementsystemapi.dtos.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createdAt", "updatedAt"},
        allowGetters = true
)
@Getter
@Setter
@NoArgsConstructor
public class AuditDto {

    private LocalDateTime createdAt;

    private Integer createdBy;

    private LocalDateTime updatedAt;

    private Integer updatedBy;

}
