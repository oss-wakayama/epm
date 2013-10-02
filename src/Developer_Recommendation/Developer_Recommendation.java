package Developer_Recommendation;

import Developer_Recommendation.ExePredict;
import Developer_Recommendation.MkArffFromDB;
import Developer_Recommendation.MkArffFromFile;
import Developer_Recommendation.Util;

public class Developer_Recommendation {

public static void execute() throws Exception{
	System.out.println("exec");
	//Util.FirstFile = Util.WorkingDir+Util.PlainFile;
	//データベースにアクセスし不具合の修正履歴を取得し，arff形式のファイルを作成する

	Util.FirstFile = Util.WorkingDir+Util.PlainFile;
	MkArffFromDB mkad = new MkArffFromDB(Util.FirstFile);
	mkad.header();
	mkad.attribute();
	mkad.data();
	mkad.predictPart();
	mkad.closeWriter();

	MkArffFromFile mka = new MkArffFromFile(Util.FirstFile);
	System.out.println("reading file");
	mka.readTXT(Util.thunderbirdTxt);
	System.out.println("header will be printed");
	mka.header();
	System.out.println("attribute will be printed");
	mka.attribute();
	System.out.println("data will be printed");
	mka.data();
	System.out.println("writer will be closed");
	mka.closeWriter();
	//Util.FirstFile =Util.WorkingDir+Util.DebugFile;//debug
	ExePredict e =new ExePredict();
	System.out.println("veterizing");
	e.ChVecter();
	System.out.println("reordering");
	e.reorderFile();
	System.out.println("predicting");
	e.predictWithNB();
	System.out.println("complete!!");
}

}
