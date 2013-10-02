package Developer_Recommendation;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MkArff {
	protected PrintWriter pw;

//=============================================================================//
	public MkArff(String _file_name) throws Exception{

		File newfile = new File(_file_name);
		newfile.createNewFile();
		File file = new File(_file_name);
			try {
				if (checkBeforeWritefile(file)) {
					 pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
				}else{
			        System.out.println("ファイルに書き込めません");
			    }
			}catch(IOException e){
			      System.out.println(e);
			      throw new IOException(e);
			}
	}

//=============================================================================//
	private boolean checkBeforeWritefile(File _file){
	    if (_file.exists()){
	      if (_file.isFile() && _file.canWrite()){
	        return true;
	      }
	    }
	    return false;
	}

//=============================================================================//
	public void header(){
		pw.println("%test");
	   pw.println("@relation test");
	   pw.println();
	}

//=============================================================================//
	public String trim(String target){
		if(target!=null){
			target.replaceAll("\n", " ");
			target.replaceAll("\t", " ");
			target.replaceAll(",", " ");
			target.replaceAll("\"", " ");
			target.replaceAll("\\\\", "/");
			target.replaceAll("　", "_");
			target.replaceAll("  ", " ");
			target.replaceAll("  ", " ");
			target.replaceAll("  ", " ");
		return target;
		}else{
			return "";
		}
	}

//=============================================================================//
	public void closeWriter(){
		pw.close();
	}


}
