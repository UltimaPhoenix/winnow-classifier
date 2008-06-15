package ia2.parse;

import static ia2.util.TrecCommons.createInstance;
import static ia2.util.TrecCommons.getAttributesFastVector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.StringTokenizer;

import weka.core.Instance;
import weka.core.Instances;

public class TrecParser implements Parser {
		
	private static final int DEFAULT_NUMBER_OF_INSTANCE = 6000;	
	private static final String DEFAULT_DATASET_NAME = "default dataset";
		
	private BufferedReader bufferedReader;
	
	public TrecParser(InputStream inputStream) {
		this(new InputStreamReader(inputStream));
	}
	
	public TrecParser(Reader inputStream) {
		this(new BufferedReader(inputStream));
	}
	
	public TrecParser(BufferedReader inputStream) {
		this.bufferedReader = inputStream;
	}

	@Override
	public Instances getDataSet() {
		return this.getDataSet(-1);
	}	

	private Instance readInstance(String line, Instances dataSet) {
		String nameCoarseCategory;		//La classe generica
		@SuppressWarnings("unused")
		String nameFineCategory;		//La classe specifica
		String question;				//La domanda divisa in token
		
		StringTokenizer stringTokenizer = new StringTokenizer(line, ":");
		nameCoarseCategory =	stringTokenizer.nextToken();
		nameFineCategory =		stringTokenizer.nextToken(" ");		//Da questo punto il delimitatore resta o spazio
		StringBuilder stringBuilder = new StringBuilder();		//il numero di elementi rimanenti
		for(int i=0; stringTokenizer.hasMoreTokens(); i++)
			stringBuilder.append(stringTokenizer.nextToken()).append(" ");
		question = stringBuilder.toString();
		return createInstance(nameCoarseCategory,question,dataSet);		
	}

	@Override
	public Instances getDataSet(int numberOfInstanceToRead) {
		Instances resultDataSet = new Instances(DEFAULT_DATASET_NAME,getAttributesFastVector(),DEFAULT_NUMBER_OF_INSTANCE);
		resultDataSet.setClassIndex(0);//TODO � troppo accoppiato
		try {
			populateDataSet(resultDataSet,this.bufferedReader,numberOfInstanceToRead);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultDataSet;
	}

	private void populateDataSet(Instances dataSet,BufferedReader bufferedReader, int numberOfInstanceToRead) throws IOException {
		for (String newLine = bufferedReader.readLine(); newLine!=null && numberOfInstanceToRead-- != 0; newLine = bufferedReader.readLine()){
			Instance instance = readInstance(newLine,dataSet);
			dataSet.add(instance);
		}
	}
}
