package com.codersergg.checklimit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CheckLimitHistory {

    @Id@SequenceGenerator(
            name = "check_id_sequence",
            sequenceName = "check_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "check_id_sequence"
    )
    private Integer id;
    private Integer customerId;
    private Integer valueOfService;
    private String serviceName;
    private LocalDateTime createdAt;
    private String message;
}
