package irille.pub.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;

import de.innosystec.unrar.Archive;
import de.innosystec.unrar.exception.RarException;
import de.innosystec.unrar.rarfile.FileHeader;

/**
 * RAR格式压缩文件解压工具类
 * 不支持RAR格式压缩
 * 支持中文,支持RAR压缩文件密码
 * 依赖jar包
 * commons-io.jar
 * commons-logging.jar
 * java-unrar-decryption-supported.jar
 * gnu-crypto.jar
 * 
 * @author 
 */
public class RarDecompressionUtil {
	
	public static final String SEPARATOR = File.separator;
	
	// =============================== RAR Format ================================
	/**
	 * 解压指定RAR文件到当前文件夹
	 * @param srcRar 指定解压
	 *  @param password 压缩文件时设定的密码
	 * @throws IOException 
	 */
	public static void unrar(String srcRar, String password) throws IOException {
		unrar(srcRar, null, password);
	}
	
	/**
	 * 解压指定的RAR压缩文件到指定的目录中
	 * @param srcRar 指定的RAR压缩文件
	 * @param destPath 指定解压到的目录
	 *  @param password 压缩文件时设定的密码
	 * @throws IOException 
	 */
	public static void unrar(String srcRar, String destPath, String password) throws IOException {
		File srcFile = new File(srcRar);
		if (!srcFile.exists()) {
			return;
		}
		if (null == destPath || destPath.length() == 0) {
			unrar(srcFile, srcFile.getParent(), password);
			return;
		}
		unrar(srcFile,destPath, password);
	}
	
	/**
	 * 解压指定RAR文件到当前文件夹
	 * @param srcRarFile 解压文件
	 *  @param password 压缩文件时设定的密码
	 * @throws IOException 
	 */
	public static void unrar(File srcRarFile, String password) throws IOException {
		if (null == srcRarFile || !srcRarFile.exists()) {
			throw new IOException("指定文件不存在.");
		}
		unrar(srcRarFile, srcRarFile.getParent(),password);
	}
	
	/**
	 * 解压指定RAR文件到指定的路径
	 * @param srcRarFile 需要解压RAR文件
	 * @param destPath 指定解压路径
	 * @param password 压缩文件时设定的密码
	 * @throws IOException 
	 */
	public static void unrar(File srcRarFile, String destPath, String password) throws IOException {
		if (null == srcRarFile || !srcRarFile.exists()) {
			throw new IOException("指定压缩文件不存在.");
		}
		if (!destPath.endsWith(SEPARATOR)) {
			destPath += SEPARATOR;
		}
		Archive archive = null;
		OutputStream unOut = null;
		try {
			archive = new Archive(srcRarFile, password, false);
			FileHeader fileHeader = archive.nextFileHeader();
			if(null == fileHeader){
				archive.close();
				throw new IOException("密码有误.");
			}
			while(null != fileHeader) {
				if (!fileHeader.isDirectory()) {
					// 1 根据不同的操作系统拿到相应的 destDirName 和 destFileName
					String destFileName = "";
					String destDirName = "";
					String str = fileHeader.getFileNameString();
					if (SEPARATOR.equals("/")) {		// 非windows系统
						destFileName = destPath.replaceAll("\\\\", "/");
						//destDirName = destFileName.substring(0, destFileName.lastIndexOf("/"));
						str = str.replace("\\", "/");
					} else {		// windows系统
						destFileName = destPath.replaceAll("/", "\\\\");
						// D:\\uploads\\2013-10-30\\cccccc\\admin96\\
						//destDirName = destFileName.substring(0, destFileName.lastIndexOf("\\"));
						// D:\\uploads\\2013-10-30\\cccccc\\admin96
					}
					// 创建文件夹
					File destFile = new File(destFileName,str);  
				    //File destDir = destFile.getParentFile();  
				    if (!destFile.getParentFile().exists()) {  
				    	destFile.getParentFile().mkdirs();  
				    }  
				    // 抽取压缩文件   
				    unOut = new FileOutputStream(destFile);  
				    archive.extractFile(fileHeader, unOut);  
				    unOut.flush();  
				    unOut.close();  

				}
				fileHeader = archive.nextFileHeader();
			}
			archive.close();
		} catch (RarException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(unOut);
		}
	}
	
	public static void main(String[] args) {
		String srcRarFile = "E:/2013111311211111.rar";
		String destPath = "";
		String password = "";
		try {
			RarDecompressionUtil.unrar(srcRarFile, destPath, password);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}