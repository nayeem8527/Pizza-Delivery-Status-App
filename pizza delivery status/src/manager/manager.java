package manager;

/*
 * Mohammad Nayeem
 * 		2014147
 * Mayank Attri
 * 		2014063
 * 
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/manager" })

public class manager extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	int temp=1;
	
    public manager() 
    {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String option = request.getParameter("option");
		if(option!=null)
		{
			int linec;
			linec = Integer.parseInt(option);
			ArrayList<String> data = new ArrayList<>();
			int extra=1;
			//change your text file location
			RandomAccessFile br = new RandomAccessFile("C:/Users/M.NAYEEM/workspace1/lab9/src/data.txt","rw");
			br.seek(0);
			String line=null;
			while((line = br.readLine())!=null)
			{
				if(line.isEmpty())
				{
					break;
				}
				if(extra==linec)
				{
					String[] words = line.split("<<");
					if(words[words.length-1].compareTo("Ordered")==0)
					{
						line=line.replaceAll("Ordered","Preparation");
						data.add(line);
						extra++;
					}
					else if(words[words.length-1].compareTo("Preparation")==0)
					{
						line=line.replaceAll("Preparation","Bake");
						data.add(line);
						extra++;
					}
					else if(words[words.length-1].compareTo("Bake")==0)
					{
						line=line.replaceAll("Bake","Quality");
						data.add(line);
						extra++;
					}
					else if(words[words.length-1].compareTo("Quality")==0)
					{
						line=line.replaceAll("Quality","Out");
						data.add(line);
						extra++;
					}
					else if(words[words.length-1].compareTo("Out")==0)
					{
						line=line.replaceAll("Out","Delivered");
						data.add(line);
						extra++;
					}
					else
					{
						data.add(line);
						extra++;
					}
				}
				else
				{
					data.add(line);
					extra++;
				}
			}
			data.add("");
			data.add("bk");
			br.seek(0);
			for(int i=0;i<data.size();i++)
			{
				br.write((data.get(i)).getBytes());
				//if(i!=data.size()-1)
				br.write(("\n").getBytes());
			}
//			br.write(("\n").getBytes());
			br.close();
			HomePage(request, response);
		}
		else
		{
			HomePage(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	
	}
	
	private void HomePage(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		response.setContentType("text/hmtl");
		PrintWriter writer = response.getWriter();
		writer.print("<html><head>");
		writer.print("<title>Manager</title>");
		writer.print("</head><body style='background-color:#C1FDB5'>");
		//change your text file location
		RandomAccessFile br = new RandomAccessFile("C:/Users/M.NAYEEM/workspace1/lab9/src/data.txt","rw");
		br.seek(0);
		String line=null;		
		while((line=br.readLine())!=null)
		{
			if(line.isEmpty())
				break;
			String[] words = line.split("<<");
			writer.print(words[0]+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+ words[words.length-1]);
			String temp1 = String.valueOf(temp);
			writer.print("<a href='?option="+temp1+"'><button type='button' style='margin-left:100px'>Update</button></a>");
			temp++;
			writer.print("<br/><br/>");
		}
		temp=1;
		br.close();
		writer.print("</body></html>");
	}
	
	
}
