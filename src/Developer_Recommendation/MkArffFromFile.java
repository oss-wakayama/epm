package Developer_Recommendation;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;
/**
 *
 * @author s141015
 *@warning ファイル内のバックスラッシュ残しておくとWEKAが制御文字として扱い，エラーを掃く場合がある
 */
public class MkArffFromFile extends MkArff{
	String e[][] = new String[1000000][3];
	HashSet<String> h = new HashSet<String>();
	int lnNum=0;
//=============================================================================//
	public MkArffFromFile(String _file_name) throws Exception{
		super(_file_name);
	}


//=============================================================================//
	public void attribute() throws Exception{
		 pw.println("@attribute body string");
		 pw.print("@attribute class{");
		 Iterator<String> it = h.iterator();
		 for(int i=0;it.hasNext();i++){
			 if(i>0){
				 pw.print("," + it.next());
			 }else{
				 pw.print(it.next());
			 }
		 }
		 pw.println("}");
		 pw.println();
	}

//=============================================================================//
	public void data() throws Exception{
		pw.println("@data");
        for(int i=0 ;i<lnNum;i++) {
        	pw.print("\"");
        	pw.print(e[i][2]);
        	pw.print("\",");
        	pw.println(e[i][1]);
        	pw.println();
        }
	}

//=============================================================================//


    public void readCSV(String file){
    	int i;
    	try {
		      File csv = new File(Util.WorkingDir+file); // CSVデータファイル
		      BufferedReader br = new BufferedReader(new FileReader(csv));

		      // 最終行まで読み込む
		      String line = "";
		      while ((line = br.readLine()) != null) {

		        // 1行をデータの要素に分割
		        StringTokenizer st = new StringTokenizer(line, ",");
		        i=0;
		        while (st.hasMoreTokens()) {
		          // 1行の各要素をタブ区切りで表示
		         e[lnNum][i++]=trim(st.nextToken());//エラーが出た場合description中の","を排除できていない
		        }
		        h.add(e[lnNum][1]);
		        lnNum++;
		      }
		      br.close();

		    } catch (FileNotFoundException e) {
		      // Fileオブジェクト生成時の例外捕捉
		      e.printStackTrace();
		    } catch (IOException e) {
		      // BufferedReaderオブジェクトのクローズ時の例外捕捉
		      e.printStackTrace();
		    }
    }
    public void readTXT(String file){
    	int i;
    	try {
		      File csv = new File(Util.WorkingDir+file); // CSVデータファイル
		      BufferedReader br = new BufferedReader(new FileReader(csv));

		      // 最終行まで読み込む
		      String line = "";
		      while ((line = br.readLine()) != null) {

		        // 1行をデータの要素に分割
		        StringTokenizer st = new StringTokenizer(line, "\t");
		        i=0;
		        while (st.hasMoreTokens()) {
		          // 1行の各要素をタブ区切りで表示
		         e[lnNum][i++]=trim(st.nextToken());//エラーが出た場合description中の","を排除できていない
		        }
		        h.add(e[lnNum][1]);
		        lnNum++;
		      }
		      br.close();

		    } catch (FileNotFoundException e) {
		      // Fileオブジェクト生成時の例外捕捉
		      e.printStackTrace();
		    } catch (IOException e) {
		      // BufferedReaderオブジェクトのクローズ時の例外捕捉
		      e.printStackTrace();
		    }
    }
}
