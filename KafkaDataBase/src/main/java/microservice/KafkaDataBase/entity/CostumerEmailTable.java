package microservice.KafkaDataBase.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="costume_email_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CostumerEmailTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="email")
    private String email;

    @Column(name="name")
    private String name;



}
