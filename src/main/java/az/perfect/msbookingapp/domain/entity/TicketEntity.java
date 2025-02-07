package az.perfect.msbookingapp.domain.entity;


import az.perfect.msbookingapp.model.enums.ClassType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ticket")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "class_type", nullable = false)
    private ClassType classType;

    @Column(name = "price", nullable = false)
    private Integer price;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    FlightEntity flight;
}
