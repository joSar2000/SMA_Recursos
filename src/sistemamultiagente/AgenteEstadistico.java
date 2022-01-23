/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemamultiagente;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import javax.swing.JFrame;
import sistemamultiagente.GUI.*;

/**
 *
 * @author LENOVO
 */
public class AgenteEstadistico extends Agent {

    private GUIFrame UI;

    //Todo agente debe iniciarse con el método setup()
    @Override
    public void setup() {
        JFrame frame = new JFrame("Recursos");
        UI = new GUIFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        UI.btnBuscar.addActionListener((e) -> {
            GetResources(UI.TextRecurso.getText());
        });
        
        UI.pack();
        UI.setVisible(true);
        
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage msg = receive();
                
                if (msg != null) {
                    try {
                        String content = msg.getContent();
                        System.out.println(content);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    block();
                }
            }
        });
    }

    //Método que permite eliminar al agente y liberar recursos
    @Override
    protected void takeDown() {
        try {
            DFService.deregister(this);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
        /*
        Vamos a cerrar la interfaz
         */
        UI.dispose();
        /*
        Mensaje de despedida
         */
        System.out.println("Gracias " + this.getName() + " por interactuar con nuestro sistema de Gestión de Recursos Acedémicos!!!");

    }

    
    public void GetResources(String resources) {

        Map<String, String> map = new HashMap<>();
        map.put("recursos", resources);
        map.put("autor", "Jose Castillo");
        ACLMessage msg  = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(new AID("agenterepositorio", AID.ISLOCALNAME));
        msg.setContent(map.get("recursos").toString() + "," +map.get("autor").toString() );
        send(msg);
    }

    /*
    public void test(String data) {
        String URI = "https://serpapi.com/search.json?engine=google_scholar&q=" + data + "&api_key=27c2e12246ac85446093e4ca6ec1eb88258e82f3a5c2dfa04f1aac82a68ba856";
        System.out.println("Link entregado: [" + URI + "]");
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(new AID("agenterepositorio", AID.ISLOCALNAME));
        msg.setLanguage("English");
        msg.setOntology("Weather-forecast-ontology");
        msg.setContent("asdasd");
        
        send(msg);
    }
     */
    /*
    private class TestApi extends OneShotBehaviour {

        @Override
        public void action() {
            ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
            msg.setContent(q);
            msg.addReceiver(new AID("agenterepositorio", AID.ISLOCALNAME));
            send(msg);

        }

    }
*/
}
