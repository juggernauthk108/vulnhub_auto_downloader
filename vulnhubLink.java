package crawler;

/*
output of the program in stored in the same repo name: outputURL.txt
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class vulnhubLink {

	public static final String URL = "C:\\Users\\Tejas.Zarekar\\Desktop\\vulnhubHome.html"; 
	public static final String outputUrl = "C:\\Users\\Tejas.Zarekar\\Desktop\\outputURL.txt";

	public static void main(String[] args) throws IOException {

		PrintWriter writer = new PrintWriter(outputUrl, "UTF-8");
		for (int i = 1; i < 22; i++) {
			
			System.out.println("going for: "+i);
			
			String source = getURLSource("https://www.vulnhub.com/?page="+i);

			File file = new File(URL);
			
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			String st;
			while ((st = br.readLine()) != null) {
				if (st.contains(".torrent")) {
					Pattern pattern = Pattern.compile(".*\\\"(.*.torrent)\\\".*");
					Matcher matcher = pattern.matcher(st);
					if (matcher.find())
						//System.out.println(matcher.group(1).replace(".torrent", ""));
					writer.println(matcher.group(1).replace(".torrent", ""));
					
				}
			}
		}
		writer.close();
	}

	public static String getURLSource(String url) throws IOException {
		URL urlObject = new URL(url);
		URLConnection urlConnection = urlObject.openConnection();
		urlConnection.setRequestProperty("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");

		return toString(urlConnection.getInputStream());
	}

	private static String toString(InputStream inputStream) throws IOException {
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"))) {
			String inputLine;
			StringBuilder stringBuilder = new StringBuilder();

			PrintWriter writer = new PrintWriter(URL, "UTF-8");

			while ((inputLine = bufferedReader.readLine()) != null) {
				writer.println(inputLine);
				stringBuilder.append(inputLine);
			}

			writer.close();
			return stringBuilder.toString();
		}
	}
}
