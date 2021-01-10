package com.g2.scheduleservice.domain;

import com.g2.scheduleservice.api.rest.resource.ResourceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private ResourceType type;
    private String description;

    @OneToMany(mappedBy = "resource")
    private List<Booking> bookings;

}
