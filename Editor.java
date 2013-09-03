import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;




public class Editor implements ActionListener,ItemListener {

	
	public JFrame frame;
	public JMenuBar barraMenu;
	public JMenu archivo,edicion,ayuda;
	public JMenuItem nuevo,guardar, abrir,copiar,pegar,acerca,actualizar,cerrar,cortar;
	public TextArea areaTexto;
	public String cadena;
	public JButton negrita,cursiva,normal,color;
	public JPanel panel;
	public Choice tamañoMenuChoice,fuente;
	public int estilo=0,tamañoFuente=12;
	public String tipografia="Serif";
	public Color colorSelector;
	public FileDialog dialogoDeArchivos;
	public String ruta;
	
	public Editor(){
	
	frame=new JFrame("Pirate Text: EL Editor De Texto Pirata");
	barraMenu=new JMenuBar();
	archivo= new JMenu("Archivo");
	barraMenu.setBackground(Color.gray);
	archivo.setForeground(Color.white);
	edicion=new JMenu("Edición");
	edicion.setForeground(Color.white);
	ayuda=new JMenu("Ayuda");
	ayuda.setForeground(Color.white);
	
	cortar=new JMenuItem("Cortar");
	nuevo=new JMenuItem("Nuevo");
	guardar=new JMenuItem("Guardar cómo...");
	abrir=new JMenuItem("Abrir");
	copiar=new JMenuItem("Copiar");
	pegar=new JMenuItem("Pegar");
	acerca=new JMenuItem("Acerca de...");
	actualizar=new JMenuItem("Buscar actualizaciones");
	cerrar=new JMenuItem("Salir");
	areaTexto=new TextArea();
	
	
	frame.add(areaTexto);
	frame.setJMenuBar(barraMenu);
	barraMenu.add(archivo);
	barraMenu.add(edicion);
	barraMenu.add(ayuda);
	archivo.add(abrir);
	archivo.add(nuevo);
	archivo.add(guardar);
	edicion.add(copiar);
	edicion.add(cortar);
	edicion.add(pegar);
	ayuda.add(actualizar);
	ayuda.add(acerca);
	archivo.addSeparator();
	archivo.add(cerrar);
	
	//Image icono = Toolkit.getDefaultToolkit().getImage("ico.jpg");
    //frame.setIconImage(icono);
	
	
	Icon ico1= new ImageIcon("1.ico");
	cursiva.setIcon(ico1);
	
	
    cortar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
	copiar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
	pegar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
	nuevo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
	cerrar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
	abrir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
	guardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
	

	frame.setSize(600,600);
	frame.setVisible(true);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	

	panel = new JPanel();
	frame.add(panel, BorderLayout.NORTH);
	negrita=new JButton("Negrita");
	cursiva=new JButton("Cursiva");
	normal=new JButton("Normal");
	color=new JButton("Color de Fuente");
	
	panel.add(negrita);
	panel.add(cursiva);
	panel.add(normal);
	panel.add(color);
	
	tamañoMenuChoice=new Choice();
	fuente=new Choice();
	panel.add(fuente);
	panel.add(tamañoMenuChoice);
	panel.setBackground(Color.black);
	tamañoMenuChoice.add("12");
	tamañoMenuChoice.add("16");
	tamañoMenuChoice.add("20");
	tamañoMenuChoice.add("24");
	tamañoMenuChoice.add("28");
	tamañoMenuChoice.add("36");
	fuente.add("Serif");
	fuente.add("SanSerif");
	fuente.add("MonoSpace");
	fuente.add("DialogInput");
	fuente.add("Courier New");
	
	
	copiar.addActionListener(this);
	pegar.addActionListener(this);
	tamañoMenuChoice.addItemListener(this);
	fuente.addItemListener(this);
	negrita.addActionListener(this);
	cursiva.addActionListener(this);
	normal.addActionListener(this);
	acerca.addActionListener(this);
	actualizar.addActionListener(this);
	color.addActionListener(this);
	cerrar.addActionListener(this);
	guardar.addActionListener(this);
	nuevo.addActionListener(this);
	abrir.addActionListener(this);
}
	
	
	public void actionPerformed(ActionEvent ae){
		
		System.out.println(ae.getSource());
		if(ae.getSource().equals(copiar))
			cadena=areaTexto.getSelectedText();
		else if (ae.getSource().equals(pegar))
			areaTexto.insert(cadena,areaTexto.getCaretPosition());
		
		else if(ae.getSource().equals(acerca))
			JOptionPane.showMessageDialog(acerca, "Pirate Text Versión 1.5 \n Derechos Reservados");
		
		
		else if(ae.getSource().equals(actualizar))
			JOptionPane.showMessageDialog(actualizar, "Copia no registrada \n Actualización abortada");
		
		else if(ae.getSource().equals(normal))
		estilo=0;
		else if(ae.getSource().equals(negrita))
		estilo=1;
		else if(ae.getSource().equals(cursiva))
		estilo=2;
		
		else if( ae.getSource().equals(cerrar))
			System.exit(0);
		
		if(ae.getSource().equals(guardar)){
		dialogoDeArchivos=new FileDialog(frame,"Guardar cómo...",FileDialog.SAVE); 
			dialogoDeArchivos.setVisible(true);
			copiarArchivo();
			}
		
		else if(ae.getSource().equals(nuevo)){
			areaTexto.setText("");
			
		}
		
		else if(ae.getSource().equals(abrir)){
			dialogoDeArchivos=new FileDialog(frame,"Abrir",FileDialog.LOAD); 

			dialogoDeArchivos.setVisible(true);
			leerArchivo();
			}
	
		else if(ae.getSource() == cortar){
            cortar();
		}
            
		else if(ae.getSource().equals(color))
			colorSelector=JColorChooser.showDialog(color, "Seleccionar Color de Fuente", Color.black);
		
			if (colorSelector!= null)
			areaTexto.setForeground(colorSelector);
			
			
		areaTexto.setFont(new Font(tipografia,estilo,tamañoFuente));
		
		
	}
	
	
	public void itemStateChanged(ItemEvent e){
		
	if(e.getItem()=="12")
	tamañoFuente=12;
	
	else if(e.getItem()=="16")
	tamañoFuente=16;		
	else if(e.getItem()=="20")
		tamañoFuente=20;
	else if(e.getItem()=="24")
		tamañoFuente=24;
	else if(e.getItem()=="28")
		tamañoFuente=28;
	else if(e.getItem()=="36")
		tamañoFuente=36;
	else if(e.getItem()=="Serif")
		tipografia="Serif";
	else if(e.getItem()=="SanSerif")
		tipografia="SanSerif";
	else if(e.getItem()=="MonoSpace")
		tipografia="MonoSpace";
	else if(e.getItem()=="DialogInput")
		tipografia="DialogInput";
	else if(e.getItem()=="Courier New")
		tipografia="Courier New";
	
	
	areaTexto.setFont(new Font(tipografia,estilo, tamañoFuente));
	
	
	}
	
