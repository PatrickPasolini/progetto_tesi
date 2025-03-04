package it.unibs.mylib;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServizioFile {
	private final static String MSG_NO_FILE = "ATTENZIONE: NON TROVO IL FILE ";
	public final static String MSG_NO_LETTURA = "ATTENZIONE: PROBLEMI CON LA LETTURA DEL FILE ";
	public final static String MSG_NO_SCRITTURA = "ATTENZIONE: PROBLEMI CON LA SCRITTURA DEL FILE ";
	public final static String MSG_NO_CHIUSURA = "ATTENZIONE: PROBLEMI CON LA CHIUSURA DEL FILE ";
//	private final static String OUTPUT_PATH = System.getProperty("file.dir");
	public final static String OUTPUT_FILE_NAME = "file.txt";

	public static Object caricaSingoloOggetto(File f) {
		Object letto = null;
		ObjectInputStream ingresso = null;

		try {
			ingresso = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)));

			letto = ingresso.readObject();

		} catch (FileNotFoundException excNotFound) {
			System.out.println(MSG_NO_FILE + f.getName());
		} catch (IOException excLettura) {
			System.out.println(MSG_NO_LETTURA + f.getName());
		} catch (ClassNotFoundException excLettura) {
			System.out.println(MSG_NO_LETTURA + f.getName());
		} finally {
			if (ingresso != null) {
				try {
					ingresso.close();
				} catch (IOException excChiusura) {
					System.out.println(MSG_NO_CHIUSURA + f.getName());
				}
			}
		} // finally

		return letto;

	} // metodo caricaSingoloOggetto

	public static void salvaSingoloOggetto(File f, Object daSalvare) {
		ObjectOutputStream uscita = null;

		try {
			uscita = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)));

			uscita.writeObject(daSalvare);

		} catch (IOException excScrittura) {
			System.out.println(MSG_NO_SCRITTURA + f.getName());
		}

		finally {
			if (uscita != null) {
				try {
					uscita.close();
				} catch (IOException excChiusura) {
					System.out.println(MSG_NO_CHIUSURA + f.getName());
				}
			}
		} // finally

	} // metodo salvaSingoloOggetto

	public static void saveOnTxtFileWithDate(String testoDaSalvare,String percorsoFile) {

		PrintWriter out = null;
//		String percorsoFile = OUTPUT_PATH + File.separator + OUTPUT_FILE_NAME;
		try {
			out = new PrintWriter(new FileOutputStream(new File(percorsoFile), true));
			String dataDaStampare = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss").format(new Date());
			out.append("----------------------------------------------------------\n");
			out.append(dataDaStampare + " - " + testoDaSalvare + System.lineSeparator());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}

	}

}
