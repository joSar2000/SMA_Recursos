/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemamultiagente.Agentes;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import sistemamultiagente.GUI.*;
/**
 *
 * @author LENOVO
 */
public class AgenteEstadistico extends Agent{
    private GUIFrame formularioRecurso;

    //Todo agente debe iniciarse con el método setup()
    @Override
    protected void setup() {
        
        /*
        Inicio con mi Interfaz
        */
        formularioRecurso = new GUIFrame();
        formularioRecurso.setVisible(true);
    }

    //Método que permite eliminar al agente y liberar recursos
    @Override
    protected void takeDown() {
        /*
        Vamos a cerrar la interfaz
        */
        formularioRecurso.dispose();
        /*
        Mensaje de despedida
        */
        System.out.println("Gracias "+this.getName()+" por interactuar con nuestro sistema de Gestión de Recursos Acedémicos!!!");
        
    }
    
    /*
    Método para poder obtener la información desde mi interfaz
    https://serpapi.com/search.json?engine=google_scholar&q=biology&api_key=27c2e12246ac85446093e4ca6ec1eb88258e82f3a5c2dfa04f1aac82a68ba856
    */
    public void GetResources (String ResourceName) {
        addBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                String URI = "https://serpapi.com/search.json?engine=google_scholar&q="+ResourceName+"&api_key=27c2e12246ac85446093e4ca6ec1eb88258e82f3a5c2dfa04f1aac82a68ba856";
                System.out.println("Link entregado: ["+URI+"]");//To change body of generated methods, choose Tools | Templates.
            }
        });
    }
}
