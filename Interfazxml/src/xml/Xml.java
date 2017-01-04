package xml;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.SQLException;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import org.w3c.dom.*;
import javax.xml.parsers.*;
public class Xml {
    private String Proveedor="",Comprador="",Factura="",Tempresa="";
    public String[] VLLVentana=new String[10];
    
    private String ubicacionArchivo="";
    public Xml(String archivo){
         Proveedor="INSERT INTO Proveedor VALUES (";
        Comprador="INSERT INTO Comprador VALUES (";
        Factura="INSERT INTO Factura VALUES (";
        ubicacionArchivo=archivo;
        try{
        leerConfiguracion();
        }
        catch(NullPointerException e)
        {
            System.err.println("fallo");
        }
    }
public void leerConfiguracion(){      
            try{
           int temp=0;
            File fXmlFile = new File(ubicacionArchivo);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();      
            //System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
           try{
             
            NodeList nList = doc.getElementsByTagName("infoTributaria");            
            Node nNode = nList.item(temp);
            Element eElement = (Element) nNode; 
            //if(eElement.getElementsByTagName("razonSocial").item(0).getTextContent()!=null) {
                 NodeList nList1 = doc.getElementsByTagName("infoFactura");           
            Node nNode1 = nList1.item(temp);
            Element eElement1 = (Element) nNode1;
            NodeList nList2 = doc.getElementsByTagName("detalles");         
            Node nNode2 = nList2.item(temp);
            Element eElement2 = (Element) nNode2;
            NodeList nAuxiliar = doc.getElementsByTagName("detalle");  
            System.out.println("2");
                 System.out.println("Razon Social  "+Tempresa);

            Proveedor+="'"+eElement.getElementsByTagName("ruc").item(0).getTextContent()+"'";
            VLLVentana[0]=eElement.getElementsByTagName("ruc").item(0).getTextContent();
            //razon social rsx
            Proveedor+=",'"+eElement.getElementsByTagName("razonSocial").item(0).getTextContent()+"'";
            VLLVentana[1]=eElement.getElementsByTagName("razonSocial").item(0).getTextContent();
            //direccion proveedor dirc
            Proveedor+=",'"+eElement1.getElementsByTagName("dirEstablecimiento").item(0).getTextContent()+"','')";
            VLLVentana[2]=eElement1.getElementsByTagName("dirEstablecimiento").item(0).getTextContent();
            ///FACTURA----------------------
            //codigo factura
            Factura+="'"+eElement.getElementsByTagName("secuencial").item(0).getTextContent()+"'";
            VLLVentana[3]=eElement.getElementsByTagName("secuencial").item(0).getTextContent();
            // emision factura ff
            Factura+=",'"+intercalarFecha(eElement1.getElementsByTagName("fechaEmision").item(0).getTextContent())+"'";
            VLLVentana[4]=intercalarFecha(eElement1.getElementsByTagName("fechaEmision").item(0).getTextContent());
            //total sin impuestos tsi
            Factura+=",'"+eElement1.getElementsByTagName("totalSinImpuestos").item(0).getTextContent();
            VLLVentana[5]=eElement1.getElementsByTagName("totalSinImpuestos").item(0).getTextContent();
            // valor impuesto iva 
            Factura+="','"+eElement2.getElementsByTagName("tarifa").item(0).getTextContent();
            VLLVentana[6]=eElement2.getElementsByTagName("tarifa").item(0).getTextContent();
            // valor total total
            Factura+="','"+eElement1.getElementsByTagName("importeTotal").item(0).getTextContent();
            VLLVentana[7]=eElement1.getElementsByTagName("importeTotal").item(0).getTextContent();
            //Ruc proveedor
            Factura+="','"+eElement.getElementsByTagName("ruc").item(0).getTextContent()+"'";
            VLLVentana[8]=eElement.getElementsByTagName("ruc").item(0).getTextContent();
            //Ruc Comprador
            Factura+=",'"+eElement1.getElementsByTagName("identificacionComprador").item(0).getTextContent()+"',";
            //VLLVentana[9]=eElement1.getElementsByTagName("identificacionComprador").item(0).getTextContent();
           }
              catch(NullPointerException e)
            {
            leerConfiguracion1();
            }
            }
      catch (Exception e) {
        e.printStackTrace();
    }
         
  }

public void leerConfiguracion1(){
    System.out.println("entrooo");
        Proveedor="INSERT INTO Proveedor VALUES (";
        Comprador="INSERT INTO Comprador VALUES (";
        Factura="INSERT INTO Factura VALUES (";
            try{
           int temp=0;
            File fXmlFile = new File(ubicacionArchivo);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();      
            //System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
           try{
                NodeList nListp = doc.getElementsByTagName("autorizacion");            
            Node nNodep = nListp.item(temp);
            Element eElementp = (Element) nNodep; 
               System.out.println(eElementp.getElementsByTagName("comprobante").item(0).getTextContent());
                File archivo = new File(ubicacionArchivo);
                BufferedWriter bw;
                if(archivo.exists()) {
                bw = new BufferedWriter(new FileWriter(archivo));
                bw.write(eElementp.getElementsByTagName("comprobante").item(0).getTextContent());
                } else {
                bw = new BufferedWriter(new FileWriter(archivo));
                bw.write(eElementp.getElementsByTagName("comprobante").item(0).getTextContent());
                }

                bw.close();
                leerConfiguracion();

           }
              catch(NullPointerException e)
            {
                
            }
            }
      catch (Exception e) {
        e.printStackTrace();
    }
         
  }
    public void EjecutarConsultaSQL(boolean verificar, int tipo_gasto){
         ConexionBase con=new ConexionBase();
        Factura+="'"+tipo_gasto+"')";
        if(verificar){
            con.InsertDB(Proveedor);
            con.InsertDB(Factura);
            System.out.println(Proveedor+"\n"+Factura);
        }else
            JOptionPane.showMessageDialog(null, "Selecione el archivo XML a guardar");
    }
    
    private String intercalarFecha(String fecha){
        String fe[]=new String[3];
        int i=0;
	StringTokenizer tokens=new StringTokenizer(fecha);
	while(tokens.hasMoreTokens()){
            fe[i++]=tokens.nextToken("/");
        }
        fecha="";
        for(int l=2;l>=0;l--){
            fecha+=fe[l];
            if(l>0)
                fecha+="-";
        }
        return fecha;
    }
    
}

