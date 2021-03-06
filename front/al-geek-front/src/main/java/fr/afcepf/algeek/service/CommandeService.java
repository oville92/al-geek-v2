package fr.afcepf.algeek.service;

import fr.afcepf.algeek.dto.Commande;
import fr.afcepf.algeek.dto.InfosBancaires;
import fr.afcepf.algeek.dto.LigneCommande;
import fr.afcepf.algeek.rest.ResponseEntityRestCommunicator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CommandeService {

    @Value("${algeek.gateway.address}")
    private String gatewayUrl;

    private final ResponseEntityRestCommunicator<Commande> cmdCommunicator = new ResponseEntityRestCommunicator<Commande>(Commande.class, Commande[].class);
    private final ResponseEntityRestCommunicator<InfosBancaires> infoCommunicator = new ResponseEntityRestCommunicator<InfosBancaires>(InfosBancaires.class, InfosBancaires[].class);
    private final ResponseEntityRestCommunicator<LigneCommande> lgCmdCommunicator = new ResponseEntityRestCommunicator<LigneCommande>(LigneCommande.class, LigneCommande[].class);


    // Remplace les appels à méthode des Services par un appel REST à order-manager
    public InfosBancaires ajouterInformationsBancaire(InfosBancaires infos) {
        String url = gatewayUrl + "/order/bank/add";
        ResponseEntity<InfosBancaires> response = infoCommunicator.post(url, infos);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        return null;
    }

    public Commande ajouterCommande(Commande commande) {
        String url = gatewayUrl + "/order/add";
        ResponseEntity<Commande> response = cmdCommunicator.post(url, commande);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        return null;
    }

    public LigneCommande ajouterLigneDeCommande(LigneCommande ligneCommande) {
        String url = gatewayUrl + "/order/lines/add";
        ResponseEntity<LigneCommande> response = lgCmdCommunicator.post(url, ligneCommande);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        return null;
    }
}
