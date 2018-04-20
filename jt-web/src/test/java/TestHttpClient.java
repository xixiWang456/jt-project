

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class TestHttpClient {

	@Test
	public void testHttpClient() throws Exception{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String uri="https://item.jd.com/13828888007.html";
		HttpGet get=new HttpGet(uri);
		HttpPost post=new HttpPost(uri);
		CloseableHttpResponse response = httpClient.execute(post);
		if(response.getStatusLine().getStatusCode()==200){
			System.out.println("请求成功");
			System.out.println(EntityUtils.toString(response.getEntity()));
		}
	}
}
