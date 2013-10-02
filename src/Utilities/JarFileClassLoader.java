package Utilities;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * JARファイルからクラスを読み込むクラスローダー
 * @author NAT
 */
public class JarFileClassLoader extends URLClassLoader {
    /**
     * コンストラクタ
     * @param jarFiles クラスを読み込むJARファイルの配列
     * @param parent 親となるクラスローダー
     * @throws MalformedURLException パスを URL として構文解析できない場合
     */
    public JarFileClassLoader(File[] jarFiles, ClassLoader parent) throws MalformedURLException {
        super(toURLs(jarFiles), parent);
    }

    /**
     * コンストラクタ
     * @param jarFile クラスを読み込むJARファイル
     * @param parent 親となるクラスローダー
     * @throws MalformedURLException パスを URL として構文解析できない場合
     */
    public JarFileClassLoader(File jarFile, ClassLoader parent) throws MalformedURLException {
        this(new File[]{jarFile}, parent);
    }

    private static URL[] toURLs(File[] jarFiles) throws MalformedURLException {
        URL[] urls = new URL[jarFiles.length];
        for (int i = 0; i < jarFiles.length; i++) {
            urls[i] = jarFiles[i].toURL();
        }
        return urls;
    }
}