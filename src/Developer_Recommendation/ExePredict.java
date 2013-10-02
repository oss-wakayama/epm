package Developer_Recommendation;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.*;
import weka.core.converters.ArffLoader;
import weka.core.converters.ConverterUtils.DataSource;
import weka.estimators.Estimator;
import weka.filters.Filter;
import weka.filters.supervised.attribute.AttributeSelection;
import weka.filters.unsupervised.attribute.Reorder;
import weka.filters.unsupervised.attribute.StringToWordVector;

public class ExePredict {
	String middle1=Util.WorkingDir+"tfidf.arff";
	String middle2=Util.WorkingDir+"tfidf2.arff";
	String middle3=Util.WorkingDir+"tfidf3.arff";
//=============================================================================//
	public ExePredict() throws Exception {}

//=============================================================================//
	public void ChVecter() throws Exception{
	//ベクター化--------------------------------------------------------------------//
	  BufferedReader reader;
	  Instances data;

	  try {
		reader = new BufferedReader(new FileReader(Util.FirstFile));
		data = new Instances(reader);
		reader.close();
	  } catch (IOException e) {
		e.printStackTrace();
		throw e;
	  }

	  data.setClassIndex(data.numAttributes() - 1);
	  /*-C :
	   *-L :
	   *-T :
	   *-I :
	   *-N :
	   *-O :
	   *-M :
	   *-S :
	   *-i :入力ファイル名
	   *-o :出力ファイル名
	   */
	  String[] options = {"-C", "-L", "-T", "-I", "-N", "1", "-O", "-M", "1", "-S", "-i", Util.FirstFile, "-o", middle1};
	  StringToWordVector filter =new StringToWordVector();
	  filter.setOptions(options);
	  filter.setInputFormat(data);
	  Instances dataFiltered = Filter.useFilter(data, filter);
	  //System.out.println("\n\nFiltered data:\n\n" + dataFiltered);//###
	  mkMiddleFile(dataFiltered, middle1);
	}

//=============================================================================//
	private void mkMiddleFile(Instances dataFiltered, String middle) throws Exception{
		//中間ファイル(middle1)作成のためのファイル処理--------------------------------------------------------------------//
		File newfile = new File(middle);
		FileWriter filewriter = null;

		try {
			newfile.createNewFile();
			filewriter = new FileWriter(newfile);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}

		BufferedWriter bw = new BufferedWriter(filewriter);
		PrintWriter pw = new PrintWriter(bw);
		pw.println(dataFiltered);
		pw.close();
	}

//=============================================================================//
	public void reorderFile() throws Exception{
		//リオーダー処理--------------------------------------------------------------------//
		/*weka.filters.unsupervised.attribute.Reorder -R 2-last,first -i /Users/s141015/Dropbox/myfolder/research/NaiveBayes/eclipse-tfidf.arff -o /Users/s141015/Dropbox/myfolder/research/NaiveBayes/eclipse-tfidf2.arff
*/
		BufferedReader reader;
		  Instances data;

		  try {
			reader = new BufferedReader(new FileReader(middle1));
			data = new Instances(reader);
			reader.close();
		  } catch (IOException e) {
			e.printStackTrace();
			throw e;
		  }
		  data.setClassIndex(data.numAttributes() - 1);
		  String[] options_ro ={"-R","2-last,first","-i",middle1,"-o",middle2};
		  Reorder ro = new Reorder();
		  ro.setOptions(options_ro);
		  ro.setInputFormat(data);
		  Instances dataFiltered = Filter.useFilter(data, ro);
		  //System.out.println("\n\nFiltered data:\n\n" + dataFiltered);//###
		  mkMiddleFile(dataFiltered,middle2);
		  //ro.main(options_ro);//middle2ファイル生成
	}

//=============================================================================//
	public void predictWithNB() throws Exception{
		//NaiveBayes実行--------------------------------------------------------------------//
		ArffLoader loader = new ArffLoader();
		try {
			loader.setFile(new File(middle2));
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		Instances structure = loader.getStructure();
		structure.setClassIndex(structure.numAttributes() - 1);
		Classifier nb = new NaiveBayes();
		nb.buildClassifier(structure);
		Instances test = DataSource.read(middle2);
		test.setClassIndex(test.numAttributes() - 1);
		System.out.println("# : actual - predicted");//###
		for (int i = 0; i < test.numInstances(); i++) {
		  double pred = nb.classifyInstance(test.instance(i));
		  double[] dist = nb.distributionForInstance(test.instance(i));
			  System.out.print((i+1) + " : ");//###
			  System.out.print(test.instance(i).toString(test.classIndex()) + " - ");//###
			  System.out.println(test.classAttribute().value((int) pred) );//###
			  //System.out.println(" - "+Utils.arrayToString(dist));//###
		  }
		//System.out.println(nb);//###
		//--------------------------------------------------------------------/
}

}
