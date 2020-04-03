package sh.devspace.demo;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

/**
 * A java simple for DevSpace
 *
 * @author heroyin
 */
public class QuickStart
{
    public static void main( String[] args ) throws IOException
    {
		HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/", new MyHandler());
        server.setExecutor(null); 
        server.start();
	}
	
	static class MyHandler implements HttpHandler {
		
        @Override
        public void handle(HttpExchange t) throws IOException {
            String response = "<html>" +  
			"<head>" +  
			"	<link rel=\"stylesheet\" href=\"https://devspace.cloud/quickstart.css\">" +  
			"</head>" +  
			"<body>" +  
			"	<img src=\"https://devspace.cloud/img/congrats.gif\" />" +  
			"	<h1>You deployed this project with DevSpace!</h1>" +  
			"	<div>" +  
			"		<h2>Now it's time to start the development mode:  </h2>" +  
			"		<ol>" +  
			"			<li>Press CTRL+C or ENTER to terminate <code>devspace open</code></li>" +  
			"			<li>Run: <code>devspace dev</code></li>" +  
			"			<li>Edit this text in <code>main.go</code> and save the file</li>" +  
			"			<li>Check the logs to see how <code>CompileDaemon</code> recompiles and restarts this project</li>" +  
			"			<li>Reload browser to see the changes</li>" +  
			"		</ol>" +  
			"	</div>" +  
			"</body>" +  
			"</html>";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

}
