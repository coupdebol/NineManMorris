import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 */

/**
 * @author Julien Antony
 *
 */
public class Engine {

	/**
	 * @param args
	 * 
	 */
	private static String method = "";
	private static String filename = "";
	private static List<Solver> solvers = new ArrayList<>();
	private static Solver solver = null;
	
	public static void main(String[] args) {
		System.out.println("This is the inference engine!");
		try
		{
			method = args[0];
			filename = args[1];
		}
		catch(IndexOutOfBoundsException e)
		{
			e.printStackTrace();
		}
		initMethods();
		System.out.println("method:" + method);
		System.out.println("filename:" + filename );
		
		//read file
		File file = new File(filename);
		BufferedReader br = null;
		FileReader fr = null;
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//read all lines
		List<String> lines = new ArrayList<>();
		try {
			while (br.ready())
			{
				lines.add(br.readLine());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//separate knowledge and query
		String[] knowledge;
		String query = "";
		for(int i=0; i<lines.size()-1;i++)
		{
			if(lines.get(i).equals("TELL"))
			{
				String line = lines.get(i+1);
				knowledge = line.split(";");
				System.out.println(line);
			}
			if(lines.get(i).equals("ASK"))
			{
				query = lines.get(i+1);
				System.out.println(query);
			}
		}
		
		

	}

	private static void initMethods() {
		//add all solvers
		
		//find correct solver
		for(Solver s:solvers)
		{
			if(s.getSolverName().equals(method))
			{
				solver = s;
			}
		}
		
	}
}
