package uniandes.algorithms.predictor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Predictor {

	private final String[] CC = { "Coil", "G3DSA:1.20.5.170", "SSF57959" };
	private final String[] GNK2 = { "PS51473", "IPR002902" };
	private final String[] LRR = { "IPR032675", "PF13855", "SSF52058", "SM00369", "SM00365", "PS51450", "PF13855",
			"PF13516", "PF12799", "PF08263", "PF07725", "PF00560", "IPR013210", "IPR011713", "IPR003591", "IPR001611" };
	private final String[] MLO = { "PF03094", "IPR004326" };
	private final String[] NBS = { "PF00931", "IPR002182" };
	private final String[] PTOL = { "G3DSA:3.30.200.20", "G3DSA:1.10.510.10", "G3DSA:3.30.200.20", "PF07714", "PS00107",
			"SM00220", "SSF56112", "PS50011", "PS00108", "PS00107", "IPR011009", "IPR000719", "IPR001245", "IPR017441",
			"IPR008271" };
	private final String[] RECEPL = { "SSF51110", "PS50927", "SM00108", "IPR001480", "G3DSA:2.90.10.10", "IPR036426",
			"PF01453", "PS50927", "IPR024171" };
	private final String[] RPW8 = { "IPR008808", "PS51153" };
	private final String[] SERTHRK = { "SM00219", "G3DSA:3.30.200.20", "IPR000719", "IPR001245", "IPR008271",
			"IPR011009", "IPR017441", "IPR020635", "IPR021820", "IPR022126", "PF00069", "PF07714", "PS00107", "PS00108",
			"PS50011", "SM00219", "SM00220", "SSF56112" };
	private final String[] TIR = { "IPR000157", "PF01582", "PS50104", "SM00255", "SSF52200" };

	public Predictor(String filename, String outputfile) {


		BufferedReader reader;
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(outputfile));
			String lineToWrite = "LineNumber,Line,CC,GNK2,LRR,MLO,NBS,PTOL,RECEPL,RPW8,SERTHRK,TIR";
			pw.println(lineToWrite);
			int lineNumber = 0;
			long time = System.currentTimeMillis();
			reader = new BufferedReader(new FileReader(filename));
			String line = reader.readLine();
			while (line != null) {
				lineToWrite = processLine(line,lineNumber);
				// read next line
				pw.println(lineToWrite);
				line = reader.readLine();
				lineNumber++;
			}
			reader.close();
			pw.close();
			time = System.currentTimeMillis() - time;
			System.out.println("Time searching for domains: " + time + "ms");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String processLine(String line, int lineNumber) {
		String answer = "";
		String separator = ",";
		int CCFlag = 0;
		int GNK2Flag = 0;
		int LRRFlag = 0;
		int MLOFlag = 0;
		int NBSFlag = 0;
		int PTOLFlag = 0;
		int RECEPLFlag = 0;
		int RPW8Flag = 0;
		int SERTHRKFlag = 0;
		int TIRFlag = 0;

		if (containsWords(line, CC))
			CCFlag = 1;
		if (containsWords(line, GNK2))
			GNK2Flag = 1;
		if (containsWords(line, LRR))
			LRRFlag = 1;
		if (containsWords(line, MLO))
			MLOFlag = 1;
		if (containsWords(line, NBS))
			NBSFlag = 1;
		if (containsWords(line, PTOL))
			PTOLFlag = 1;
		if (containsWords(line, RECEPL))
			RECEPLFlag = 1;
		if (containsWords(line, RPW8))
			RPW8Flag = 1;
		if (containsWords(line, SERTHRK))
			SERTHRKFlag = 1;
		if (containsWords(line, TIR))
			TIRFlag = 1;

		line = line.replaceAll(",", " ");
		line = line.replaceAll("\t", " ");
		answer = lineNumber + separator + line + separator + CCFlag + separator + GNK2Flag + separator + LRRFlag + separator + MLOFlag
				+ separator + NBSFlag + separator + PTOLFlag + separator + RECEPLFlag + separator + RPW8Flag + separator
				+ SERTHRKFlag + separator + TIRFlag;
		
		return answer;
	}

	public boolean containsWords(String input, String[] words) {
		// return Arrays.stream(words).anyMatch(input::contains);
		for (String s : words) {
			if (input.contains(s)) {
				return true;
			}
		}
		return false;
	}

}