	void copiarArchivo(){

		 try { 
		 ruta=dialogoDeArchivos.getDirectory()+dialogoDeArchivos.getFile();

		 File outputFile = new File(ruta);
		 FileOutputStream fos=new FileOutputStream(outputFile);

		 DataOutputStream dos = new DataOutputStream(fos);

		 dos.writeChars(areaTexto.getText()); dos.close(); } catch (FileNotFoundException e) {
		 System.err.println("FileStreamsTest: " + e);
		 } catch (IOException e) {
		 System.err.println("FileStreamsTest: " + e);
		 } 
	}
	
	@SuppressWarnings("deprecation")
	void leerArchivo(){
		try { 
		ruta=dialogoDeArchivos.getDirectory()+dialogoDeArchivos.getFile();
		File inputFile = new File(ruta);
		FileInputStream fos=new FileInputStream(inputFile);
		DataInputStream dos = new DataInputStream(fos);
		areaTexto.setText(dos.readLine()); dos.close(); 
		} catch (FileNotFoundException e) {
		System.err.println("FileStreamsTest: " + e);
		} catch (IOException e) {
		System.err.println("FileStreamsTest: " + e);
		} 
		
	}
	
	 public void cortar(){
	        int inicio = areaTexto.getSelectionStart();
	        int fin = areaTexto.getSelectionEnd();
	        String s = areaTexto.getText();
	        cadena = s.substring( inicio , fin );
	        String inicioText = areaTexto.getText().substring(0, inicio);
	        String finText = areaTexto.getText().substring(fin, areaTexto.getText().length());
	        areaTexto.setText(inicioText+finText);
	       
	    }
	
	/*public void abrirArchivo(){
		try{
            
            FileInputStream fstream = new FileInputStream("");
            DataInputStream entrada = new DataInputStream(fstream);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
            String strLinea;
            while ((strLinea = buffer.readLine()) != null)   {
                System.out.println (strLinea);
            }
            entrada.close();
        }catch (Exception e){
            System.err.println(" Ocurrio un error: " + e.getMessage());
        }
        }
        */
		
	
	
	public static void main(String[] args) {
		
		new Editor();
		
	}



}