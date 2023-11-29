package ma.gov.cmr.echangeafbatch.dao.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "ws_pas_suivi")
@Data
public class WsPasSuiviEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_lot", nullable = false)
    private Long idLot;

    @Column(name = "date_lot")
    private Instant dateLot = Instant.now();

    @Column(name = "cin", nullable = false)
    private String cin;

    @Column(name = "idcs", nullable = false)
    private Integer idcs;

    @Column(name = "statut", nullable = false)
    private Integer statut;

    @Column(name = "date_creation")
    private Instant dateCreation = Instant.now();

    @Column(name = "date_modification")
    private Instant dateModification = Instant.now();

    @Column(name = "flag_situation")
    private Integer flagSituation;

    @Column(name = "flag_couvert_af")
    private Integer flagCouvertAf;

    @Column(name = "envoi_reponse")
    private String envoiReponse;

    @Column(name = "date_envoi_reponse")
    private Instant dateEnvoiReponse;
}
