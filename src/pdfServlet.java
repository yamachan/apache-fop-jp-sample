import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopConfParser;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.FopFactoryBuilder;
import org.apache.fop.apps.MimeConstants;

import dao.ClientDAO;
import dao.DocumentDAO;
import dto.ClientDTO;
import dto.DocumentDTO;

@WebServlet("/pdfServlet")
public class pdfServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TransformerFactory tFactory = TransformerFactory.newInstance();

    public pdfServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
	    	request.setCharacterEncoding("utf-8");
	    	String i_fname = request.getParameter("i_fname");
	    	Date i_fdate = new Date(request.getParameter("i_fdate"));
	    	long i_ccode = Long.parseLong(request.getParameter("i_ccode"));
	    	ClientDTO client = ClientDAO.getClient(i_ccode);
	    	String i_body = request.getParameter("i_body");
	    	i_body =  i_body == null ? "EMPTY" : i_body;
	    	//i_body +=  " (i_fname=" + i_fname + ", i_fdate=" + i_fdate + ")";
	    	String xmlData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><?xml-stylesheet type=\"application/xml\"?>"
	    			+ "<users-data>"
	    			+ "<date>" + (new Date()).toString() + "</date>"
	    			+ "<cname>" + client.getName() + "</cname>"
	    			+ "<cage>" + client.getAge() + "</cage>"
	    			+ "<body>" + i_body + "</body>"
	    			+ "</users-data>";
	    	FopConfParser fopConfParser = new FopConfParser(new File(this.getServletContext().getRealPath("/WEB-INF/fop.xconf")));
	    	FopFactoryBuilder fopFactoryBuilder = fopConfParser.getFopFactoryBuilder();
	    	FopFactory fopFactory = fopFactoryBuilder.build();
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        FOUserAgent userAgent = fopFactory.newFOUserAgent();
	        userAgent.setTitle("yamachan360's sample PDF");
	        Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, userAgent, out);
	        //Source xsl = new StreamSource(this.getServletContext().getRealPath("/WEB-INF/sample.xsl"));
	        DocumentDTO document = DocumentDAO.getDocument(i_fdate, i_fname);
	        Source xsl = new StreamSource(new StringReader(document.getFormat()));
	        Transformer transformer = tFactory.newTransformer(xsl);
	        Result res = new SAXResult(fop.getDefaultHandler());
	        transformer.transform(new StreamSource(new StringReader(xmlData)), res);
	        response.setContentType("application/pdf");
	        response.setContentLength(out.size());
	        response.setHeader("Content-Disposition", "inline");
	        response.getOutputStream().write(out.toByteArray());
	        response.getOutputStream().flush();
	    } catch (Exception ex) {
	        throw new ServletException(ex);
	    }
	}

}
