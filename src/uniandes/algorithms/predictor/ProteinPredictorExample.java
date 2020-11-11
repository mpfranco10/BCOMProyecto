package uniandes.algorithms.predictor;


public class ProteinPredictorExample {



	public static void main(String[] args) {

		String filename = "./data/Arabidopsis_thaliana.TAIR10.pep.all.fa.tsv";
		String outputfile = "data\\featuresFile.csv";
		Predictor predictor = new Predictor(filename, outputfile);



	}
}
