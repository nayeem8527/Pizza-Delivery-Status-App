package customer;

/*
 * Mohammad Nayeem
 * 		2014147
 * Mayank Attri
 * 		2014063
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/home" })
public class Customer extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	String option;
	long a = 123456789;
	int count=0;
	String p,p1,p2,p3,q,q1,q2,q3;
	String pizza,pizza1,pizza2,pizza3,quantity,quantity1,quantity2,quantity3;
	
    public Customer() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		option = request.getParameter("option");
        if (option != null && (option.equals("order"))) 
        {
        	a++;
            Order(request, response, option,a);
        }
        else if(option != null && option.equals("track"))
        {
        	TrackID(request,response,option);
        }
        else if(option != null && option.equals("details"))
        {
        	Details(request,response,option,p,p1,p2,p3,q,q1,q2,q3);
        }
        else 
        {
            HomePage(request, response);
        }
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String name,phone,address,id;
    	pizza = request.getParameter("pizza");
    	pizza1 = request.getParameter("pizza1");
    	pizza2 = request.getParameter("pizza2");
    	pizza3 = request.getParameter("pizza3");
    	quantity = request.getParameter("quantity");
    	quantity1 = request.getParameter("quantity1");
    	quantity2 = request.getParameter("quantity2");
    	quantity3 = request.getParameter("quantity3");
    	name = request.getParameter("name");
    	phone = request.getParameter("phone");
    	address = request.getParameter("Address");
    	id=request.getParameter("id");
    	if(id!=null)
    	{
      		int c=0;
    		RandomAccessFile br = new RandomAccessFile("C:/Users/M.NAYEEM/workspace1/lab9/src/data.txt","rw");
			br.seek(0);
			String line=null;
			while((line=br.readLine())!=null)
			{
				String[] words = line.split("<<");
				if(words[0].compareTo(id)==0)
				{
					c++;
					track(request,response,words[(words.length-1)]);
				}
			}
			if(c==0)
				error(request,response);
			br.close();
    	}
    	else if(count==1)
    	{
    		if(name.compareTo("")==0 || phone.compareTo("")==0 || address.compareTo("")==0)
    		{
    			count=0;
    			error(request,response);
    		}
    		else
    		{
    			count=0;
    			try
    			{
    				String word="Ordered";
    				RandomAccessFile br = new RandomAccessFile("C:/Users/M.NAYEEM/workspace1/lab9/src/data.txt","rw");
    				br.seek(0);
    				ArrayList<String> r = new ArrayList<>();
    				String line=null;
    				while((line = br.readLine())!=null)
    				{
    					if(line.isEmpty())
    					{
    						r.add(a+"<<"+name+"<<"+phone+"<<"+address+"<<"+p+" "+q+"<<"+p1+" "+q1+"<<"+p2+" "+q2+"<<"+p3+" "+q3+"<<"+word);
    	    				break;
    					}
    					else
    						r.add(line);
    				}
    				r.add("");
    				r.add("bk");
    				br.seek(0);
    				for(int i=0;i<r.size();i++)
    				{
    					br.write((r.get(i)).getBytes());
    					br.write(("\n").getBytes());
    				}
    				br.close();
     				track(request,response,word);
    			}
    			catch(FileNotFoundException e)
    			{
    				e.printStackTrace();
    			}

    		}
    	}
    	else if(pizza==null && pizza1==null && pizza2==null && pizza3==null && id==null)
    	{
    		error(request,response);
    	}
    	else
    	{
    		p=pizza;
        	p1=pizza1;
        	p2=pizza2;
        	p3=pizza3;
        	q=quantity;
        	q1=quantity1;
        	q2=quantity2;
        	q3=quantity3;
    		Details(request,response,option,pizza,pizza1,pizza2,pizza3,quantity,quantity1,quantity2,quantity3);
    	}
	}
	
	private void HomePage(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		response.setContentType("text/hmtl");
		PrintWriter writer = response.getWriter();
		writer.print("<hmtl><head>"+
				"<title>Pizza Mania</title>"+
				"</head><body background='C:/Users/M.NAYEEM/workspace1/lab9/src/images/pizza.jpg'>"+
				"<h1 style='text-align:center;margin-top:100px;font-weight:900;font-size:60px'>Pizza Mania</h1>"+
				"<a href='?option=order'><button type='button' style='margin-left:500px'>Order</button></a>"+
				"<a href='?option=track'><button type='button' style='margin:20px'>Track your order</button></a>"+
				"</body></html>"
				);
	}

	protected void Order(HttpServletRequest request,HttpServletResponse response,String option,long id) throws IOException
	{
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.print("<html><head>");
		writer.print("<title>Placing your order</title>");
		writer.print("</head><body style='background-color:#8ACBCC'>");
		writer.print("<h3>Your order id is:  </h3>"+a);
		writer.print("<hr style='border-color:green'/>");
		writer.append("<FORM METHOD=\"POST\">");
		writer.println("Select your pizza!");
		writer.print("<br/><br/><span style='margin-left:420px'>S M L</span>");
		writer.println("<br/><br/>1.<img src='C:/Users/M.NAYEEM/workspace1/lab9/src/images/veggie.png'></img> Veggie Delight   " + "<input type='radio' name=\"pizza\" value='small,Veggie Delight' style='margin-left:70px'>" + "<input type='radio' name=\"pizza\" value='medium,Veggie Delight'>" + "<input type='radio' name=\"pizza\" value='large,Veggie Delight'>" + "<input type='text' name='quantity' value='1' size='5' style='margin-left:120px;text-align:center'>");
		//change the image source according to your place
		writer.println("<br/><br/><br/>2.<img src='C:/Users/M.NAYEEM/workspace1/lab9/src/images/paneer.png'></img> Spicy Paneer   " + "<input type='radio' name='pizza1' value='small,Spicy Paneer' style='margin-left:85px'>" + "<input type='radio' name='pizza1' value='medium,Spicy Paneer'>" + "<input type='radio' name='pizza1' value='large,Spicy Paneer'>" + "<input type='text' name='quantity1' value='1' size='5' align='center' style='margin-left:127px;text-align:center'>");
		writer.println("<br/><br/><br/>3.<img src='C:/Users/M.NAYEEM/workspace1/lab9/src/images/mushroom.png'></img> Mushrooms   " + "<input type='radio' name='pizza2' value='small,Mushrooms' style='margin-left:95px'>" + "<input type='radio' name='pizza2' value='medium,Mushrooms'>" + "<input type='radio' name='pizza2' value='large,Mushrooms'>" + "<input type='text' name='quantity2' value='1' size='5' align='center' style='margin-left:130px;text-align:center'>");
		writer.println("<br/><br/><br/>4.<img src='C:/Users/M.NAYEEM/workspace1/lab9/src/images/chicken.png'></img> Spicy Chicken   " + "<input type='radio' name='pizza3' value='small,Spicy Chicken' style='margin-left:72px'>" + "<input type='radio' name='pizza3' value='medium,Spicy Chicken'>" + "<input type='radio' name='pizza3' value='large,Spicy Chicken'>" + "<input type='text' name='quantity3' value='1' size='5' align='center' style='margin-left:120px;text-align:center'>" );
		
		writer.println("<br/><br/><br/><br/><input type='submit' value='Submit' style='margin-left:500px'/>");
		writer.append("</FORM>");
		writer.print("</body></html>");
	}

	protected void Details(HttpServletRequest request,HttpServletResponse response,String option,String pizza,String pizza1,String pizza2,String pizza3,String quantity,String quantity1,String quantity2,String quantity3) throws IOException
	{
		count++;
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.print("<html><head>");
		writer.print("<title>Contact Information</title>");
		writer.print("</head><body style='background-color:#B4F7A9'>");
		writer.print("<h4>Your order id is:- </h4>");
		writer.print(a);
		writer.print("<h4>Your order is:- </h4>");
		if(pizza!=null)
		{
			writer.print(quantity + "   " + pizza);
		}
		if(pizza1!=null)
		{
			writer.print("<br/>");
			writer.print(quantity1 + "   " + pizza1);
		}
		if(pizza2!=null)
		{
			writer.print("<br/>");
			writer.print(quantity2 + "   " + pizza2);
		}
		if(pizza3!=null)
		{
			writer.print("<br/>");
			writer.print(quantity3 + "   " + pizza3);
		}
		writer.print("<br/><br/><hr><h1>Details</h1></hr>");
		writer.append("<FORM METHOD=\"POST\">");
		writer.print("Name:- ");
		writer.print("<input type='text' name='name'style='margin-left:120px'>");
		writer.print("<br/><br/>Phone Number:- ");
		writer.print("<input type='number' name='phone' style='margin-left:50px'>");
		writer.print("<br/><br/>Address:- ");
		writer.print("<input type='text' name='Address' size='75' style='margin-left:100px'>");
		writer.println("<br/><br/><input type='submit' value='Placing the Order' style='margin-left:80px'/>");
		writer.append("</FORM>");
		writer.print("</body></html>");
	}
	
	protected void error(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.print("<html><head>");
		writer.print("<title>Error</title>");
		writer.print("</head><body style='background-color:#E4DBDA'>");
		writer.print("<h1 style='text-align:center;color:red'>Something is wrong!!!!</h1>");
		writer.print("<h2 style='text-align:center;color:red'>(Fields not filled correctly or properly)</h2>");
		writer.print("<a href='?option=order'><button type='button' style='margin-left:500px'>Order</button></a>");
		writer.print("<a href='?option=track'><button type='button' style='margin:20px'>Track your order</button></a>");
		writer.print("<a href='?option=details'><button type='button' style='margin:20px'>details</button></a>");
		writer.print("</body></html>");
	}
	
	protected void track(HttpServletRequest request,HttpServletResponse response,String word) throws IOException
	{
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.print("<html><head>");
		writer.print("<title>Order Status</title>");
		writer.print("</head><body style='background-color:#F4FA9B'>");
		writer.print("<p style='margin-top:75px;font-weight:bold'>Your order status is:-<p>");
		writer.print("<br/>"+word);
		writer.print("</body></html>");
	}
	
	protected void TrackID(HttpServletRequest request,HttpServletResponse response,String option) throws IOException
	{
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.print("<html><head>");
		writer.print("<title>Tracking Order</title>");
		writer.print("</head><body style='background-color:#C4A1C4'>");
		writer.append("<FORM METHOD=\"POST\">");
		writer.print("<p style='margin-left:400px;margin-top:100px'>Enter your request ID:- ");
		writer.print("<input type='number' name='id'></p>");
		writer.println("<input type='submit' value='Submit' style='margin-left:530px'/>");
		writer.append("</FORM>");
		writer.print("</body></html>");
	}
}
