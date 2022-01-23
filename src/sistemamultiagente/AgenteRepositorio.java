/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemamultiagente;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author LENOVO
 */

/*
    Obtenemos cada uno de nuestros parametros
    https://serpapi.com/search.json?engine=google_scholar&q=biology&api_key=27c2e12246ac85446093e4ca6ec1eb88258e82f3a5c2dfa04f1aac82a68ba856
     */
public class AgenteRepositorio extends Agent{
    @Override
    protected void setup() {
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage msg = receive();
                if (msg != null) {
                    System.out.println(msg.getContent());

                    String recurso = msg.getContent().split(",", 2)[0].trim();
                    String autor = msg.getContent().split(",", 2)[1].trim();
                    
                    String URL = "http:"+recurso+"/"+autor;
                    //System.out.println("Recurso: "+recurso +"\n"+"autor: "+autor);
                    
                    ACLMessage msgCla  = new ACLMessage(ACLMessage.INFORM);
                    msgCla.addReceiver(new AID("agenteclasificador", AID.ISLOCALNAME));
                    msgCla.setContent(URL);
                    send(msgCla);
                } else {
                    block();
                }
            }
        });
    }
    
    
}
