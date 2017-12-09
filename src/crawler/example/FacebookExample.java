package crawler.example;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.github.abola.crawler.CrawlerPack;


/**
 * 資料探索練習 Facebook Graph Api Search 
 * 
 * 重點
 * 1. 利用Graph Api調整出需要的資料
 * 2. 取得一組Access Token (試著使用 long term token)
 * 3. 試著用『excel』或任何最簡易的方式，對資料進行探索
 * 
 * @author Abola Lee
 *
 */
public class FacebookExample {
	
	public static void main(String[] args) {
		
		// 遠端資料路徑
		// [query sample]
		// search?q=靠北&type=page&limit=1000&fields=name,id,likes,talking_about_count,fan_count
		String uri = 
				"https://graph.facebook.com/v2.5"
				+ "/search?q=批踢踢&type=page&limit=1000&fields=name,id,likes,talking_about_count,fan_count"
				+ "&access_token=EAACEdEose0cBACHBUhfyQpsTrux1CMnz66udX4W7QswZBc3jzO2R2NlZC8fTCvBxNZBIHvJ6VVYXGTL8RC6iK6t0c5fzZBSqZBSkxbEW5ZCwqiettwCLCpgY0vzk49kZCM9TN9fdEk4UmjWtF1s7Dsr8sCJ2wVcZCNZAhEd127lO8uvi2U2ZATtZBl7BZAT8J2sP05gZD";



		// Jsoup select 後回傳的是  Elements 物件
//		[data sample]
//		----
//		{
//			"data": [
//			{
//				"name": "靠北工程師",
//					"id": "1632027893700148",
//					"likes": 174587,
//					"talking_about_count": 188119
//			}
//		}
		Elements elems =
				CrawlerPack.start()
				.getFromJson(uri)
				.select("data");
		
		String output = "id,名稱,按讚數,討論人數\n";
		
		// 遂筆處理
		for( Element data: elems ){
			String id = data.select("id").text();
			String name = data.select("name").text();
			String likes = data.select("likes").text();
			String talking_about_count = data.select("talking_about_count").text();
			
			output += id+",\""+name+"\","+likes+","+talking_about_count+"\n";
		}
		
		System.out.println( output );
	} 
}
